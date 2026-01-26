package com.example.bai2.services;


import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.bai2.models.Book;

@Service
public class BookService {
    private List<Book> books = new ArrayList<>(List.of(
        new Book(1,"To Kill a Mockingbird","Harper Lee"),
        new Book(2,"1984","George Orwell"),
        new Book(3,"The Great Gatsby","F. Scott Fitzgerald")
    ));

    public List<Book> getAllBooks() {
        return books;
    }

    public Book getBookById(int id) {
        return books.stream().filter(book -> book.getId() == id).findFirst().orElse(null);
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void updateBook(int id, Book book){
        books = books.stream().map(b -> {
            if(b.getId() == id) {
                return book;
            }
            return b;
        }).toList();
    }
    
    public void deleteBook(int id) {
        books = books.stream().filter(b -> b.getId() != id).toList();
    }

}
