package com.jpapractice.likelionhomework.book.repo;

import com.jpapractice.likelionhomework.book.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {}
