package com.blogbackend.blogbackend.service;


import com.blogbackend.blogbackend.modals.Blog;
import com.blogbackend.blogbackend.modals.Users;
import com.blogbackend.blogbackend.repository.BlogRepository;
import com.blogbackend.blogbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Service
public class BlogService {
    @Autowired
    private com.blogbackend.blogbackend.repository.BlogRepository BlogRepository;


    @Autowired
    private UserRepository userRepository;


    public Blog addblog(Blog blog, int  userid) {
        Users user = userRepository.findByUserid(userid);
        blog.setUser(user);
        return BlogRepository.save(blog);
    }



    public List<Blog> getBlogList(){
        return BlogRepository.findAll();

    }

    public List<Blog> getBlogById(int id){
        return BlogRepository.findById(id);
    }

    public List<Blog> getBlogByUserId(int id){
        Users u=userRepository.findByUserid(id);
        return BlogRepository.findByUser(u);
    }

    public Blog update(Blog b){
        int blogid= b.getId();
        Blog blog=BlogRepository.findByBlogid(blogid);

        blog.setBody(b.getBody());
        blog.setTitle(b.getTitle());
        blog.setStatus(b.getStatus());
        blog.setCreateDate(b.getCreateDate());
        return BlogRepository.save(blog);
    }
    public List<Blog> deleteblog(int userid, int blogid)
    {
        Blog blog1=BlogRepository.findByBlogid(blogid);
        Users users=userRepository.findByUserid(userid);
        BlogRepository.delete(blog1);
        return BlogRepository.findByUser(users);

    }

    public List<Blog> getpublicBlogs(){
        return BlogRepository.findByStatus("public");

    }


    public List<Blog> searchResult(String keyword) {
        List<Blog> itemsList = BlogRepository.findAll();
        List<Blog> foundList = new ArrayList<>();

        for (Blog items : itemsList) {
            if (items.getTitle() != null && items.getBody() != null && (
                    items.getTitle().toLowerCase().contains(keyword.toLowerCase())
                            || items.getBody().toLowerCase().contains(keyword.toLowerCase())
//                    || items.getCreateDate().getDate().contains(keyword)

            )
            ) {
                foundList.add(items);
            }
        }
        return foundList;
    }
}
