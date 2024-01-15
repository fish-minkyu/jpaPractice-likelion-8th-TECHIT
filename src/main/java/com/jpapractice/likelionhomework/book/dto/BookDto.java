package com.jpapractice.likelionhomework.book.dto;

import com.jpapractice.likelionhomework.book.entity.Book;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
public class BookDto {
  private Long id;
  @Setter
  private String title;
  @Setter
  private String summary;
  @Setter
  private Integer rating;
  private AuthorDto author;

  public BookDto(String summary, Integer rating) {
    this.summary = summary;
    this.rating = rating;
  }

  public BookDto(String title, String summary, Integer rating) {
    this.id = id;
    this.title = title;
    this.summary = summary;
    this.rating = rating;
  }

  public static BookDto fromEntity(Book entity, boolean withAuthor) {
    BookDto dto = new BookDto(
      entity.getTitle(),
      entity.getSummary(),
      entity.getRating()
    );
    dto.id = entity.getId();
    if (withAuthor) dto.author = new AuthorDto(
      entity.getAuthor().getId(),
      entity.getAuthor().getName(),
      entity.getAuthor().getDebutYear()
    );

    return dto;
  }
}
