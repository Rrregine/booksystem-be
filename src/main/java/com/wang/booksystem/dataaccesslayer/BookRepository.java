package com.wang.booksystem.dataaccesslayer;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {
    Book findBookByBookID(String bookId);

    List<Book> findBooksByBookTopic_BookTopicID(String bookTopic_id);
}
