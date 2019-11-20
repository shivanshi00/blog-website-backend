package com.blogbackend.blogbackend.service;

import com.blogbackend.blogbackend.modals.Blog;
import com.blogbackend.blogbackend.modals.Users;
import com.blogbackend.blogbackend.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CurrentUser {
    @Autowired
    private com.blogbackend.blogbackend.repository.UserRepository UserRepository;

    @Autowired
    private com.blogbackend.blogbackend.repository.BlogRepository BlogRepository;


    @Autowired
    private com.blogbackend.blogbackend.service.BlogService BlogService;

    public Users getUserProfile(Principal principal) {
        Optional<Users> optional= UserRepository.findByUsername(principal.getName());
        return optional.get();
    }

    public  Users follow(int userid, Principal principal){
        Users user=UserRepository.findByUserid(userid);
        String name=principal.getName();
        int  principalid = UserRepository.findByUsername(name).get().getUserid();
        Users currentuser=UserRepository.findByUserid(principalid);
        ArrayList<Integer> arr=user.getFollowers();
        arr.add(principalid);
        user.setFollowers(arr);
        ArrayList<Integer> arr2=currentuser.getFollowing();
        arr2.add(userid);
        currentuser.setFollowing(arr2);
        UserRepository.save(user);
        UserRepository.save(currentuser);
        Optional<Users> optional= UserRepository.findByUsername(principal.getName());
        return optional.get();
    }

    public Users unfollow (int userid, Principal principal){
        Users user=UserRepository.findByUserid(userid);
        String name=principal.getName();
        int  principalid = UserRepository.findByUsername(name).get().getUserid();
        Users currentuser=UserRepository.findByUserid(principalid);
        ArrayList<Integer> arr=user.getFollowers();
        arr.remove(Integer.valueOf(principalid));
        user.setFollowers(arr);
        ArrayList<Integer> arr2=currentuser.getFollowing();
        arr2.remove(Integer.valueOf(userid));
        currentuser.setFollowing(arr2);
        UserRepository.save(user);
        UserRepository.save(currentuser);
        Optional<Users> optional= UserRepository.findByUsername(principal.getName());
        return optional.get();
    }

    public Users deletefollowing (int userid, Principal principal){
        Users user=UserRepository.findByUserid(userid);
        String name=principal.getName();
        int  principalid = UserRepository.findByUsername(name).get().getUserid();

        Users currentuser=UserRepository.findByUserid(principalid);
        ArrayList<Integer> arr=currentuser.getFollowing();
        arr.remove(Integer.valueOf(userid));
        currentuser.setFollowing(arr);
        ArrayList<Integer> arr2=user.getFollowers();
        arr2.remove(Integer.valueOf(principalid));
        user.setFollowers(arr2);
        UserRepository.save(user);
        UserRepository.save(currentuser);
        Optional<Users> optional= UserRepository.findByUsername(principal.getName());
        return optional.get();
    }

    public List<Blog> getblogsoffollowing(Principal principal){
        String name=principal.getName();
        int  principalid = UserRepository.findByUsername(name).get().getUserid();
        Users currentuser=UserRepository.findByUserid(principalid);
        ArrayList<Integer> arr=currentuser.getFollowing();
        ArrayList<Blog> list=new ArrayList<>();
        for(int i=0;i<arr.size();i++){
            Users user=UserRepository.findByUserid(arr.get(i));
            List<Blog> blog=BlogRepository.findByUser(user);
            list.addAll(blog);
        }
        List<Blog> publicblogs= BlogService.getpublicBlogs();
        list.addAll(publicblogs);
        return list;
    }


    public List<Users> getfollowers(Principal principal){
        String name=principal.getName();
        int  principalid = UserRepository.findByUsername(name).get().getUserid();
        Users currentuser=UserRepository.findByUserid(principalid);
        ArrayList<Integer> arr=currentuser.getFollowers();
        List<Users> list=new ArrayList<>();
        for(int i=0;i<arr.size();i++){
            Users user=UserRepository.findByUserid(arr.get(i));
            list.add(user);
        }
        return list;
    }

    public List<Users> getfollowing(Principal principal){
        String name=principal.getName();
        int  principalid = UserRepository.findByUsername(name).get().getUserid();
        Users currentuser=UserRepository.findByUserid(principalid);
        ArrayList<Integer> arr=currentuser.getFollowing();
        List<Users> list=new ArrayList<>();
        for(int i=0;i<arr.size();i++){
            Users user=UserRepository.findByUserid(arr.get(i));
            list.add(user);
        }
        return list;
    }


}
