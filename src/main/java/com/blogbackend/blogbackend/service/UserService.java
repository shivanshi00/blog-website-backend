package com.blogbackend.blogbackend.service;

import com.blogbackend.blogbackend.modals.Users;
import com.blogbackend.blogbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private com.blogbackend.blogbackend.repository.UserRepository UserRepository;
    public Optional<Users> CurrentUser(Principal prinicipal) {

        String username = prinicipal.getName();

        return UserRepository.findByUsername(username);
    }
    public int getUserId(Principal principal)
    {
        String username = principal.getName();
        int  id = UserRepository.findByUsername(username).get().getUserid();
        return id;
    }

    public Optional<Users> getUserProfile(Principal principal) {
        return UserRepository.findByUsername(principal.getName());
    }
    public List<Users> searchResult(String keyword) {
        List<Users> itemsList = UserRepository.findAll();
        List<Users> foundList = new ArrayList<>();

        for (Users items : itemsList) {
            if (items.getUsername() != null && items.getStatus() != null && (items.getUsername().
                    toLowerCase().contains(keyword.toLowerCase())

            )
            ) {
                foundList.add(items);
            }
        }
        return foundList;
    }

}
