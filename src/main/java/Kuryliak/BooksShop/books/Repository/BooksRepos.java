package Kuryliak.BooksShop.books.Repository;

import Kuryliak.BooksShop.books.Model.Books;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BooksRepos extends JpaRepository<Books,Long> {
}
