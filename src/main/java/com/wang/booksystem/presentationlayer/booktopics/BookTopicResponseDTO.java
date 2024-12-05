package com.wang.booksystem.presentationlayer.booktopics;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BookTopicResponseDTO {

    private String bookTopicID;

    private String bookTopicName;

    private String imageURL;
}
