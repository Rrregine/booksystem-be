package com.wang.booksystem.presentationlayer.booktopicbook;

import com.wang.booksystem.businesslayer.BookTopicBookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping("/api/v1/booktopics/{bookTopicid}/books")
public class BookTopicBookController {

    private BookTopicBookService bookTopicBookService;

    public BookTopicBookController(BookTopicBookService bookTopicBookService) {
        this.bookTopicBookService = bookTopicBookService;
    }

    @GetMapping()
    public ResponseEntity<BookTopicBookResponseDTO> getAllBooksForBookTopic(@PathVariable String bookTopicid){
        return ResponseEntity.status(HttpStatus.OK).body(bookTopicBookService.getAllBooksByBookTopicId(bookTopicid));
    }

}
