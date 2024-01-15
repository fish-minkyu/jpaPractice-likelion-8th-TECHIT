package com.jpapractice.likelionhomework.article;

import com.jpapractice.likelionhomework.article.dto.CommentDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/article/{articleId}/comment")
@RequiredArgsConstructor
public class CommentController {
  private final ArticleService articleService;
  private final CommentService commentService;

  // Create
  @PostMapping
  public String create(
    @PathVariable("articleId") Long articleId,
    @ModelAttribute CommentDto dto
  ) {
    commentService.create(articleId, dto);
    return String.format("redirect:/article/%d", articleId);
  }

  // Update
  @GetMapping("{commentId}/edit")
  public String edit(
    @PathVariable("articleId") Long articleId,
    @PathVariable("commentId") Long commentId,
    Model model
  ) {
    model.addAttribute("article", articleService.readOne(articleId));
    model.addAttribute("comment", commentService.readOne(commentId));
    return "article/comment/edit";
  }

  @PostMapping("{commentId}/update")
  public String update(
    @PathVariable("articleId") Long articleId,
    @PathVariable("commentId") Long commentId,
    @RequestParam("content") String content
  ) {
    commentService.update(commentId, new CommentDto(content));
    return String.format("redirect:/article/%d", articleId);
  }

  // Delete
  @PostMapping("/{commentId}/delete")
  public String delete(
    @PathVariable("articleId") Long articleId,
    @PathVariable("commentId") Long commentId
  ) {
    commentService.delete(commentId);
    return String.format("redirect:/article/%d", articleId);
  }
}
