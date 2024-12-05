package com.wang.booksystem.presentationlayer.books;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BookRequestDTO {

    private String title;

    private String bookTopicId;

    private Integer publishedYear;

    private String coverURL;

}
