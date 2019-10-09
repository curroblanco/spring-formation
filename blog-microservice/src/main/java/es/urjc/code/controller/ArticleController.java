package es.urjc.code.controller;

import es.urjc.code.dto.ArticleDto;
import es.urjc.code.dto.CommentDto;
import es.urjc.code.exception.MessageContainsSwearingException;
import es.urjc.code.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/articles")
public class ArticleController {

    private ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping
    private List<ArticleDto> getAllArticles() {

        return articleService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    private Long insertArticle(@RequestBody ArticleDto articleDto) {

        return articleService.insertOne(articleDto);
    }

    @PostMapping("/{id}/comment")
    @ResponseStatus(HttpStatus.CREATED)
    private ArticleDto insertCommentIntoArticle(@PathVariable Long id, @RequestBody CommentDto commentDto) throws MessageContainsSwearingException {

        return articleService.insertOneCommentInBlog(id, commentDto);
    }

    @GetMapping("/{id}")
    private ArticleDto getAnArticle(@PathVariable Long id) {

        return articleService.findOne(id);
    }

    @PutMapping("/{id}")
    private ArticleDto updateAnArticle(@PathVariable Long id, @RequestBody ArticleDto articleDto) {

        return articleService.updateOne(id, articleDto);
    }

    @DeleteMapping("/{id}")
    private ArticleDto deleteAnArticle(@PathVariable Long id) {


        return articleService.deleteOne(id);
    }
}
