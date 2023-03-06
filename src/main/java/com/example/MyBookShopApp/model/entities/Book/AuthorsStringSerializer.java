package com.example.MyBookShopApp.model.entities.Book;
import com.example.MyBookShopApp.model.entities.Book2Author.Book2Author;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.util.Set;
public class AuthorsStringSerializer extends StdSerializer<Set<Book2Author>> {
    protected AuthorsStringSerializer(Class<Set<Book2Author>> t) {
        super(t);
    }
    protected AuthorsStringSerializer() {
        this(null);
    }
    @Override
    public void serialize(Set<Book2Author> book2Authors,
                          JsonGenerator jsonGenerator,
                          SerializerProvider serializerProvider) throws IOException {
        String authors = "";
        for (Book2Author x : book2Authors) {
            String author = x.getAuthor().getFirstName() + " " + x.getAuthor().getLastName();
            authors = authors.concat(author).concat(", ");
        }
        if (book2Authors.isEmpty()) {
            jsonGenerator.writeString("");
        } else {
            jsonGenerator.writeString(authors.substring(0, authors.length() - 2));
        }
    }
}
