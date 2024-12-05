package com.wang.booksystem.dataaccesslayer;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "booktopics")
@Data
@NoArgsConstructor
public class BookTopic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; //database id

    @Column(name = "booktopicid")
    private String bookTopicID; //public id

    @Column(name = "booktopicname")
    private String BookTopicName;

    @Column(name = "imageurl")
    private String imageURL;

    @OneToMany(mappedBy = "bookTopic")
    private Set<Book> book;
}

