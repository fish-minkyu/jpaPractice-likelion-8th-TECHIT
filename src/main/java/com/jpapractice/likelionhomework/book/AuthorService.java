package com.jpapractice.likelionhomework.book;

import com.jpapractice.likelionhomework.book.dto.AuthorDto;
import com.jpapractice.likelionhomework.book.entity.Author;
import com.jpapractice.likelionhomework.book.repo.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorService {
  private final AuthorRepository authorRepository;

  // Create
  public AuthorDto create(AuthorDto dto) {
    Author author = new Author(
      dto.getName(), dto.getDebutYear()
    );
    return AuthorDto.fromEntity(authorRepository.save(author));
  }

  // Read
  public List<AuthorDto> readAll() {
    List<AuthorDto> authors = new ArrayList<>();
    for (Author author: authorRepository.findAll()) {
      authors.add(AuthorDto.fromEntity(author));
    }

    return authors;
  }

  public AuthorDto read(Long id) {
    Author author = authorRepository.findById(id)
      .orElseThrow();
    return AuthorDto.fromEntity(author);
  }

/*  // Update
  public AuthorDto update(Long id, AuthorDto dto) {
    Author author = authorRepository.findById(id)
      .orElseThrow();
    author.setName(dto.getName());
    author.setDebutYear(dto.getDebutYear());
    return AuthorDto.fromEntity(authorRepository.save(author));
  }

  // Delete
  public void delete(Long id) {
    authorRepository.delete(
      authorRepository.findById(id).orElseThrow()
    );
  }*/
}
