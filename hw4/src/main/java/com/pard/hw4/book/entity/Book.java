package com.pard.hw4.book.entity;

import com.pard.hw4.book.dto.BookCreateDto;
import com.pard.hw4.user.entity.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

@Builder
@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;

    private String name;




    @ColumnDefault("0")
    private boolean isLoaned;

    public static Book toEntity(BookCreateDto dto){
        return Book.builder()
                .name(dto.getName())
                .build();
    }
    public void change(boolean loaned){
        this.isLoaned = loaned;
    }
}
