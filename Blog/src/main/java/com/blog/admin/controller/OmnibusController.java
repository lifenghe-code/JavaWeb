package com.blog.admin.controller;
import com.alibaba.fastjson.JSONObject;
import com.blog.admin.pojo.Omnibus;
import com.blog.admin.service.OmnibusService;
import com.blog.user.entity.Result;
import com.blog.user.entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/omnibus")
public class OmnibusController {
    @Autowired
    private OmnibusService omnibusService;
    @PostMapping(value = "/showall")
    public Result showall() {
        Integer userNumber = omnibusService.countUser();
        Integer articleNumber =  omnibusService.countArticle();
        JSONObject data = new JSONObject();
        data.put("userNumber",userNumber);
        data.put("articleNumber",articleNumber);
        return new Result<List<Omnibus>>(true, StatusCode.OK, "查询成功", data);
    }
}
