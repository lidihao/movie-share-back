package com.hao.movieshareback.controller;

import com.hao.movieshareback.model.task.JavaSystemTask;
import com.hao.movieshareback.service.task.TaskService;
import com.hao.movieshareback.vo.ResultBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/task")
@RestController
public class TaskManagerController {

    @Autowired
    private TaskService taskService;


    @PreAuthorize("@el.check('admin')")
    @GetMapping("/listTask")
    public ResultBody listTask(){
        return ResultBody.success(taskService.getTaskList());
    }

    @PreAuthorize("@el.check('admin')")
    @PostMapping("/addTask")
    public ResultBody addTask(@RequestBody JavaSystemTask javaSystemTask){
        taskService.addJavaSystemTask(javaSystemTask);
        return ResultBody.success();
    }
    @PreAuthorize("@el.check('admin')")
    @GetMapping("/edit/{systemTaskId}")
    public ResultBody getTaskByTaskId(@PathVariable("systemTaskId") Integer systemTaskId){
        return ResultBody.success(taskService.getTaskById(systemTaskId));
    }

    @PreAuthorize("@el.check('admin')")
    @PostMapping("/updateTask")
    public ResultBody updateTaskByTaskId(@RequestBody JavaSystemTask javaSystemTask){
        taskService.updateTask(javaSystemTask);
        return ResultBody.success();
    }

    @PreAuthorize("@el.check('admin')")
    @PostMapping("/scheduleTask/{systemTaskId}")
    public ResultBody scheduleTask(@PathVariable("systemTaskId") Integer systemTaskId){
        taskService.scheduleTask(systemTaskId);
        return ResultBody.success();
    }

    @PreAuthorize("@el.check('admin')")
    @PostMapping("/stopTask/{systemTaskId}")
    public ResultBody stopTask(@PathVariable("systemTaskId") Integer systemTaskId){
        taskService.stopTask(systemTaskId);
        return ResultBody.success();
    }

    @PreAuthorize("@el.check('admin')")
    @PostMapping("/deleteTask/{systemTaskId}")
    public ResultBody deleteTask(@PathVariable("systemTaskId") Integer systemTaskId){
        taskService.deleteTask(systemTaskId);
        return ResultBody.success();
    }
}
