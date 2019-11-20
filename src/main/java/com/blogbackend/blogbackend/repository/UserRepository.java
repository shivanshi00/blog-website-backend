package com.blogbackend.blogbackend.repository;

import com.blogbackend.blogbackend.modals.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users,Long> {
    Users findByUserid(int userid);
    Optional<Users> findByUsername(String username);
    List<Users> findByStatus(String s);
}
