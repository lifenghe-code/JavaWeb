package com.blog.admin.dao;
import com.blog.admin.pojo.Omnibus;
import tk.mybatis.mapper.common.Mapper;
@org.apache.ibatis.annotations.Mapper
public interface OmnibusMapper extends Mapper<Omnibus> {
    Integer countUser();
    Integer countArticle();
}
