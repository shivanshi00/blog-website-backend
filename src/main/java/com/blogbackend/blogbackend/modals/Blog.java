package com.blogbackend.blogbackend.modals;


import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "blogs")
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "blogid")
    private Integer blogid;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "body", columnDefinition = "TEXT")
    private String body;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date", nullable = false, updatable = false)
    @CreationTimestamp
    private Date createDate;



    @Column(name = "status")
    private String status;

    @ManyToOne
    @JoinColumn(name = "userid", referencedColumnName = "userid", nullable = false)
    private Users user;

//    @OneToMany(mappedBy = "followers", cascade = CascadeType.REMOVE)
//    private Collection<Users> followers;
//
//    @OneToMany(mappedBy = "following", cascade = CascadeType.REMOVE)
//    private Collection<Users> following;


    public Integer getId() {
        return blogid;
    }

    public void setId(Integer id) {
        this.blogid = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Integer getBlogid() {
        return blogid;
    }

    public void setBlogid(Integer blogid) {
        this.blogid = blogid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

//    public Collection<Users> getFollowers() {
//        return followers;
//    }
//
//    public void setFollowers(Collection<Users> followers) {
//        this.followers = followers;
//    }
//
//    public Collection<Users> getFollowing() {
//        return following;
//    }
//
//    public void setFollowing(Collection<Users> following) {
//        this.following = following;
//    }
}
