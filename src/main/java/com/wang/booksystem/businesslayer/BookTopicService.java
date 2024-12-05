package com.wang.booksystem.businesslayer;

import com.wang.booksystem.dataaccesslayer.BookTopic;
import com.wang.booksystem.presentationlayer.booktopics.BookTopicRequestDTO;
import com.wang.booksystem.presentationlayer.booktopics.BookTopicResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookTopicService {
    List<BookTopicResponseDTO> getAllBookTopics();

    BookTopicResponseDTO getOneBookTopic(String id);

    BookTopicResponseDTO addBookTopic(BookTopicRequestDTO bookTopicRequestDTO);

    BookTopicResponseDTO updateBookTopic(BookTopicRequestDTO bookTopicRequestDTO, String bookTopicId);

    void deleteBookTopic(String bookTopicId);
}
