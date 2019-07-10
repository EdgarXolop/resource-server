package com.webapp.controller;

import com.webapp.model.Book;
import com.webapp.model.BookDTO;
import com.webapp.service.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    public BookServiceImpl _bookService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<BookDTO>> index(){

       return new ResponseEntity<>(_bookService.getPage(0,0), HttpStatus.OK);
    }


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookDTO> postBook(@RequestBody Book book) {

       return new ResponseEntity<>(_bookService.addBook(book),HttpStatus.ACCEPTED);

    }

    @GetMapping(path = "/{id}", produces =MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookDTO> getById(@PathVariable(required = true) Integer id) {

        return  new ResponseEntity<>(_bookService.findBook(id),HttpStatus.OK);
    }


    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookDTO> putBook(@RequestBody Book book) {

        return new ResponseEntity<>(_bookService.updateBook(book),HttpStatus.OK);

    }

    @DeleteMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> delete(@PathVariable(required = true) Integer id) {

        _bookService.removeBook(id);

        return new ResponseEntity<>("",HttpStatus.OK);
    }
}