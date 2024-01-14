package com.jpapractice.likelionhomework.article.dto;

import com.jpapractice.likelionhomework.article.entity.Article;
import com.jpapractice.likelionhomework.article.entity.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class ArticleDto {
  private Long id;
  @Setter
  private String title;
  @Setter
  private String content;
  @Setter
  private String writer;
  private List<CommentDto> comments = new ArrayList<>();

  public ArticleDto(String title, String content) {
    this.title = title;
    this.content = content;
  }

  public ArticleDto(String title, String content, String writer) {
    this.title = title;
    this.content = content;
    this.writer = writer;
  }

  public ArticleDto fromEntity(Article entity) {
    ArticleDto dto = new ArticleDto();
    dto.id = entity.getId();
    dto.title = entity.getTitle();
    dto.content = entity.getContent();
    dto.writer = entity.getWriter();
    dto.comments = new ArrayList<>();
    for (Comment comment: entity.getComments())
      dto.comments.add(CommentDto.fromEntity(comment));
    return dto;
  }
}
