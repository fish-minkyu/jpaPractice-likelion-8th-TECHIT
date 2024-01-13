package com.jpapractice.likelionhomework.article.entity;

import jakarta.persistence.*;

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
