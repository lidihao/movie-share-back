package com.hao.movieshareback.config;


import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.Executor;

@Configuration
public class QuartzConfig {

    @Autowired
    private DataSource dataSource;

    //配置properies配置文件
    @Bean("quartzProperties")
    public Properties getQuartzProperties() throws IOException {
        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
        propertiesFactoryBean.setLocation(new ClassPathResource("/quartz.properties"));
        //等配置文件全部读取完成并注入后再初始化对象
        propertiesFactoryBean.afterPropertiesSet();
        return propertiesFactoryBean.getObject();
    }


    //配置任务调度工厂
    @Bean("schedulerFactory")
    public SchedulerFactoryBean getScheduleFactoryBean(@Qualifier("quartzProperties") Properties quartzConfig,
                                                       @Qualifier("quartzThreadPool") Executor executor) throws IOException {
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        //开启更新job
        schedulerFactoryBean.setOverwriteExistingJobs(true);
        //配置数据源
        schedulerFactoryBean.setDataSource(dataSource);
        //配置实例在spring中的key
        schedulerFactoryBean.setApplicationContextSchedulerContextKey("quartz-schedule");
        //配置配置文件
        schedulerFactoryBean.setQuartzProperties(quartzConfig);
        //配置线程池
        schedulerFactoryBean.setTaskExecutor(executor);
        return schedulerFactoryBean;
    }



    //配置线程池
    @Bean("quartzThreadPool")
    public Executor getSchedulerThreadPool(){
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(10);
        threadPoolTaskExecutor.setMaxPoolSize(20);
        threadPoolTaskExecutor.setQueueCapacity(50);
        return threadPoolTaskExecutor;
    }


    //开启当前的任务调度器
    @Bean("quartzScheduler")
    public Scheduler getSchedule(@Qualifier("schedulerFactory")SchedulerFactoryBean schedulerFactoryBean) throws IOException, SchedulerException {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        return scheduler;
    }



}
