drop table if exists booktopics ;

create table booktopics
(
    id          INT         NOT NULL AUTO_INCREMENT,
    booktopicid     VARCHAR(36) NOT NULL UNIQUE,
    booktopicname VARCHAR (255) NOT NULL,
    imageurl   VARCHAR(255),
    PRIMARY KEY (id)
);

drop table if exists books;

create table books
(
    id            INT          NOT NULL AUTO_INCREMENT,
    bookid        VARCHAR(36)  NOT NULL UNIQUE,
    title         VARCHAR(255) NOT NULL,
    publishedyear INT          NOT NULL,
    coverurl      VARCHAR(255),
    booktopicid   VARCHAR(36)  NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (booktopicid) REFERENCES booktopics(booktopicid)
);