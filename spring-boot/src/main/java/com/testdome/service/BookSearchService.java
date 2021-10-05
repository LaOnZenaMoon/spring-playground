package com.testdome.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookSearchService {

    @Autowired
    public BookRepository bookRepository;

    public boolean bookExists(String bookName) {
        return bookRepository.getBooks().contains(bookName);
    }
}
