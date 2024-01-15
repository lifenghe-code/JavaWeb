package com.blog.user.pojo;
import javax.persistence.Table;
import javax.persistence.*;
import java.io.Serializable;
@Table(name = "usertable")
public class User implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;
    @Column(name = "Name")
    private String name;
    @Column(name = "PassWord")
    private String passWord;
    @Column(name = "ArticleLibId")
    private long articleLibId; //用户文章仓库ID号

    public void setId(Long Id) {
        this.id = Id;
    }
    public void setName(String Name) {
        this.name = Name;
    }
    public void setPassWord(String PassWord) {
        this.passWord = PassWord;
    }
    public long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getPassWord() {
        return passWord;
    }

    public void setArticleLibId(Long articleLibId) {
        this.articleLibId = articleLibId;
    }

    public long getArticleLibId() {
        return articleLibId;
    }
}
