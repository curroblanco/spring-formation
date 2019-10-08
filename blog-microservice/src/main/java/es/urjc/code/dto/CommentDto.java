package es.urjc.code.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class CommentDto {
    private Long id;
    private String author;
    private String message;
    private LocalDate commentDate;
}
