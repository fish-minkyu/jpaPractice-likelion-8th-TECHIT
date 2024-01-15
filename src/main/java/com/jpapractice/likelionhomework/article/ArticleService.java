package com.jpapractice.likelionhomework.article;

import com.jpapractice.likelionhomework.article.dto.ArticleDto;
import com.jpapractice.likelionhomework.article.entity.Article;
import com.jpapractice.likelionhomework.article.repo.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleService {
  private final ArticleRepository articleRepository;

  // Create
  public ArticleDto create(ArticleDto dto) {
    Article article = new Article(
      dto.getTitle(), dto.getContent(), dto.getWriter()
    );

    return ArticleDto.fromEntity(articleRepository.save(article));
  }

  // Read
  // All
  public List<ArticleDto> readAll() {
    List<ArticleDto> articles = new ArrayList<>();
    for (Article article: articleRepository.findAll()) {
      articles.add(ArticleDto.fromEntity(article));
    }

    return articles;
  }

  // One
  public ArticleDto readOne(Long id) {
    // repository.findById()는 Optional 객체를 반환한다.
    Article article = articleRepository.findById(id).orElseThrow();

    return ArticleDto.fromEntity(article);
  }

  // Update
  public ArticleDto update(ArticleDto dto) {
    Article article = articleRepository.findById(dto.getId()).orElseThrow();
    article.setTitle(dto.getTitle());
    article.setContent(dto.getContent());
    article.setWriter(dto.getWriter());

    return ArticleDto.fromEntity(articleRepository.save(article));
  }

  // Delete
  public void delete(Long id) {
    articleRepository.delete(articleRepository.findById(id).orElseThrow());
  }
}
