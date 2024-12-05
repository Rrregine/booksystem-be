package com.wang.booksystem.businesslayer;

import com.wang.booksystem.dataaccesslayer.Book;
import com.wang.booksystem.dataaccesslayer.BookRepository;
import com.wang.booksystem.dataaccesslayer.BookTopic;
import com.wang.booksystem.dataaccesslayer.BookTopicRepository;
import com.wang.booksystem.presentationlayer.books.BookRequestDTO;
import com.wang.booksystem.presentationlayer.books.BookResponseDTO;
import com.wang.booksystem.presentationlayer.booktopics.BookTopicResponseDTO;
import com.wang.booksystem.utils.exceptions.NotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class BookServiceImpl implements BookService{
    private BookRepository bookRepository;
    private BookTopicRepository bookTopicRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, BookTopicRepository bookTopicRepository){
        this.bookRepository = bookRepository;
        this.bookTopicRepository = bookTopicRepository;
    }

    @Override
    public List<BookResponseDTO> getAllBooks(){

        List<Book> bookEntities = bookRepository.findAll();

        //convert list of Movie entities to list of MovieResponseDTO
        List<BookResponseDTO> bookResponseDTOList = new ArrayList<>();

        for (Book book : bookEntities){

            BookResponseDTO bookResponseDTO = new BookResponseDTO();
            BeanUtils.copyProperties(book, bookResponseDTO);

            BookTopicResponseDTO bookTopicResponseDTO = new BookTopicResponseDTO();
            BeanUtils.copyProperties(book.getBookTopic(), bookTopicResponseDTO);
            bookResponseDTO.setBookTopic(bookTopicResponseDTO);

            bookResponseDTOList.add(bookResponseDTO);
        }

        return bookResponseDTOList;
    }

    @Override
    public BookResponseDTO getOneBook(String bookId) {

        Book oneBook = bookRepository.findBookByBookID(bookId);

        if (oneBook == null){
            throw new NotFoundException("Unknown bookId : " + bookId);
        }

        BookResponseDTO bookResponseDTO = new BookResponseDTO();
        BeanUtils.copyProperties(oneBook, bookResponseDTO);

        BookTopicResponseDTO bookTopicResponseDTO = new BookTopicResponseDTO();
        BeanUtils.copyProperties(oneBook.getBookTopic(), bookTopicResponseDTO);
        bookResponseDTO.setBookTopic(bookTopicResponseDTO);

        return bookResponseDTO;
    }

    @Override
    public BookResponseDTO addBook(BookRequestDTO bookRequestDTO) {

        BookTopic foundBookTopic = bookTopicRepository.findBookTopicByBookTopicID(bookRequestDTO.getBookTopicId());

        if (foundBookTopic == null){
            throw new NotFoundException("Unknown bookTopicId : " + bookRequestDTO.getBookTopicId());
        }

        //convert movieRequestDTO to an entity
        Book book = new Book();

        BeanUtils.copyProperties(bookRequestDTO, book);
        book.setBookID(UUID.randomUUID().toString());

        book.setBookTopic(foundBookTopic);

        //save movie entity to database via repository
        Book savedBook = bookRepository.save(book);

        //convert savedMovie(entity) to movieRequestDTO
        BookResponseDTO bookResponseDTO = new BookResponseDTO();
        BeanUtils.copyProperties(savedBook, bookResponseDTO);

        BookTopicResponseDTO bookTopicResponseDTO = new BookTopicResponseDTO();
        BeanUtils.copyProperties(savedBook.getBookTopic(), bookTopicResponseDTO);
        bookResponseDTO.setBookTopic(bookTopicResponseDTO);

        return bookResponseDTO;
    }

    @Override
    public BookResponseDTO updateBook(BookRequestDTO bookRequestDTO, String bookId) {

        Book foundBook = bookRepository.findBookByBookID(bookId);

        if (foundBook == null){
            throw new NotFoundException("Unknown bookId : " + bookId);
        }
//
        BookTopic bookTopic = bookTopicRepository.findBookTopicByBookTopicID(bookRequestDTO.getBookTopicId());

        //convert movieRequestDTO to an entity
        Book book = new Book();
        BeanUtils.copyProperties(bookRequestDTO, book);

        book.setBookID(foundBook.getBookID());
        book.setId(foundBook.getId());
        book.setBookTopic(bookTopic);

        //save movie entity to database via repository
        Book savedBook = bookRepository.save(book);

        //convert savedMovie(entity) to movieRequestDTO
        BookResponseDTO bookResponseDTO = new BookResponseDTO();
        BeanUtils.copyProperties(savedBook, bookResponseDTO);

        BookTopicResponseDTO bookTopicResponseDTO = new BookTopicResponseDTO();
        BeanUtils.copyProperties(savedBook.getBookTopic(), bookTopicResponseDTO);
        bookResponseDTO.setBookTopic(bookTopicResponseDTO);

        return bookResponseDTO;
    }

    @Override
    public void deleteBook(String bookId) {
        Book foundBook = bookRepository.findBookByBookID(bookId);

        if (foundBook == null){
            throw new NotFoundException("Unknown movieId : " + bookId);
        }

        bookRepository.delete(foundBook);
    }
}
