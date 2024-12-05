package com.wang.booksystem.dataaccesslayer;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookTopicRepository extends JpaRepository <BookTopic, Integer> {

    BookTopic findBookTopicByBookTopicID(String bookTopicId);

}
