package com.example.bai2.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.bai2.models.Book;

@Service
public class BookService {
    private List<Book> books = new ArrayList<>(List.of(
            new Book(1, "To Kill a Mockingbird", "Harper Lee"),
            new Book(2, "1984", "George Orwell"),
            new Book(3, "The Great Gatsby", "F. Scott Fitzgerald")));
    private int nextId = books.size() + 1;

    public List<Book> getAllBooks() {
        return books;
    }

    public Optional<Book> getBookById(int id) {
        return books.stream()
                .filter(book -> book.getId() == id)
                .findFirst();
    }

    public void addBook(Book book) {
        book.setId(nextId++);
        books.add(book);
    }

    public void updateBook(Book updateBook) {
        books.stream()
                .filter(book -> book.getId() == updateBook.getId())
                .findFirst()
                .ifPresent(book -> {
                    book.setTitle(updateBook.getTitle());
                    book.setAuthor(updateBook.getAuthor());
                });
    }

    public void deleteBook(int id) {
        books.removeIf(book -> book.getId() == id);
    }
}
