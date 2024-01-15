package com.blog.user.pojo;
import java.io.Serializable;
import java.util.Vector;
import javax.persistence.*;

@Table(name = "articlelib")
public class ArticleLib implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private long id;
    @Column(name = "ArticleId")
    private String ids = "";

    public void setArticleLibId(long articleLibId) {
        this.id = articleLibId;
    }

    public long getId() {
        return this.id;
    }

    public void addArticleId(Long articleId) {
        String tmp = String.valueOf(articleId);
        if(this.ids.isEmpty()){
            this.ids  += tmp;
        }
        else {
            this.ids = this.ids + "," + tmp;
        }
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    public String getArticleIds() {
        return this.ids;
    }
}
