package es.urjc.code.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import es.urjc.code.entity.Comment;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ArticleDto {

    private Long id;
    private String authorName;
    private String authorNick;
    private String title;
    private String summary;
    private String text;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Comment> comments;
}
