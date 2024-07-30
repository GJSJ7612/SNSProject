package org.example.snsproject.entity.blog;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.example.snsproject.entity.User;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class Article {
    private int id;
    private int commentCounts;
    private Article_body body;
    @JsonFormat(pattern = "YYYY.MM.dd HH:mm")
    private LocalDateTime createDate;
    private String summary;
    private String title;
    private int viewCounts;
    private int authorId;
    private int bodyId;
    private int categoryId;
    private int weight;
    private List<Tag> tags;
    private User author;
    private Category category;
    private int articleId;
}
