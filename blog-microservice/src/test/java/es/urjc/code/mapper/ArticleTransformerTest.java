package es.urjc.code.mapper;

import es.urjc.code.dto.ArticleDto;
import es.urjc.code.entity.Article;
import es.urjc.code.entity.Comment;
import org.junit.Test;

import java.util.Collections;

import static org.junit.Assert.assertEquals;

public class ArticleTransformerTest {

    private ArticleTransformer articleTransformer = new ArticleTransformer();

    @Test
    public void shouldReturnEntity() {
        ArticleDto articleDto = ArticleDto.builder()
                .text("Test Text")
                .summary("Test Summary")
                .authorName("Test AuthorName")
                .authorNick("Test AuthorNick")
                .title("Test Title")
                .text("Test Text")
                .build();

        Article article = articleTransformer.fromDtoToEntity(articleDto);

        assertEquals("Test Text", article.getText());
        assertEquals("Test Summary", article.getSummary());
        assertEquals("Test AuthorName", article.getAuthorName());
        assertEquals("Test AuthorNick", article.getAuthorNick());
        assertEquals("Test Title", article.getTitle());
        assertEquals("Test Text", article.getText());
    }

    @Test
    public void shouldReturnDto() {
        Article article = new Article();
        article.setId(1L);
        article.setTitle("Test Title");
        article.setSummary("Test Summary");
        article.setAuthorNick("Test AuthorNick");
        article.setAuthorName("Test AuthorName");
        article.setText("Test Text");
        article.setComments(Collections.singletonList(new Comment()));

        ArticleDto articleDto = articleTransformer.fromEntityToDto(article);

        assertEquals(Long.valueOf(1), article.getId());
        assertEquals("Test Text", article.getText());
        assertEquals("Test Summary", article.getSummary());
        assertEquals("Test AuthorName", article.getAuthorName());
        assertEquals("Test AuthorNick", article.getAuthorNick());
        assertEquals("Test Title", article.getTitle());
        assertEquals("Test Text", article.getText());
        assertEquals(1, article.getComments().size());
    }
}