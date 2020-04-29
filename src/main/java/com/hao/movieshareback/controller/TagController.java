package com.hao.movieshareback.controller;

import com.hao.movieshareback.annotation.auth.AnonymousAccess;
import com.hao.movieshareback.service.TagService;
import com.hao.movieshareback.vo.ResultBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/tag")
@RestController
public class TagController {
    @Autowired
    private TagService tagService;

    @AnonymousAccess
    @GetMapping("/list")
    public ResultBody getAllTag(){
        return ResultBody.success(tagService.listTag());
    }

    @AnonymousAccess
    @GetMapping("/tagCountList")
    public ResultBody getTagCountList(){
        return ResultBody.success(tagService.getTagNameCountList());
    }
}
