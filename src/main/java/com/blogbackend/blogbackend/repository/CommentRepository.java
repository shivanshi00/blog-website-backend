package com.blogbackend.blogbackend.repository;





        import com.blogbackend.blogbackend.modals.Comments;
        import com.blogbackend.blogbackend.modals.Blog;
        import org.springframework.data.jpa.repository.JpaRepository;
        import org.springframework.stereotype.Repository;

        import java.util.List;

@Repository
public interface CommentRepository  extends JpaRepository<Comments, Integer> {
    Comments findById(int commentid);
    List<Comments> findByBlog(Blog blog);
}

