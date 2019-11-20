package com.blogbackend.blogbackend.service;



        import com.blogbackend.blogbackend.modals.Blog;
        import com.blogbackend.blogbackend.modals.Comments;
        import com.blogbackend.blogbackend.modals.Users;
        import com.blogbackend.blogbackend.repository.BlogRepository;
        import com.blogbackend.blogbackend.repository.CommentRepository;
        import com.blogbackend.blogbackend.repository.UserRepository;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Service;

        import java.util.List;

@Service

public class CommentService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private com.blogbackend.blogbackend.repository.BlogRepository BlogRepository;
    @Autowired
    private com.blogbackend.blogbackend.repository.CommentRepository CommentRepository;

    public Comments addcomment(Comments comments, int blogid, int  userid) {
        Users user = userRepository.findByUserid(userid);
        Blog blog =BlogRepository.findByBlogid(blogid);
        comments.setUser(user);
        comments.setBlog(blog);
        return CommentRepository.save(comments);
    }

    public List<Comments> getList(){
        return CommentRepository.findAll();

    }

    public List<Comments> getbyblog(int id){
        Blog b=BlogRepository.findByBlogid(id);
        return CommentRepository.findByBlog(b);
    }

    public List<Comments> deletecomment(int commentid)
    {
        Comments comment =CommentRepository.findById(commentid);
        Blog b=comment.getBlog();
        CommentRepository.delete(comment);
        return CommentRepository.findByBlog(b);

    }

}
