package com.example.MyBookShopApp.model.components;
import lombok.Getter;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
@Getter
public class AuthorBiographyRendering {
    private List<String> shortDescription = new ArrayList<>();
    private List<String> longDescription = new ArrayList<>();

    public void setDescriptions (String biography) {
        String[] description = biography.split(" ");
        int sentenceLength = 15;
        int sentenceNumber = description.length / sentenceLength;
        List<String> desc = new ArrayList<>();
        for (int i = 0; i < sentenceNumber; i++) {
            String sentence = "";
            for (int j = i * sentenceLength ; j < (i + 1) * sentenceLength; j++) {
                sentence = sentence.concat(description[j]).concat("\s");
            }
            desc.add(sentence);
        }
        List<String> paragraph = new ArrayList<>();
        for (int i = 0; i < desc.size(); i++) {
            if (i % 3 == 0 && i != 0) {
                paragraph.add(desc.subList(i - 3, i).stream()
                        .map(e -> e.toString()).reduce("", String::concat));
            }
        }
        shortDescription = paragraph.subList(0, 2);
        longDescription = paragraph.subList(3, paragraph.size());
    }

}
