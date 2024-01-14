package com.jpapractice.likelionhomework.article.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class Comment {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String content;
  private String writer;
  @ManyToOne
  private Article article;
}
