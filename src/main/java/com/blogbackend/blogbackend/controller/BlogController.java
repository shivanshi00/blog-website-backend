package com.blogbackend.blogbackend.controller;

        import com.blogbackend.blogbackend.modals.Blog;
        import com.blogbackend.blogbackend.service.BlogService;
        import com.blogbackend.blogbackend.service.UserService;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.web.bind.annotation.*;

        import javax.validation.Valid;
        import java.security.Principal;
        import java.util.List;
        import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/myblogs")
public class BlogController {
    @Autowired
    BlogService blogService;
    @Autowired
    UserService userService;

    @PostMapping(value="/addblog")
    @ResponseBody
    public Blog addblog(@RequestBody Blog blog, Principal principal)
    {
        return blogService.addblog(blog,userService.getUserId(principal));
    }


    @RequestMapping(value="/showall",method= RequestMethod.GET)
    @ResponseBody
    public List<Blog> showblogs()
    {

        return blogService.getBlogList();
    }


    @GetMapping(value="/getblogById/{blogid}")
    public List<Blog> getBlogById(@PathVariable("blogid")int id){
        return blogService.getBlogById(id);
    }

    @GetMapping(value="/getBlogByCurrentUser")
    public List<Blog> getBlogByCurrentUser(Principal principle){
        int id= userService.getUserId(principle);
        return blogService.getBlogByUserId(id);
    }

    @PutMapping("/update/{blogid}")
    public Blog update(@Valid @RequestBody Blog blog){
        blogService.update(blog);

        return blog;
    }

    @RequestMapping(value="/deleteblog/{blogid}",method= RequestMethod.GET)
    @ResponseBody
    public List<Blog> deleteblog(@PathVariable("blogid") int blogid, Principal principal)
    {
        return blogService.deleteblog(userService.getUserId(principal),blogid);
    }

    @GetMapping(path = "search/{keyword}", produces = "application/json")
    public List<Blog> getSearchResult(@PathVariable("keyword") String keyword) {
        return blogService.searchResult(keyword);
    }

    @RequestMapping(value="/getpublicBlogs",method= RequestMethod.GET)
    @ResponseBody
    public List<Blog> getpublicBlogs()
    {

        return blogService.getpublicBlogs();
    }


}
