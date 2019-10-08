package es.urjc.code.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import es.urjc.code.dto.ArticleDto;
import es.urjc.code.entity.Article;
import es.urjc.code.repository.ArticleRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class ArticleControllerTest {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturn200AndListOfArticles() throws Exception {
        Article article = articleRepository.save(dumpArticle());

        final ResultActions result = mockMvc.perform(
                get("/articles")
                .accept(MediaType.APPLICATION_JSON)
        );

        result.andExpect(status().isOk());
        result.andExpect(jsonPath("$[0].id").value(article.getId()));
        result.andExpect(jsonPath("$[0].title").value(article.getTitle()));
        result.andExpect(jsonPath("$[0].text").value(article.getText()));
        result.andExpect(jsonPath("$[0].authorName").value(article.getAuthorName()));
        result.andExpect(jsonPath("$[0].authorNick").value(article.getAuthorNick()));
        result.andExpect(jsonPath("$.length()").value(1));
    }

    @Test
    public void shouldReturn403Forbidden() throws Exception {

        final ResultActions result = mockMvc.perform(
                post("/articles")
                        .accept(MediaType.APPLICATION_JSON)
        );

        result.andExpect(status().isForbidden());
    }

    @Test
    public void shouldReturn200AndArticle() throws Exception {

        Article article = articleRepository.save(dumpArticle());

        final ResultActions result = mockMvc.perform(
                get("/articles/1")
                        .accept(MediaType.APPLICATION_JSON)
        );

        result.andExpect(status().isOk());
        result.andExpect(jsonPath("$.id").value(article.getId()));
        result.andExpect(jsonPath("$.title").value(article.getTitle()));
        result.andExpect(jsonPath("$.text").value(article.getText()));
        result.andExpect(jsonPath("$.authorName").value(article.getAuthorName()));
        result.andExpect(jsonPath("$.authorNick").value(article.getAuthorNick()));
    }

    @WithMockUser(value = "admin")
    @Test
    public void shouldReturn201AndArticleId() throws Exception {
        ArticleDto articleDto = dumpArticleDto();
        ObjectMapper objectMapper = new ObjectMapper();
        final ResultActions result = mockMvc.perform(
                post("/articles")
                        .content(objectMapper.writeValueAsString(articleDto))
                        .contentType(MediaType.APPLICATION_JSON)
        );

        result.andExpect(status().isCreated());
        result.andExpect(content().string("1"));
    }

    @Test
    public void shouldReturn201AndArticleComment() {
            // TODO
    }

    @WithMockUser(value = "admin")
    @Test
    public void shouldReturn200AndArticleUpdated() throws Exception {
        Article article = articleRepository.save(dumpArticle());
        ArticleDto articleDto = dumpArticleDto();
        articleDto.setTitle("New Title");

        ObjectMapper objectMapper = new ObjectMapper();
        final ResultActions result = mockMvc.perform(
                put("/articles/1")
                        .content(objectMapper.writeValueAsString(articleDto))
                        .contentType(MediaType.APPLICATION_JSON)
        );

        result.andExpect(status().isOk());
        result.andExpect(jsonPath("$.title").value("New Title"));
        result.andExpect(jsonPath("$.text").value(article.getText()));
        result.andExpect(jsonPath("$.authorName").value(article.getAuthorName()));
        result.andExpect(jsonPath("$.authorNick").value(article.getAuthorNick()));
    }

    @WithMockUser(value = "admin")
    @Test
    public void shouldReturn200AndArticleDeleted() throws Exception {
        Article article = articleRepository.save(dumpArticle());

        final ResultActions result = mockMvc.perform(
                delete("/articles/1")
                        .accept(MediaType.APPLICATION_JSON)
        );

        result.andExpect(status().isOk());
        result.andExpect(jsonPath("$.id").value(article.getId()));
        result.andExpect(jsonPath("$.title").value(article.getTitle()));
        result.andExpect(jsonPath("$.text").value(article.getText()));
        result.andExpect(jsonPath("$.authorName").value(article.getAuthorName()));
        result.andExpect(jsonPath("$.authorNick").value(article.getAuthorNick()));
    }

    private Article dumpArticle() {
        Article article = new Article();
        article.setAuthorName("Test Name");
        article.setAuthorNick("Test Nick");
        article.setSummary("Test Summary");
        article.setTitle("Test Title");
        article.setText("Test Text");
        article.setId(1L);

        return article;
    }

    private ArticleDto dumpArticleDto() {
        return ArticleDto.builder()
                .authorNick("Test Nick")
                .authorName("Test Name")
                .summary("Test Summary")
                .text("Test Text")
                .title("Test Title")
                .id(1L)
                .build();
    }

}