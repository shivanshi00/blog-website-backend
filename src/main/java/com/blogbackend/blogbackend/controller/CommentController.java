package com.blogbackend.blogbackend.controller;





        import com.blogbackend.blogbackend.modals.Comments;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Controller;
        import org.springframework.ui.Model;
        import org.springframework.validation.BindingResult;
        import org.springframework.web.bind.annotation.*;

        import javax.validation.Valid;
        import java.security.Principal;
        import java.util.List;
        import java.util.Optional;



@CrossOrigin("*")
@RestController
@RequestMapping("/comments")
public class CommentController {


    @Autowired
    com.blogbackend.blogbackend.service.BlogService blogService;
    @Autowired
    com.blogbackend.blogbackend.service.UserService userService;
    @Autowired
    com.blogbackend.blogbackend.service.CommentService commentService;

    @PostMapping(value="/addcomment/{blogid}")
    @ResponseBody
    public Comments addcomments(@Valid  @RequestBody Comments comment, @PathVariable("blogid")int id, Principal principal)
    {
        return commentService.addcomment(comment,id,userService.getUserId(principal));
    }

    @RequestMapping(value="/showall",method= RequestMethod.GET)
    @ResponseBody
    public List<Comments> showblogs()
    {

        return commentService.getList();
    }

    @GetMapping(value="/getbyblog/{blogid}")
    public List<Comments> getCommentByBlog(@PathVariable("blogid")int id){
        return commentService.getbyblog(id);
    }

    @RequestMapping(value="/deletecomment/{commentid}",method= RequestMethod.GET)
    @ResponseBody
    public List<Comments> deletecomment(@PathVariable("commentid") int commentid)
    {
        return commentService.deletecomment(commentid);
    }
}
