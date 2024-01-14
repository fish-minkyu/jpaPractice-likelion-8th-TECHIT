package com.jpapractice.likelionhomework.article;

import com.jpapractice.likelionhomework.article.dto.ArticleDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/article")
@RequiredArgsConstructor
public class ArticleController {
  private final ArticleService articleService;

  // Create
  @GetMapping("/create-view")
  public String createView() {

    return "article/create";
  }

  @PostMapping("/create")
  public String create(
    @ModelAttribute ArticleDto article
    ) {
    articleService.create(article);
    return "redirect:/article";
  }

  // Read
  // read-all, 랜딩 페이지
  @GetMapping("/list")
  public String readAll() {
    return "article/list";
  }

  // read-one
  @GetMapping("/{id}/detail")
  public String readOne() {

    return "article/detail";
  }

  // Update
  @GetMapping("/{id}/update-view")
  public String updateView() {
    return "article/update";
  }

  @PostMapping("/{id}/update")
  public String update(
    @PathVariable("id") Long id,
    Model model
  ) {

    return String.format("redirect:/article/%d", id);
  }

  // Delete
  @GetMapping("/{id}/delete-view")
  public String deleteView() {

    return "article/delete";
  }

  @PostMapping("/{id}/delete")
  public String delete(
    @PathVariable("id") Long id,
    Model model
  ) {

    return "redirect:/article";
  }

}
