package com.example.MyBookShopApp.model.entities.Author;
import com.example.MyBookShopApp.model.entities.Book2Author.Book2Author;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.json.simple.JSONObject;
import java.io.IOException;
import java.util.Set;

public class BookSetSerializer extends StdSerializer<Set<Book2Author>> {

    protected BookSetSerializer(Class<Set<Book2Author>> t) {
        super(t);
    }
    protected BookSetSerializer() {
        this(null);
    }
    @Override
    public void serialize(Set<Book2Author> book2Authors,
                          JsonGenerator jsonGenerator,
                          SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartArray();
        for (Book2Author x : book2Authors) {
            JSONObject object = new JSONObject();
            object.put("bookId", x.getBook().getBookId());
            object.put("description", x.getBook().getDescription());
            object.put("discount", x.getBook().getDiscount());
            object.put("image", x.getBook().getImage());
            object.put("price", x.getBook().getPrice());
            object.put("pubDate", x.getBook().getPub_date());
            object.put("slug", x.getBook().getSlug());
            object.put("title", x.getBook().getTitle());
            object.put("isBestseller", x.getBook().isBestseller());
            jsonGenerator.writeObject(object);
        }
        jsonGenerator.writeEndArray();
    }
}
