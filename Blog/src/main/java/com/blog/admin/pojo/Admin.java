package com.blog.admin.pojo;

import javax.persistence.*;
import java.io.Serializable;
@Table(name = "admin")
public class Admin implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;
    @Column(name = "Name")
    private String name;
    @Column(name = "PassWord")
    private String passWord;
    @Column(name = "Type")//true代表普通用户
    private Boolean type = false;
    public Admin(){

    }
    public Admin(Long id, String name, String passWord, Boolean type) {
        this.id = id;
        this.name = name;
        this.passWord = passWord;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public Boolean getType() {
        return type;
    }

    public void setType(Boolean type) {
        this.type = type;
    }
}
