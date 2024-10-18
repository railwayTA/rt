package com.pard.hw4.book.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.pard.hw4.book.entity.Book;
import com.pard.hw4.user.dto.UserDto;
import com.pard.hw4.user.dto.UserLoanDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookReadDto {
    private boolean loan;
    private Long bookId;
    private String name;
    private UserDto.Read user;

    public BookReadDto(Book book){
        this.bookId = book.getBookId();
        this.name = book.getName();
        this.loan = book.isLoaned();
    }

    public BookReadDto(Book book, UserDto.Read user){
        this.bookId = book.getBookId();
        this.name = book.getName();
        this.user = user;
    }
    public BookReadDto(Book book, UserLoanDto.Read dto){
        this.bookId = book.getBookId();
    }
}
