package com.wang.booksystem.dataaccesslayer;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "books")
@Data
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; //database id

    @Column(name = "bookid")
    private String bookID; //public id

    @Column(name = "title")
    private String title;

    @Column(name = "publishedyear")
    private Integer publishedYear;

    @Column(name = "coverurl")
    private String coverURL;

    @ManyToOne
    @JoinColumn(name = "booktopicid", referencedColumnName = "booktopicid")
    private BookTopic bookTopic;
}


