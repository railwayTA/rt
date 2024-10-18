package com.pard.hw4.user.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.pard.hw4.book.dto.BookReadDto;
import com.pard.hw4.user.entity.UserLoan;
import lombok.Getter;
import lombok.Setter;


public class UserLoanDto {
    @Getter
    @Setter
    public static class Create {
        private Long userId;
        private Long bookId;
        private boolean isReturn;
    }

    @Getter
    @Setter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Read{
        private Long id;
        private boolean isReturn;
        private UserDto.Read userDto;
        private BookReadDto bookDto;

        public Read(UserLoan userLoanHistory, UserDto.Read userDto, BookReadDto bookDto){
            this.id = userLoanHistory.getId();
            this.bookDto = bookDto;
            this.userDto = userDto;
            this.isReturn = userLoanHistory.isReturn();
        }

    }

}
