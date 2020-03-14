package com.hao.movieshareback.dao;

import com.hao.movieshareback.model.Category;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CategoryMapper {
    List<Category> selectAllCategory();
    Category selectCategoryById(Integer categoryId);
    Category selectCategoryByName(String categoryName);
}
