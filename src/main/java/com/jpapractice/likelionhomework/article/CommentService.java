package com.jpapractice.likelionhomework.article;

import com.jpapractice.likelionhomework.article.dto.CommentDto;
import com.jpapractice.likelionhomework.article.entity.Article;
import com.jpapractice.likelionhomework.article.entity.Comment;
import com.jpapractice.likelionhomework.article.repo.ArticleRepository;
import com.jpapractice.likelionhomework.article.repo.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {
  private final ArticleRepository articleRepository;
  private final CommentRepository commentRepository;

  // Create
  public CommentDto create(Long articleId, CommentDto dto) {
    Article article = articleRepository.findById(articleId)
      .orElseThrow();
    Comment comment = new Comment(
      dto.getContent(), dto.getWriter(), article
    );
    return CommentDto.fromEntity(commentRepository.save(comment));
  }

  // Read
  public CommentDto readOne(Long commentId) {
    return CommentDto.fromEntity(commentRepository.findById(commentId).orElseThrow());
  }

  // Update
  public CommentDto update(
    Long commentId,
    CommentDto dto
  ) {
    Comment comment = commentRepository.findById(commentId)
      .orElseThrow();
    comment.setContent(dto.getContent());
    return CommentDto.fromEntity(commentRepository.save(comment));
  }

  // Delete
  public void delete(Long commentId) {
    commentRepository.delete(commentRepository.findById(commentId)
      .orElseThrow());
  }
}
