package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Post {

    private long id;
    private String title;
    private String content;
    private String picUrl;
    private Date createdDate;
    private Category category;
    private User user;

}
