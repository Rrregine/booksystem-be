package com.wang.booksystem.businesslayer;

import com.wang.booksystem.dataaccesslayer.Book;
import com.wang.booksystem.dataaccesslayer.BookRepository;
import com.wang.booksystem.dataaccesslayer.BookTopic;
import com.wang.booksystem.dataaccesslayer.BookTopicRepository;
import com.wang.booksystem.presentationlayer.books.BookResponseDTO;
import com.wang.booksystem.presentationlayer.booktopicbook.BookTopicBookResponseDTO;
import com.wang.booksystem.presentationlayer.booktopics.BookTopicResponseDTO;
import com.wang.booksystem.utils.exceptions.NotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookTopicBookServiceImpl implements BookTopicBookService{

    private BookTopicRepository bookTopicRepository;

    private BookRepository bookRepository;

    @Autowired
    public BookTopicBookServiceImpl (BookTopicRepository bookTopicRepository, BookRepository bookRepository){
        this.bookTopicRepository = bookTopicRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public BookTopicBookResponseDTO getAllBooksByBookTopicId(String bookTopicId) {

        BookTopic foundBookTopic = bookTopicRepository.findBookTopicByBookTopicID(bookTopicId);

        if (foundBookTopic == null){
            throw new NotFoundException("Unknown bookTopicId : " + bookTopicId);
        }

        BookTopicBookResponseDTO bookTopicBookResponseDTO = new BookTopicBookResponseDTO();
        BeanUtils.copyProperties(foundBookTopic, bookTopicBookResponseDTO);

        List<Book> bookList = bookRepository.findBooksByBookTopic_BookTopicID(bookTopicId);

        List<BookResponseDTO> bookResponseDTOList = new ArrayList<>();

        for(Book book : bookList){

            BookResponseDTO bookResponseDTO = new BookResponseDTO();
            BeanUtils.copyProperties(book, bookResponseDTO);

            BookTopicResponseDTO bookTopicResponseDTO = new BookTopicResponseDTO();
            BeanUtils.copyProperties(book.getBookTopic(), bookTopicResponseDTO);

            bookResponseDTO.setBookTopic(bookTopicResponseDTO);
            bookResponseDTOList.add(bookResponseDTO);
        }

        bookTopicBookResponseDTO.setBooks(bookResponseDTOList);

        return bookTopicBookResponseDTO;
    }
}
