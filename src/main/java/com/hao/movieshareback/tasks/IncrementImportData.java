package com.hao.movieshareback.tasks;

import cn.hutool.core.date.DateUtil;
import com.hao.movieshareback.dao.VideoMapper;
import com.hao.movieshareback.service.VideoService;
import com.hao.movieshareback.vo.Page;
import com.hao.movieshareback.vo.PageInfo;
import com.hao.movieshareback.vo.PageList;
import com.hao.movieshareback.vo.VideoIndexVo;
import org.quartz.JobExecutionContext;

import java.io.IOException;
import java.util.Date;

public class IncrementImportData extends ImportToElasticsearchJob {
    @Override
    protected void doImportData(Integer pageSize, VideoMapper videoMapper, VideoService videoService, JobExecutionContext jobExecutionContext) {
        Integer pageNum=1;
        PageList<VideoIndexVo> videoIndexVos=null;
        PageInfo pageInfo=null;
        Date date = jobExecutionContext.getPreviousFireTime();
        do {
            Page page = new Page(pageNum++,pageSize);
            videoIndexVos=videoMapper.selectVideoIndexVo(page, DateUtil.formatDateTime(date));
            pageInfo = videoIndexVos.getPageInfo();
            try {
                videoService.indexVideo(videoIndexVos);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }while (pageInfo!=null&&pageInfo.isHasNextPage());
    }
}
