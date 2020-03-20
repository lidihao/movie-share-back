package com.hao.movieshareback.dao;

import com.hao.movieshareback.model.Log;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface LogMapper {
    void save(Log log);
}
