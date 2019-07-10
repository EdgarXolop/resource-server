package com.webapp.service;

import com.webapp.model.Book;
import com.webapp.model.BookDTO;
import com.webapp.repository.BookRepositoryImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepositoryImpl bookRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public BookDTO addBook(Book book) {

        bookRepository.add(book);

        return convertToBookDTO(book);
    }

    @Override
    public BookDTO findBook(Integer id) {

        Book book = bookRepository.get(id);

        return convertToBookDTO(book);
    }

    @Override
    public BookDTO updateBook(Book book) {

        bookRepository.update(book);

        return convertToBookDTO(book);
    }

    @Override
    public void removeBook(Integer id) {

        bookRepository.delete(id);
    }

    @Override
    public List<BookDTO> getPage(Integer page, Integer size) {

        List<Book> listBook = bookRepository.getAll();

        return listBook.stream().map(book -> convertToBookDTO(book)).collect(Collectors.toList());
    }

    private BookDTO convertToBookDTO(Book book) {

        BookDTO bookDTO = modelMapper.map(book,BookDTO.class);

        return bookDTO;
    }
}
