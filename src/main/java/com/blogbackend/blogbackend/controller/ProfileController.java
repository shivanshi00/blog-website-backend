package com.blogbackend.blogbackend.controller;

import com.blogbackend.blogbackend.modals.Blog;
import com.blogbackend.blogbackend.modals.Users;
import com.blogbackend.blogbackend.repository.UserRepository;
import com.blogbackend.blogbackend.service.CurrentUser;
import com.blogbackend.blogbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@CrossOrigin( origins = "http://localhost:4200", methods = { RequestMethod.PUT , RequestMethod.GET , RequestMethod.POST } )
@RestController
@RequestMapping("/profile")
public class ProfileController {
    @Autowired
    CurrentUser Currentuser;
    @Autowired
    com.blogbackend.blogbackend.repository.UserRepository UserRepository;

    @Autowired
    com.blogbackend.blogbackend.service.UserService UserService;

    @GetMapping("/get")
    public Users getdata(Principal principal){
        return Currentuser.getUserProfile(principal);
    }

    @PutMapping("/update")
    public Users update(@Valid @RequestBody Users users){
        users.setActive(1);
        UserRepository.save(users);
        return users;
    }

    @GetMapping("/follow/{userid}")
    public Users follows(@PathVariable("userid") int userid, Principal principal){
        Currentuser.follow(userid,principal);
        return Currentuser.getUserProfile(principal);
    }

    @GetMapping("/unfollow/{userid}")
    public Users unfollow(@PathVariable("userid") int userid, Principal principal){
        Currentuser.unfollow(userid,principal);
        return Currentuser.getUserProfile(principal);

    }

    @GetMapping("/get/{id}")
    public Users getdata(@PathVariable("id") int id){
        Users user=UserRepository.findByUserid(id);
        return user;

    }
    @GetMapping("/deletefollowing/{userid}")
    public Users deletefollowing(@PathVariable("userid") int userid, Principal principal){
        Currentuser.deletefollowing(userid,principal);
        return Currentuser.getUserProfile(principal);
    }
    @GetMapping("/getblogsoffollowing")
    public List<Blog> getblogs(Principal principal){
        return Currentuser.getblogsoffollowing(principal);
    }


    @GetMapping("/getfollowers")
    public List<Users> getfollowers(Principal principal){
        return Currentuser.getfollowers(principal);
    }

    @GetMapping("/getfollowing")
    public List<Users> getfollowing(Principal principal){
        return Currentuser.getfollowing(principal);
    }


    @GetMapping(path = "search/{keyword}")
    public List<Users> getSearchResult(@PathVariable("keyword") String keyword) {
        return UserService.searchResult(keyword);
    }
}
