package com.hao.movieshareback.dao;

import com.hao.movieshareback.model.task.JavaSystemTask;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface JavaSystemTaskMapper {
    void save(JavaSystemTask javaSystemTask);
    List<JavaSystemTask> selectTask();
    JavaSystemTask selectTaskByTaskId(Integer systemTaskId);
    void updateTask(JavaSystemTask javaSystemTask);
    void updateTaskStatus(String jobStatus,Integer systemTaskId);
    void deleteTaskByTaskId(Integer systemTaskId);
}