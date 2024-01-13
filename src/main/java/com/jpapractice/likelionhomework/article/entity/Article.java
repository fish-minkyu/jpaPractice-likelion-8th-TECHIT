package com.jpapractice.likelionhomework.article.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Article {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String title;
  private String content;
  private String writer;
  @OneToMany(mappedBy = "article")
  private List<Comment> comments = new ArrayList<>();
}
