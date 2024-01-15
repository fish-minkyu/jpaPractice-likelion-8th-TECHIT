# 리뷰

## Controller

컨트롤러 클래스 자체에 `@RequestMapping`를 추가해두었습니다.

```java
@Controller
@RequestMapping("article")
public class ArticleController {
    // ...
}
```

이렇게 하면 Controller 내부 메서드의 `@RequestMapping`과 Controller의 `@RequestMapping`의
값을 합쳐서 URL이 구성됩니다. 만약 메서드의 매핑에 URL 값을 전달하지 않으면
클래스의 URL 만으로 매핑이 됩니다.

```java
@Controller
@RequestMapping("article")
public class ArticleController {
    // ...

    // 이 메서드는 `/article` 경로로의 요청에 호출됩니다.
    @GetMapping
    public String article(Model model) {
        model.addAttribute("articles", articleService.readAll());
        return "article/index";
    }
    // ...
    
    // 이 메서드는 `/article/write` 경로로의 요청에 호출됩니다.
    @GetMapping("write")
    public String articleWrite() {
        return "article/write";
    }
    
    // ...
}
```

이때 클래스에 붙은 `@RequestMapping`에도 경로 변수를 작성해줄 수 있으며,

```java
@Controller
@RequestMapping("article/{articleId}/comment")
public class CommentController {
    // ...
}
```

이를 메서드에서 매개변수에 할당하는 것도 가능합니다.

```java
@Controller
@RequestMapping("article/{articleId}/comment")
public class CommentController {
    // ...
    @PostMapping
    public String create(
            // 
            @PathVariable("articleId")
            Long articleId,
            @RequestParam("writer")
            String writer,
            @RequestParam("content")
            String content
    ) {
        commentService.create(articleId, new CommentDto(content, writer));
        return String.format("redirect:/article/%d", articleId);
    }
    // ...
}
```

## Entity 클래스

강의중에 사용한 것과 다르게 `@Data`를 사용하지 않았습니다. 대신 전체 클래스에는 `@Getter`를,
속성별로 사용자가 할당할 수 있는 값에는 `@Setter`를 적용해 주었습니다.

```java
@Getter
@Entity
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    private String title;
    @Setter
    @Lob
    private String content;
    @Setter
    private String writer;

    @OneToMany(mappedBy = "article")
    private final List<Comment> comments = new ArrayList<>();
}
```

## DTO 클래스

실제로 데이터베이스에 저장되는 정보와 사용자가 서비스 활용을 위해서 필요로 하는 정보를
분리하기 위해 DTO 클래스를 따로 정의하였습니다.

```java
@Getter
@ToString
@NoArgsConstructor
public class ArticleDto {
    private Long id;
    @Setter
    private String title;
    @Setter
    private String content;
    @Setter
    private String writer;
    private List<CommentDto> comments = new ArrayList<>();
}
```

`ArticleDto` 클래스에는 `List<CommentDto>` 속성을 통해 댓글 목록을 확인할 수 있습니다.

```java
@Getter
@ToString
@NoArgsConstructor
public class CommentDto {
    private Long id;
    @Setter
    private String content;
    @Setter
    private String writer;
}
```

실제 서비스에서 댓글은 사실상 게시글의 부속처럼 활용되기 때문에, `CommentDto`에는 `Article`에 대한 정보가 포함되지 않습니다.

또한 이 DTO 클래스들은 Entity 클래스를 매개변수로 받는 정적 팩토리 메서드가 정의되어 있습니다.

```java
// static factory method
public static ArticleDto fromEntity(Article entity) {
    ArticleDto dto = new ArticleDto();
    dto.id = entity.getId();
    dto.title = entity.getTitle();
    dto.content = entity.getContent().replace("\n", "<br>");
    dto.writer = entity.getWriter();
    dto.comments = new ArrayList<>();
    for (Comment comment: entity.getComments())
        dto.comments.add(CommentDto.fromEntity(comment));
    return dto;
}
```

이를 이용하면 실제로 DTO 객체가 필요한 시점에 `new` 키워드를 이용해 생성하지 않고,
Entity를 바탕으로 DTO를 만드는 과정을 간소화 할 수 있습니다.

```java
public List<ArticleDto> readAll() {
    List<ArticleDto> articleList = new ArrayList<>();
    for (Article article: repository.findAll()) {
        articleList.add(ArticleDto.fromEntity(article));
    }

    return articleList;
}
```

## Service & Repository

기본적으로 `findById()` 메서드의 결과는 `Optional`을 반환합니다.
여기서는 `Optional`의 `.orElseThrow()`를 사용해 존재하지 않는
데이터에 대하여 예외가 발생하도록 하였습니다.

```java
public ArticleDto read(Long id) {
    Article article = repository.findById(id).orElseThrow();
    return ArticleDto.fromEntity(article);
}
```

`create` 메서드의 결과로 DTO 클래스를 반환하여, 생성 이후에 만들어진
Entity를 확인할 수 있는 페이지로 이동할 수 있도록 하였습니다. `save()`의 결과로
만들어진 Entity를 활용합니다.

```java
public ArticleDto create(ArticleDto dto) {
    Article article = new Article(
            dto.getTitle(), dto.getContent(), dto.getWriter()
    );
    return ArticleDto.fromEntity(repository.save(article));
}
```
