package com.wang.booksystem.presentationlayer.books;

import com.wang.booksystem.businesslayer.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping("/api/v1/books")
public class BookController {

    private BookService bookService;

    public BookController(BookService bookService){
        this.bookService = bookService;
    }

    @GetMapping()
    public ResponseEntity<List<BookResponseDTO>> getBooks(){
        return ResponseEntity.status(HttpStatus.OK).body(bookService.getAllBooks());
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<BookResponseDTO> getBookByBookId(@PathVariable String bookId){
        return ResponseEntity.status(HttpStatus.OK).body(bookService.getOneBook(bookId));
    }

    @PostMapping()
    public ResponseEntity<BookResponseDTO> addBook(@RequestBody BookRequestDTO bookRequestDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.addBook(bookRequestDTO));
    }

    @PutMapping("/{bookId}")
    public ResponseEntity<BookResponseDTO> updateBook(@RequestBody BookRequestDTO bookRequestDTO,
                                      @PathVariable String bookId){
        return ResponseEntity.status(HttpStatus.OK).body(bookService.updateBook(bookRequestDTO,bookId));
    }

    @DeleteMapping("/{bookId}")
    public ResponseEntity<Void> deleteBook(@PathVariable String bookId){
        bookService.deleteBook(bookId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
