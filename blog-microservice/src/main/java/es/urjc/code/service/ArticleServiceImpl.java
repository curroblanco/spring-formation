package es.urjc.code.service;

import es.urjc.code.dto.ArticleDto;
import es.urjc.code.dto.CommentDto;
import es.urjc.code.dto.MessageDto;
import es.urjc.code.entity.Article;
import es.urjc.code.entity.Comment;
import es.urjc.code.exception.MessageContainsSwearingException;
import es.urjc.code.mapper.ArticleTransformer;
import es.urjc.code.mapper.CommentTransformer;
import es.urjc.code.repository.ArticleRepository;
import es.urjc.code.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ArticleServiceImpl implements ArticleService {

    private static final String SWEARING_ERROR_MESSAGE = "Swearing words found in comment. It won't get published";

    private ArticleRepository articleRepository;
    private ArticleTransformer articleTransformer;
    private CommentRepository commentRepository;
    private CommentTransformer commentTransformer;
    private SwearingService swearingService;

    @Autowired
    public ArticleServiceImpl(ArticleRepository articleRepository, ArticleTransformer articleTransformer, CommentRepository commentRepository, CommentTransformer commentTransformer, SwearingService swearingService) {
        this.articleRepository = articleRepository;
        this.articleTransformer = articleTransformer;
        this.commentRepository = commentRepository;
        this.commentTransformer = commentTransformer;
        this.swearingService = swearingService;
    }

    @Override
    public List<ArticleDto> findAll() {

        return articleRepository.findAll()
                .stream()
                .map(articleTransformer::fromEntityToDtoWithoutComments)
                .collect(Collectors.toList());
    }

    @Override
    public ArticleDto findOne(long id) {

        return articleRepository.findById(id).
                map(articleTransformer::fromEntityToDto)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public long insertOne(ArticleDto articleDto) {

        Article article = articleTransformer.fromDtoToEntity(articleDto);

        return articleRepository.save(article).getId();
    }

    @Override
    public ArticleDto insertOneCommentInBlog(long id, CommentDto commentDto) throws MessageContainsSwearingException {
        Optional<Article> article = articleRepository.findById(id);
        checkComment(swearingService.evaluateComment(new MessageDto(commentDto.getMessage())));

        if(article.isPresent()) {
            Comment comment = commentTransformer.fromDtoToEntity(commentDto);
            comment.setArticle(article.get());
            commentRepository.save(comment);
            return articleTransformer.fromEntityToDto(articleRepository.save(article.get()));
        } else {
            throw new EntityNotFoundException();
        }
    }

    @Override
    public ArticleDto updateOne(long id, ArticleDto articleDto) {

        Optional<Article> articleToBeUpdated = articleRepository.findById(id);

        return articleToBeUpdated
                .map(i -> {
                    populateArticleForDb(i, articleDto);
                    articleRepository.save(i);
                    return articleTransformer.fromEntityToDto(i);
                })
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public ArticleDto deleteOne(long id) {
        Article article = articleRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        articleRepository.deleteById(id);
        return articleTransformer.fromEntityToDto(article);
    }

    private void populateArticleForDb(Article article, ArticleDto articleDto) {
        article.setTitle(articleDto.getTitle());
        article.setText(articleDto.getText());
        article.setSummary(articleDto.getSummary());
        article.setAuthorNick(articleDto.getAuthorNick());
        article.setAuthorName(articleDto.getAuthorName());
    }

    private void checkComment(boolean result) throws MessageContainsSwearingException {
        if(result) {
            throw new MessageContainsSwearingException(SWEARING_ERROR_MESSAGE);
        }
    }
}
