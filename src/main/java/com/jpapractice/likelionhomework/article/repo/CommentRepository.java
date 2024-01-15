package com.jpapractice.likelionhomework.article.repo;

import com.jpapractice.likelionhomework.article.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
