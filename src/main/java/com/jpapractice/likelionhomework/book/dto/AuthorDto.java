package com.jpapractice.likelionhomework.book.dto;

import com.jpapractice.likelionhomework.book.entity.Author;
import com.jpapractice.likelionhomework.book.entity.Book;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class AuthorDto {
  private Long id;
  @Setter
  private String name;
  @Setter
  private Integer debutYear;
  @Setter
  private List<BookDto> books = new ArrayList<>();

  public AuthorDto(String name, Integer debutYear) {
    this.name = name;
    this.debutYear = debutYear;
  }

  public AuthorDto(Long id, String name, Integer debutYear) {
    this.id = id; // BookDto에  author의 id를 넣기 위해 생성자에 포함시켰다.
    this.name = name;
    this.debutYear = debutYear;
  }

  // static factory method - way2
  public static AuthorDto fromEntity(Author entity) {
    AuthorDto dto = new AuthorDto(
      entity.getId(),
      entity.getName(),
      entity.getDebutYear()
    );
    for (Book book: entity.getBooks()) {
      dto.books.add(BookDto.fromEntity(book, false));
    }

    return dto;
  }
}
