package com.hao.movieshareback.dao;

import com.hao.movieshareback.model.Log;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface LogMapper {
    void save(Log log);
    List<Log> selectLogForRecommend(String startDate,String endDate);
}
