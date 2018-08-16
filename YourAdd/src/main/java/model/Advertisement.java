package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Advertisement {
    private int id;
    private String title;
    private String description;
    private Category category;
    private int price;
    private String picUrl;
    private User author;



}
