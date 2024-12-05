package com.wang.booksystem.businesslayer;

import com.wang.booksystem.presentationlayer.booktopicbook.BookTopicBookResponseDTO;

public interface BookTopicBookService {
    BookTopicBookResponseDTO getAllBooksByBookTopicId(String bookTopicId);
}
