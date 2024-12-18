package com.nishant.accounts.repo;

import com.nishant.accounts.entity.Students;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentsRepo extends JpaRepository<Students, String> {

    Optional<Students> findByMobileNumber(String mobileNumber);
}
