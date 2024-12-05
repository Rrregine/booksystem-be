package com.wang.booksystem.presentationlayer.books;

import com.wang.booksystem.presentationlayer.booktopics.BookTopicResponseDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BookResponseDTO {

    private String bookID; //public id

    private String title;

    private Integer publishedYear;

    private String coverURL;

    private BookTopicResponseDTO bookTopic;
}