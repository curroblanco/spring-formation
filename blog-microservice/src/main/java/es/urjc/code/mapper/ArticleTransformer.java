package es.urjc.code.mapper;

import es.urjc.code.dto.ArticleDto;
import es.urjc.code.entity.Article;
import org.springframework.stereotype.Component;

@Component
public class ArticleTransformer implements Transformer<Article, ArticleDto> {

    @Override
    public Article fromDtoToEntity(ArticleDto articleDto) {
        Article article = new Article();

        article.setAuthorName(articleDto.getAuthorName());
        article.setAuthorNick(articleDto.getAuthorNick());
        article.setSummary(articleDto.getSummary());
        article.setText(articleDto.getText());
        article.setTitle(articleDto.getTitle());

        return article;
    }

    @Override
    public ArticleDto fromEntityToDto(Article article) {
        return ArticleDto.builder()
                .authorName(article.getAuthorName())
                .authorNick(article.getAuthorNick())
                .comments(article.getComments())
                .summary(article.getSummary())
                .text(article.getText())
                .title(article.getTitle())
                .id(article.getId())
                .build();
    }

    public ArticleDto fromEntityToDtoWithoutComments(Article article) {
        return ArticleDto.builder()
                .authorName(article.getAuthorName())
                .authorNick(article.getAuthorNick())
                .summary(article.getSummary())
                .text(article.getText())
                .title(article.getTitle())
                .id(article.getId())
                .build();
    }
}
