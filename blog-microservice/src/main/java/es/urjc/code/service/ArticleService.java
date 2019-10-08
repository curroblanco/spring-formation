package es.urjc.code.service;

import es.urjc.code.dto.ArticleDto;
import es.urjc.code.dto.CommentDto;
import es.urjc.code.exception.MessageContainsSwearingException;

import java.util.List;

public interface ArticleService {

    List<ArticleDto> findAll();
    ArticleDto findOne(long id);
    long insertOne(ArticleDto articleDto);
    ArticleDto insertOneCommentInBlog(long id, CommentDto commentDto) throws MessageContainsSwearingException;
    ArticleDto updateOne(long id, ArticleDto articleDto);
    ArticleDto deleteOne(long id);
}
