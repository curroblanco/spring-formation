package es.urjc.code.mapper;

import es.urjc.code.dto.CommentDto;
import es.urjc.code.entity.Comment;
import org.springframework.stereotype.Component;

@Component
public class CommentTransformer implements Transformer<Comment, CommentDto> {

    @Override
    public Comment fromDtoToEntity(CommentDto commentDto) {
        Comment comment = new Comment();
        comment.setAuthor(commentDto.getAuthor());
        comment.setMessage(commentDto.getMessage());
        comment.setCommentDate(comment.getCommentDate());

        return comment;
    }

    @Override
    public CommentDto fromEntityToDto(Comment comment) {
        return CommentDto.builder()
                .author(comment.getAuthor())
                .message(comment.getMessage())
                .commentDate(comment.getCommentDate())
                .id(comment.getId())
                .build();
    }
}
