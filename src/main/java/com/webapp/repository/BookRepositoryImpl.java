package com.webapp.repository;

import com.webapp.model.Book;
import com.webapp.http.exception.BookNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@Component
public class BookRepositoryImpl {

    @Autowired
    BookRepository _bookRepository;


    public Book add(Book book){

        _bookRepository.save(book);

        return book;

    }


    public List<Book> getAll(){

         return _bookRepository.findByDeletedAtIsNull();

    }

    public Book get(Integer bookId){

        Optional<Book> _optional =_bookRepository.findById(bookId);

        _optional.orElseThrow(() -> new BookNotFoundException("Couldn't find a Book with id: " + bookId));


        return _optional.get();

    }


    public Book update(Book book){

        Optional<Book> _optional =_bookRepository.findById(book.getBookId());

        _optional.orElseThrow(() -> new BookNotFoundException("Couldn't find a Book with id: " + book.getBookId()));

        _bookRepository.save(book);

        return book;

    }

    public void delete(Integer bookId){

        Optional<Book> _optional =_bookRepository.findById(bookId);

        _optional.orElseThrow(() -> new BookNotFoundException("Couldn't find a Book with id: " + bookId));

        Book book = _optional.get();

        book.setDeletedAt(new Date());

        _bookRepository.save(book);

    }
}
