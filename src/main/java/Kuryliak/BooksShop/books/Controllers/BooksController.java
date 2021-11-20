package Kuryliak.BooksShop.books.Controllers;

import Kuryliak.BooksShop.books.Model.Books;
import Kuryliak.BooksShop.books.Repository.BooksRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@EnableWebSecurity
@RequestMapping("/book")
public class BooksController {


    @Autowired
    private final BooksRepos booksRepos;

    public BooksController(BooksRepos booksRepos) {
        this.booksRepos = booksRepos;
    }

    @GetMapping
    public String showBooks(Model model) {
        Iterable<Books> booksList = booksRepos.findAll();
        model.addAttribute("books", booksList);
        return "homePage";
    }

    @PostMapping
    public String createBook(@RequestParam String bookName,
                             @RequestParam String author,
                             @RequestParam String description,
                             @RequestParam double price, Model model) {
        Books books = new Books(author, bookName, description, price);
        booksRepos.save(books);
        Iterable<Books> booksIterable = booksRepos.findAll();
        model.addAttribute("books", booksIterable);
        return "homePage";
    }

    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable("id") Long id) {
        booksRepos.deleteById(id);
        return "redirect:/books";
    }

    @PostMapping("/{id}/edit")
    public String editBook(@RequestParam("id") Long id,
                           @RequestParam String bookName,
                           @RequestParam String author,
                           @RequestParam String description,
                           @RequestParam double price, Books books1
            , Model model) {

        books1.setBookName(bookName);
        books1.setAuthor(author);
        books1.setDescription(description);
        books1.setPrice(price);
        booksRepos.save(books1);
        Iterable<Books> books = booksRepos.findAll();
        model.addAttribute("books", books);
        return "editPage";
    }

}
