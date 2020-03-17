package com.hao.movieshareback;


import com.hao.movieshareback.model.VideoFile;
import com.hao.movieshareback.utils.NetworkInterfaceUtil;
import com.hao.movieshareback.utils.VideoUtils;
import org.apache.lucene.util.QueryBuilder;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.FrameRecorder;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.lucene.search.function.FieldValueFactorFunction;
import org.elasticsearch.common.lucene.search.function.FunctionScoreQuery;
import org.elasticsearch.common.lucene.search.function.ScoreFunction;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.functionscore.FieldValueFactorFunctionBuilder;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class BaseMailTest {
    @Test
    public void testVideo(){
        try {
            List<String> list= NetworkInterfaceUtil.getIp4Addresses();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testConvert() throws FrameRecorder.Exception, FrameGrabber.Exception {
        File file = new File("/home/lidihao/Downloads/test.rmvb");
        VideoUtils.convertToMp4(file);
    }

}
