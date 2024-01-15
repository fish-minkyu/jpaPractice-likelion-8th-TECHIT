package com.jpapractice.likelionhomework.book.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Entity
@NoArgsConstructor
public class Book {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Setter
  private String title;
  @Setter
  private String summary;
  @Setter
  private Integer rating;

  @Setter
  @ManyToOne
  private Author author;

  public Book(String title, String summary, Integer rating, Author author) {
    this.title = title;
    this.summary = summary;
    this.rating = rating;
    this.author = author;
  }
}
