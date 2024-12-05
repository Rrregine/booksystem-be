package com.wang.booksystem.presentationlayer.booktopicbook;

import com.wang.booksystem.presentationlayer.books.BookResponseDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class BookTopicBookResponseDTO {

    private String bookTopicID;

    private String BookTopicName;

    private String imageURL;

    private List<BookResponseDTO> books;
}

