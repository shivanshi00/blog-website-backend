package com.blogbackend.blogbackend.repository;


import com.blogbackend.blogbackend.modals.Blog;
import com.blogbackend.blogbackend.modals.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<Blog,Integer> {

    List<Blog> findById(int blogid);
    List<Blog> findByUser(Users user);
    Blog findByBlogid(int blogid);
    List<Blog> findByStatus(String s);

}
