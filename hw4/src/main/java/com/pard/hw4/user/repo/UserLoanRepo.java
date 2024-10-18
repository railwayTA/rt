package com.pard.hw4.user.repo;
import com.pard.hw4.user.entity.UserLoan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserLoanRepo extends JpaRepository<UserLoan, Long>{ }
