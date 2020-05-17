package com.hao.movieshareback.service.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class TaskManagerStarter implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    private TaskManager taskManager;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        taskManager.startSchedule();
    }
}
