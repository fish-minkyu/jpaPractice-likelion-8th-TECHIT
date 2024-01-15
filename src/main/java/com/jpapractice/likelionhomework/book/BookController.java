package com.jpapractice.likelionhomework.book;

import com.jpapractice.likelionhomework.book.dto.BookDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {
  private final BookService bookService;
  private final AuthorService authorService;

  // 랜딩 페이지
  @GetMapping
  public String books(Model model) {
    model.addAttribute("books", bookService.readAll());
    return "book/index";
  }

  // Create
  @GetMapping("/new")
  public String newBook(Model model) {
    model.addAttribute("authors", authorService.readAll());
    return "book/new";
  }

  @PostMapping
  public String create(
    @RequestParam("title") String title,
    @RequestParam("summary") String summary,
    @RequestParam("rating") Integer rating,
    @RequestParam("author-id") Long authorId
  ) {
    Long newId = bookService.create(authorId, new BookDto(title, summary, rating)).getId();
    return String.format("redirect:/book/%d", newId);
  }

  // Read
  @GetMapping("/{id}")
  public String read(
    @PathVariable("id") Long id,
    Model model
  ) {
    model.addAttribute("book", bookService.read(id));
    return "book/read";
  }

  // Update
  @GetMapping("/{id}/edit")
  public String edit(
    @PathVariable("id") Long id,
    Model model
  ) {
    model.addAttribute("book", bookService.read(id));
    return "book/edit";
  }

  @PostMapping("/{id}/update")
  public String update(
    @PathVariable("id") Long id,
    @RequestParam("summary") String summary,
    @RequestParam("rating") Integer rating
  ) {
    bookService.update(id, new BookDto(summary, rating));
    return String.format("redirect:/book/%d", id);
  }

  // Delete
  @PostMapping("/{id}/delete")
  public String delete(
    @PathVariable("id") Long id
  ) {
    bookService.delete(id);
    return "redirect:/book";
  }
}
