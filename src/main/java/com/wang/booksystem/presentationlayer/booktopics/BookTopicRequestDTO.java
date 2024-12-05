package com.wang.booksystem.presentationlayer.booktopics;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BookTopicRequestDTO {

    private String bookTopicName;

    private String imageURL;

}
