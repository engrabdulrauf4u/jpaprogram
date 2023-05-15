package com.test.jpaprogram.repository;

import com.test.jpaprogram.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    public List<User> findAll();
}
