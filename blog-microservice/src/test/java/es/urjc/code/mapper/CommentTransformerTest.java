package es.urjc.code.mapper;

import es.urjc.code.dto.CommentDto;
import es.urjc.code.entity.Comment;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class CommentTransformerTest {

    private static final LocalDate CURRENT_DATE = LocalDate.now();

    private CommentTransformer commentTransformer = new CommentTransformer();

    @Test
    public void shouldReturnEntity() {
        CommentDto commentDto = CommentDto
                .builder()
                .commentDate(CURRENT_DATE)
                .message("Test Message")
                .author("Test Author")
                .build();

        Comment comment = commentTransformer.fromDtoToEntity(commentDto);

        assertEquals(CURRENT_DATE, comment.getCommentDate());
        assertEquals("Test Message", comment.getMessage());
        assertEquals("Test Author", comment.getAuthor());
    }

    @Test
    public void shouldReturnDto() {
        Comment comment = new Comment();
        comment.setAuthor("Test Author");
        comment.setCommentDate(CURRENT_DATE);
        comment.setMessage("Test Message");

        CommentDto commentDto = commentTransformer.fromEntityToDto(comment);

        assertEquals(CURRENT_DATE, commentDto.getCommentDate());
        assertEquals("Test Message", commentDto.getMessage());
        assertEquals("Test Author", commentDto.getAuthor());
    }
}