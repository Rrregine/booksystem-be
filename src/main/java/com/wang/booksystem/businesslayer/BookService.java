package com.wang.booksystem.businesslayer;

import com.wang.booksystem.presentationlayer.books.BookRequestDTO;
import com.wang.booksystem.presentationlayer.books.BookResponseDTO;

import java.util.List;

public interface BookService {
    List<BookResponseDTO> getAllBooks();

    BookResponseDTO getOneBook(String id);

    BookResponseDTO addBook(BookRequestDTO bookRequestDTO);

    BookResponseDTO updateBook(BookRequestDTO bookRequestDTO, String bookId);

    void deleteBook(String bookId);
}
