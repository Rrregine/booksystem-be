package com.wang.booksystem.businesslayer;

import com.wang.booksystem.dataaccesslayer.BookTopic;
import com.wang.booksystem.dataaccesslayer.BookTopicRepository;
import com.wang.booksystem.presentationlayer.booktopics.BookTopicRequestDTO;
import com.wang.booksystem.presentationlayer.booktopics.BookTopicResponseDTO;
import com.wang.booksystem.utils.exceptions.InUseException;
import com.wang.booksystem.utils.exceptions.NotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class BookTopicServiceImpl implements BookTopicService{
    private BookTopicRepository bookTopicRepository;

    @Autowired
    public BookTopicServiceImpl(BookTopicRepository bookTopicRepository){
        this.bookTopicRepository = bookTopicRepository;
    }
    @Override
    public List<BookTopicResponseDTO> getAllBookTopics(){

        List<BookTopic> bookTopicEntities = bookTopicRepository.findAll();

        //convert list of Movie entities to list of MovieResponseDTO
        List<BookTopicResponseDTO> bookTopicResponseDTOList = new ArrayList<>();

        for (BookTopic bookTopic : bookTopicEntities){

            BookTopicResponseDTO bookTopicResponseDTO = new BookTopicResponseDTO();

            BeanUtils.copyProperties(bookTopic, bookTopicResponseDTO);
            bookTopicResponseDTOList.add(bookTopicResponseDTO);
        }

        return bookTopicResponseDTOList;
    }

    @Override
    public BookTopicResponseDTO getOneBookTopic(String bookTopicId) {
        BookTopic oneBookTopic = bookTopicRepository.findBookTopicByBookTopicID(bookTopicId);
        if (oneBookTopic == null){
            throw new NotFoundException("Unknown bookTopicId : " + bookTopicId);
        }
        BookTopicResponseDTO bookTopicResponseDTO = new BookTopicResponseDTO();
        BeanUtils.copyProperties(oneBookTopic, bookTopicResponseDTO);

        return bookTopicResponseDTO;
    }

    @Override
    public BookTopicResponseDTO addBookTopic(BookTopicRequestDTO bookTopicRequestDTO) {

        //convert movieRequestDTO to an entity
        BookTopic bookTopic = new BookTopic();

        BeanUtils.copyProperties(bookTopicRequestDTO, bookTopic);

        bookTopic.setBookTopicID(UUID.randomUUID().toString());

        //save movie entity to database via repository
        BookTopic savedBookTopic = bookTopicRepository.save(bookTopic);

        //convert savedMovie(entity) to movieRequestDTO
        BookTopicResponseDTO bookTopicResponseDTO = new BookTopicResponseDTO();
        BeanUtils.copyProperties(savedBookTopic, bookTopicResponseDTO);

        return bookTopicResponseDTO;
    }

    @Override
    public BookTopicResponseDTO updateBookTopic(BookTopicRequestDTO bookTopicRequestDTO, String bookTopicId) {

        BookTopic foundBookTopic = bookTopicRepository.findBookTopicByBookTopicID(bookTopicId);

        if (foundBookTopic == null){
            throw new NotFoundException("Unknown directorId : " + bookTopicId);
        }

        //convert movieRequestDTO to an entity
        BookTopic bookTopic = new BookTopic();

        BeanUtils.copyProperties(bookTopicRequestDTO, bookTopic);

        bookTopic.setBookTopicID(foundBookTopic.getBookTopicID());
        bookTopic.setId(foundBookTopic.getId());

        //save movie entity to database via repository
        BookTopic savedBookTopic = bookTopicRepository.save(bookTopic);

        //convert savedMovie(entity) to movieRequestDTO
        BookTopicResponseDTO bookTopicResponseDTO = new BookTopicResponseDTO();
        BeanUtils.copyProperties(savedBookTopic, bookTopicResponseDTO);

        return bookTopicResponseDTO;
    }

    @Override
    public void deleteBookTopic(String bookTopicId) {
        BookTopic foundBookTopic = bookTopicRepository.findBookTopicByBookTopicID(bookTopicId);

        if (foundBookTopic == null){
            throw new NotFoundException("Unknown directorId : " + bookTopicId);
        }

        try{
            bookTopicRepository.delete(foundBookTopic);
        }
        catch (DataIntegrityViolationException ex){
            throw new InUseException("Cannot delete director with directorid: " + bookTopicId
                    + "as it is currently assigned to one or more movies.");
        }
    }
}
