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

  // 랜딩 페이지
  @GetMapping
  public String readAll(Model model) {
    model.addAttribute("articles", articleService.readAll());
    return "article/index";
  }

  // Create
  @GetMapping("/write")
  public String createView() {

    return "article/write";
  }

  @PostMapping
  public String create(
    @ModelAttribute ArticleDto dto
    ) {
    Long newId = articleService.create(dto).getId();
    return String.format("redirect:/article/%d", newId);
  }

  // Read
  @GetMapping("/{id}")
  public String readOne(
    @PathVariable("id") Long id,
    Model model
  ) {
    model.addAttribute("article", articleService.readOne(id));
    return "article/read";
  }

  // Update
  @GetMapping("/{id}/edit")
  public String updateView(
    @PathVariable("id") Long id,
    Model model
  ) {
    model.addAttribute("article", articleService.readOne(id));
    return "article/update";
  }

  @PostMapping("/{id}/update")
  public String update(
    @PathVariable("id") Long id,
    @ModelAttribute ArticleDto dto,
    Model model
  ) {
    articleService.update(dto);
    return String.format("redirect:/article/%d", id);
  }

  // Delete
  @PostMapping("/{id}/delete")
  public String delete(
    @PathVariable("id") Long id,
    Model model
  ) {
    articleService.delete(id);
    return "redirect:/article";
  }
}
