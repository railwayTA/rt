package com.pard.hw4.book.controller;

import com.pard.hw4.book.dto.BookCreateDto;
import com.pard.hw4.book.dto.BookReadDto;
import com.pard.hw4.book.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/book")
public class BookController {
    private final BookService bookService;

    @PostMapping("")
    public String createBook(@RequestBody BookCreateDto dto){
        bookService.createBook(dto);
        return  "추가됨";
    }

    @GetMapping("")
    public List<BookReadDto> findAll(){
        return bookService.findAll();
    }

    @DeleteMapping("/{id}")
    public List<BookReadDto> deleteById(@PathVariable Long id) {
        return bookService.deleteById(id);
    }

}
