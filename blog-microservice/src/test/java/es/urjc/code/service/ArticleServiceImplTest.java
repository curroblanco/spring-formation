package es.urjc.code.service;

import es.urjc.code.dto.ArticleDto;
import es.urjc.code.dto.CommentDto;
import es.urjc.code.entity.Article;
import es.urjc.code.entity.Comment;
import es.urjc.code.exception.MessageContainsSwearingException;
import es.urjc.code.mapper.ArticleTransformer;
import es.urjc.code.mapper.CommentTransformer;
import es.urjc.code.repository.ArticleRepository;
import es.urjc.code.repository.CommentRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class ArticleServiceImplTest {

    @Mock
    private ArticleRepository articleRepository;

    @Mock
    private ArticleTransformer articleTransformer;

    @Mock
    private CommentRepository commentRepository;

    @Mock
    private CommentTransformer commentTransformer;

    @Mock
    private SwearingService swearingService;

    @InjectMocks
    private ArticleService articleService = new ArticleServiceImpl(articleRepository, articleTransformer,
            commentRepository, commentTransformer, swearingService);

    @Test
    public void shouldReturnListOfArticles() {
        List<Article> articleList = new ArrayList<>();
        articleList.add(dumpArticle());

        when(articleRepository.findAll()).thenReturn(articleList);
        when(articleTransformer.fromEntityToDto(any())).thenReturn(dumpArticleDto());

        List<ArticleDto> articleDtoList = articleService.findAll();

        assertEquals(1, articleDtoList.size());
    }


    @Test
    public void shouldReturnAnArticle() {

        when(articleRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(dumpArticle()));
        when(articleTransformer.fromEntityToDto(any())).thenReturn(dumpArticleDto());

        ArticleDto articleDto = articleService.findOne(1L);

        assertEquals(Long.valueOf(1), articleDto.getId());
        assertEquals("Test Name", articleDto.getAuthorName());
        assertEquals("Test Nick", articleDto.getAuthorNick());
    }

    @Test
    public void shouldReturnLong() {
        when(articleRepository.save(any())).thenReturn(dumpArticle());

        Long id = articleService.insertOne(dumpArticleDto());

        assertEquals(Long.valueOf(1), id);
    }

    @Test
    public void shouldReturnArticleWithComment() throws MessageContainsSwearingException {
        when(articleRepository.findById(anyLong())).thenReturn(Optional.of(dumpArticle()));
        when(swearingService.evaluateComment(anyString())).thenReturn(Boolean.FALSE);
        when(commentTransformer.fromDtoToEntity(any())).thenReturn(new Comment());
        when(articleTransformer.fromEntityToDto(any())).thenReturn(dumpArticleDto());

        ArticleDto articleDto = articleService.insertOneCommentInBlog(1L, CommentDto.builder().build());

        assertEquals(Long.valueOf(1), articleDto.getId());
        assertEquals("Test Name", articleDto.getAuthorName());
        assertEquals("Test Nick", articleDto.getAuthorNick());
    }

    @Test(expected = MessageContainsSwearingException.class)
    public void shouldReturnException() throws MessageContainsSwearingException {
        when(articleRepository.findById(anyLong())).thenReturn(Optional.of(dumpArticle()));
        when(swearingService.evaluateComment(anyString())).thenReturn(Boolean.TRUE);
        when(commentTransformer.fromDtoToEntity(any())).thenReturn(new Comment());
        when(articleTransformer.fromEntityToDto(any())).thenReturn(dumpArticleDto());

        articleService.insertOneCommentInBlog(1L, CommentDto.builder().message("TEST MESSAGE").build());
    }

    @Test
    public void shouldUpdateArticle() {
        when(articleRepository.findById(anyLong())).thenReturn(Optional.of(dumpArticle()));
        when(articleTransformer.fromEntityToDto(any())).thenReturn(dumpArticleDto());

        ArticleDto articleDto = articleService.updateOne(1L, dumpArticleDto());

        assertEquals(Long.valueOf(1), articleDto.getId());
        assertEquals("Test Name", articleDto.getAuthorName());
        assertEquals("Test Nick", articleDto.getAuthorNick());
    }

    @Test
    public void shouldDeleteArticle() {
        when(articleRepository.findById(anyLong())).thenReturn(Optional.of(dumpArticle()));
        when(articleTransformer.fromEntityToDto(any())).thenReturn(dumpArticleDto());

        ArticleDto articleDto = articleService.deleteOne(1L);

        assertEquals(Long.valueOf(1), articleDto.getId());
        assertEquals("Test Name", articleDto.getAuthorName());
        assertEquals("Test Nick", articleDto.getAuthorNick());
    }

    private Article dumpArticle() {
        Article article = new Article();
        article.setAuthorName("Test Name");
        article.setAuthorNick("Test Nick");
        article.setSummary("Test Summary");
        article.setTitle("Test Title");
        article.setId(1L);

        return article;
    }

    private ArticleDto dumpArticleDto() {

        return ArticleDto.builder()
                .authorNick("Test Nick")
                .authorName("Test Name")
                .summary("Test Summary")
                .text("Test Text")
                .id(1L)
                .build();
    }
}