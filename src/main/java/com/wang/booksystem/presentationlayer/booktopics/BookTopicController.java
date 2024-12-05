package com.wang.booksystem.presentationlayer.booktopics;

import com.wang.booksystem.businesslayer.BookTopicService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping("/api/v1/booktopics")
public class BookTopicController {

    private BookTopicService bookTopicService;

    public BookTopicController(BookTopicService bookTopicService){
        this.bookTopicService = bookTopicService;
    }

    @GetMapping()
    public ResponseEntity<List<BookTopicResponseDTO>> getBookTopics(){
        return ResponseEntity.status(HttpStatus.OK).body(bookTopicService.getAllBookTopics());
    }

    @GetMapping("/{booktopicId}")
    public ResponseEntity<BookTopicResponseDTO> getBookTopicByBookTopicId(@PathVariable String booktopicId){
        return ResponseEntity.status(HttpStatus.OK).body(bookTopicService.getOneBookTopic(booktopicId));
    }

    @PostMapping()
    public ResponseEntity<BookTopicResponseDTO> addBookTopic(@RequestBody BookTopicRequestDTO bookTopicRequestDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(bookTopicService.addBookTopic(bookTopicRequestDTO));
    }

    @PutMapping("/{booktopicId}")
    public ResponseEntity<BookTopicResponseDTO> updateBookTopic(@RequestBody BookTopicRequestDTO bookTopicRequestDTO,
                                              @PathVariable String booktopicId){
        return ResponseEntity.status(HttpStatus.OK).body(bookTopicService.updateBookTopic(bookTopicRequestDTO, booktopicId));
    }

    @DeleteMapping("/{booktopicId}")
    public ResponseEntity<Void> deleteBookTopic(@PathVariable String booktopicId){
        bookTopicService.deleteBookTopic(booktopicId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

}
