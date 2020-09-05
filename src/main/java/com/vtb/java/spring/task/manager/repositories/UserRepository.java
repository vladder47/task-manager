package com.vtb.java.spring.task.manager.repositories;

import com.vtb.java.spring.task.manager.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
