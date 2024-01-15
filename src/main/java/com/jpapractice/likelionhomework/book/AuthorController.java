package com.jpapractice.likelionhomework.book;

import com.jpapractice.likelionhomework.book.dto.AuthorDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/author")
@RequiredArgsConstructor
public class AuthorController {
  private final AuthorService authorService;

  @GetMapping("/new")
  public String newAuthor() {
    return "book/author/new";
  }

  // Create
  @PostMapping
  public String create(
    @RequestParam("name") String name,
    @RequestParam("debut") Integer debut
  ) {
    authorService.create(new AuthorDto(name, debut));
    return "redirect:/book/new";
  }

  // Read
  @GetMapping("/{id}")
  public String read(
    @PathVariable("id") Long id,
    Model model
  ) {
    model.addAttribute("author", authorService.read(id));
    return "book/author/read";
  }
}
