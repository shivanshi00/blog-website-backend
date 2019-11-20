package com.blogbackend.blogbackend.controller;

import com.blogbackend.blogbackend.modals.Users;
import com.blogbackend.blogbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api")
@CrossOrigin( origins = "http://localhost:4200", methods = { RequestMethod.PUT , RequestMethod.GET , RequestMethod.POST } )
public class UserLoginController {
    @Autowired
    com.blogbackend.blogbackend.repository.UserRepository UserRepository;

    @GetMapping("/validateUser")
    public String validateuser()
    {
        return "\"user validated\"";
    }
    @PostMapping(path="/addUsers", produces="application/json")
    public Users addusers(@Valid @RequestBody Users user )
    {
        return UserRepository.save(user);
    }
    @GetMapping("/getUsers")
    public List<Users> getusers()
    {
        return UserRepository.findAll();
    }


}
