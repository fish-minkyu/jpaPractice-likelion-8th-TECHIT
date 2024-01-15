package com.jpapractice.likelionhomework.article.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class Article {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Setter
  private String title;
  @Setter
  private String content;
  @Setter
  private String writer;
  @OneToMany(mappedBy = "article")
  private List<Comment> comments = new ArrayList<>();

  public Article() {}

  // Dto -> Entity로 갈 때 필요
  public Article(String title, String content, String writer) {
    this.title = title;
    this.content = content;
    this.writer = writer;
  }
}
