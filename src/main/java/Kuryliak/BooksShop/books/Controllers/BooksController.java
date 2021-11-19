package Kuryliak.BooksShop.books.Controllers;

import Kuryliak.BooksShop.books.Model.Books;
import Kuryliak.BooksShop.books.Repository.BooksRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/book")
public class BooksController {


    @Autowired
    private final BooksRepos booksRepos;

    public BooksController(BooksRepos booksRepos) {
        this.booksRepos = booksRepos;
    }

    @GetMapping
    public String showBooks(Map<String,Object> model) {
        Iterable<Books> booksList = booksRepos.findAll();
        model.put("books", booksList);
        return "homePage";
    }
}
