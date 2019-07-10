package com.webapp.service;

import com.webapp.model.Book;
import com.webapp.model.BookDTO;

import java.util.List;

public interface BookService {

    BookDTO addBook(Book book);
    BookDTO findBook(Integer id);
    BookDTO updateBook(Book book);
    void removeBook(Integer id);
    List<BookDTO> getPage(Integer page, Integer size);

}
