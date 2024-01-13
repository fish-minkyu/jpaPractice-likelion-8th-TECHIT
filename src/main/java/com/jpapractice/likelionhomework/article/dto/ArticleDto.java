package com.jpapractice.likelionhomework.article.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

  public ArticleDto(String title, String content) {
    this.title = title;
    this.content = content;
  }

  public ArticleDto(String title, String content, String writer) {
    this.title = title;
    this.content = content;
    this.writer = writer;
  }
}
