package com.pard.hw4.book.service;

import com.pard.hw4.book.dto.BookCreateDto;
import com.pard.hw4.book.dto.BookReadDto;
import com.pard.hw4.book.entity.Book;
import com.pard.hw4.book.repo.BookRepo;
import com.pard.hw4.user.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepo bookRepo;

    public void createBook(BookCreateDto dto){
        bookRepo.save(Book.toEntity(dto));
    }

    public List<BookReadDto> findAll(){
        return bookRepo.findAll()
                .stream()
                .map(book -> new BookReadDto(book))
                .collect(Collectors.toList());
    }

    public List<BookReadDto> deleteById(Long id) {
        bookRepo.deleteById(id);
        return findAll();
    }
}
