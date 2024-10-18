package com.pard.hw4.user.service;

import com.pard.hw4.book.dto.BookReadDto;
import com.pard.hw4.book.entity.Book;
import com.pard.hw4.book.repo.BookRepo;
import com.pard.hw4.user.dto.UserDto;
import com.pard.hw4.user.dto.UserLoanDto;
import com.pard.hw4.user.entity.UserLoan;
import com.pard.hw4.user.repo.UserLoanRepo;
import com.pard.hw4.user.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserLoanService {
    private final UserRepo userRepo;
    private final BookRepo bookRepo;
    private final UserLoanRepo userLoanRepo;

    public void createLoan(UserLoanDto.Create dto){
        userLoanRepo.save(UserLoan.toEntity(dto,
                userRepo.findById(dto.getUserId()).orElseThrow(),
                bookRepo.findById(dto.getBookId()).orElseThrow()));
    }

    public List<UserLoanDto.Read> findAll(){
        return userLoanRepo.findAll()
                .stream()
                .map(userLoan -> new UserLoanDto.Read(userLoan,
                        new UserDto.Read(userLoan.getUser()),
                        new BookReadDto(userLoan.getBook())))
                .collect(Collectors.toList());
    }

    public UserLoanDto.Read findById(Long id){
        UserLoan userLoan = userLoanRepo.findById(id).orElseThrow();
        return new UserLoanDto.Read(userLoan,
                new UserDto.Read(userLoan.getUser()),
                new BookReadDto(userLoan.getBook()));
    }

    public boolean isBookReturned(UserLoanDto.Create dto) {
        Optional<Book> optionalBook = bookRepo.findById(dto.getBookId());
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            return !book.isLoaned();
        }
        return false;
    }

    public String requestLoan(UserLoanDto.Create dto){
        if (isBookReturned(dto)){
            createLoan(dto);
            changeBookState(dto.getBookId(),true);
            return "대여 성공";
        }
        return "대여 실패";
    }
    @Transactional
    public String requestReturn(Long loanId) {
        UserLoan userLoan = userLoanRepo.findById(loanId)
                .orElseThrow(() -> new IllegalArgumentException("대출 기록 없음"));

        if (userLoan.isReturn()) {
            return "이미 반납된 도서입니다.";
        }

        userLoan.change(true);
        userLoanRepo.save(userLoan);
        Book book = userLoan.getBook();
        book.change(false);
        bookRepo.save(book);

        return "반납 성공";
    }


    public void changeBookState(Long bookId, boolean loaned){
        Book book = bookRepo.findById(bookId).orElseThrow();
        book.change(loaned);
        bookRepo.save(book);
    }

}
