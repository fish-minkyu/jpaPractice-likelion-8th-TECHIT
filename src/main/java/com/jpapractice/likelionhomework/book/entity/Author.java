package com.jpapractice.likelionhomework.book.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
public class Author {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Setter
  private String name;
  @Setter
  private Integer debutYear;

  @OneToMany(mappedBy = "author")
  private List<Book> books = new ArrayList<>();

  public Author(String name, Integer debutYear) {
    this.name = name;
    this.debutYear = debutYear;
  }
}
