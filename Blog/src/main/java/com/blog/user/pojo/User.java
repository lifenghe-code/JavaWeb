package com.blog.user.pojo;
import javax.persistence.Table;
import javax.persistence.*;
import java.io.Serializable;
@Table(name = "user")
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
    @Column(name = "Status") //true代表可以正常登录
    private Boolean status  = true;
    @Column(name = "Type")//true代表普通用户
    private Boolean type = true;
    public void setId(Long Id) {
        this.id = Id;
    }
    public void setName(String Name) {
        this.name = Name;
    }

    public void setArticleLibId(long articleLibId) {
        this.articleLibId = articleLibId;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Boolean getType() {
        return type;
    }

    public void setType(Boolean type) {
        this.type = type;
    }

    public void setPassWord(String PassWord) {
        this.passWord = PassWord;
    }
    public Long getId() {
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
