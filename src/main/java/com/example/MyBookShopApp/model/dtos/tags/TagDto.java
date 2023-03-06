package com.example.MyBookShopApp.model.dtos.tags;
import com.example.MyBookShopApp.model.entities.Tag.TagEntity;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class TagDto {
    private TagEntity tag;
    private Integer frequency;
    private String tagClass;

}
