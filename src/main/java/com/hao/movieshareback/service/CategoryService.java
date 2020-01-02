package com.hao.movieshareback.service;

import com.hao.movieshareback.dao.CategoryMapper;
import com.hao.movieshareback.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    public List<Category> listCategory(){
        return categoryMapper.selectAllCategory();
    }
}
