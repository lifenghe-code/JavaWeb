package com.blog.user.pojo;
import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Table(name = "article")
public class Article implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;
    @Column(name = "Title")
    private String title;
    @Column(name = "Content")
    private String content;

    @Column(name = "Date")
    private Timestamp date;

    @Column(name = "AuthorName")
    private String authorName;
    @Column(name = "AuthorId")
    private Long authorId;
    @Column(name = "Comments")
    private String comments;
    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public String getComments() {
        return comments;
    }
    public void setComments(String comments) {
        this.comments = comments;
    }
    public void setId(Long articleId) {
        this.id = articleId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setTime(Timestamp time) {
        this.date = time;
    }

    public Long getId() {
        return this.id;
    }

    public String getContent() {
        return content;
    }

    public String getTitle() {
        return title;
    }

    public Timestamp getDate() {
        return date;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

}
