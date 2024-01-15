package com.jpapractice.likelionhomework.book;

import com.jpapractice.likelionhomework.book.dto.BookDto;
import com.jpapractice.likelionhomework.book.entity.Author;
import com.jpapractice.likelionhomework.book.entity.Book;
import com.jpapractice.likelionhomework.book.repo.AuthorRepository;
import com.jpapractice.likelionhomework.book.repo.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
  private final BookRepository bookRepository;
  private final AuthorRepository authorRepository;

  // Create
  public BookDto create(Long authorId, BookDto dto) {
    Author author = authorRepository.findById(authorId)
      .orElseThrow();
    Book book = new Book(
      dto.getTitle(),
      dto.getSummary(),
      dto.getRating(),
      author
    );

    return BookDto.fromEntity(bookRepository.save(book), true);
  }

  // Read
  public BookDto read(Long id) {
    Book book = bookRepository.findById(id)
      .orElseThrow();
    return BookDto.fromEntity(book, true);
  }

  public List<BookDto> readAll() {
    List<BookDto> books = new ArrayList<>();
    for (Book book: bookRepository.findAll()) {
      books.add(BookDto.fromEntity(book, true));
    }

    return books;
  }

  // Update
  public BookDto update(Long bookId, BookDto dto) {
    Book book = bookRepository.findById(bookId)
      .orElseThrow();
    book.setSummary(dto.getSummary());
    book.setRating(dto.getRating());

    return BookDto.fromEntity(bookRepository.save(book), true);
  }

  // Delete
  public void delete(Long bookId) {
    bookRepository.delete(bookRepository.findById(bookId).orElseThrow());
  }
}
