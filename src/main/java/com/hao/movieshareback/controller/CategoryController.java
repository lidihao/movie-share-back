package com.hao.movieshareback.controller;

import com.hao.movieshareback.model.Category;
import com.hao.movieshareback.service.CategoryService;
import com.hao.movieshareback.vo.ResultBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/category")
@RestController
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    public ResultBody getAllCategory(){
        return ResultBody.success(categoryService.listCategory());
    }
}
