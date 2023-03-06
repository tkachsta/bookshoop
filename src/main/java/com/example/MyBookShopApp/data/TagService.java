package com.example.MyBookShopApp.data;
import com.example.MyBookShopApp.model.dtos.tags.TagDto;
import com.example.MyBookShopApp.model.entities.Book.BookEntity;
import com.example.MyBookShopApp.model.entities.Book2Tag.Book2Tag;
import com.example.MyBookShopApp.model.entities.Book2Tag.Book2TagRepository;
import com.example.MyBookShopApp.model.entities.Tag.TagEntity;
import com.example.MyBookShopApp.model.entities.Tag.TagRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TagService {
    private final TagRepository tagRepository;
    private final Book2TagRepository book2TagRepository;
    public TagService(TagRepository tagRepository, Book2TagRepository book2TagRepository) {
        this.tagRepository = tagRepository;
        this.book2TagRepository = book2TagRepository;
    }
    public List<TagDto> getExistingTags() {
        List<Book2Tag> book2Tags = book2TagRepository.findAll();
        Map<TagEntity, Integer> tagsFrequency = new HashMap<>();

        book2Tags.forEach(x -> {
            if (!tagsFrequency.containsKey(x.getTag())) {
                tagsFrequency.put(x.getTag(), 1);
            } else {
                tagsFrequency.put(x.getTag(), tagsFrequency.get(x.getTag()) + 1);
            }
        });

        List<TagDto> existingTags = new ArrayList<>();
        Integer min = Collections.min(tagsFrequency.entrySet(), Map.Entry.comparingByValue()).getValue();
        Integer max = Collections.max(tagsFrequency.entrySet(), Map.Entry.comparingByValue()).getValue();
        tagsFrequency.forEach((k, v)  -> {
            TagDto tagDto = new TagDto();
            if ( v < ((max - min) / 5) * 1) {
                tagDto.setTagClass("Tag Tag_xs");
            } else if (v < ((max - min) / 5) * 2) {
                tagDto.setTagClass("Tag Tag_sm");
            } else if (v < ((max - min) / 5) * 3) {
                tagDto.setTagClass("Tag");
            } else if (v < ((max - min) / 5) * 4) {
                tagDto.setTagClass("Tag Tag_md");
            } else {
                tagDto.setTagClass("Tag Tag_lg");
            }
            tagDto.setTag(k);
            tagDto.setFrequency(v);
            existingTags.add(tagDto);
        });
        return existingTags;
    }
    public Page<BookEntity> getBooksByTags(Integer offset, Integer limit, Integer tagId) {
        Optional<TagEntity> tag = tagRepository.findById(tagId);
        Pageable nextPage;
        if (tag.isPresent() && offset == null) {
            nextPage = PageRequest.of(0, 20);
            return book2TagRepository.findBooksByTag(tagId, nextPage);
        } else if (tag.isPresent()) {
            nextPage = PageRequest.of(offset, limit);
            return book2TagRepository.findBooksByTag(tagId, nextPage);
        }
        return null;
    }

    public TagEntity getTagName(Integer id) {
        Optional<TagEntity> tag = tagRepository.findById(id);
        return tag.orElse(null);
    }
}
