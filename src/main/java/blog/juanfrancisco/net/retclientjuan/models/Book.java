package blog.juanfrancisco.net.retclientjuan.models;

import lombok.Data;
import org.springframework.data.annotation.Id;



@Data
public class Book {
    @Id
    private String commentId;


    private String isbn;
    private String title;
    private String author;
    private Float price;


    private String content;

}
