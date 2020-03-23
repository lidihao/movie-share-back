package com.hao.movieshareback.tasks;

import com.hao.movieshareback.recommend.RecommendByCF;
import com.hao.movieshareback.utils.SpringContextJobUtil;
import org.apache.mahout.cf.taste.common.TasteException;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class RecommendCFUserBaseJob extends QuartzJobBean {
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        RecommendByCF recommendByCF = SpringContextJobUtil.getBean(RecommendByCF.class);
        try {
            recommendByCF.recommend(5,8);
        } catch (TasteException e) {
            e.printStackTrace();
        }
    }
}
