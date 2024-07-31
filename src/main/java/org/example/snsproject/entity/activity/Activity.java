package org.example.snsproject.entity.activity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.example.snsproject.entity.User;
import org.springframework.http.StreamingHttpOutputMessage;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class Activity {
    private int id;
    private int commentCounts;
    @JsonFormat(pattern = "YYYY-MM-dd HH:mm")
    private LocalDateTime createDate;
    private String summary;
    private String title;
    private int viewCounts;
    private int weight;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private int authorId;
    private User author;
    private int bodyId;
    private Activity_body body;
    private int categoryId;
    private Activity_category category;
    private Activity_tag tagId;
    private List<Activity_tag> tags;
}
