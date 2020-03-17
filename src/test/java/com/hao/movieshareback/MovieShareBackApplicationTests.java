package com.hao.movieshareback;

import com.hao.movieshareback.dao.UserMapper;
import com.hao.movieshareback.model.User;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.lucene.search.function.FieldValueFactorFunction;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.functionscore.FieldValueFactorFunctionBuilder;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Date;

@SpringBootTest
class MovieShareBackApplicationTests {
    @Autowired
    JavaMailSender javaMailSender;
    @Test
    void contextLoads() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("测试");
        message.setFrom("2315447580@qq.com");
        message.setTo("18302026070@163.com");
        message.setSentDate(new Date());
        message.setText("这是测试文件");
        javaMailSender.send(message);
    }

    @Autowired
    UserMapper userMapper;
    @Test
    void test(){
        User user=new User();
        user.setUserName("李帝豪");
        user.setIntroduce("1");
        user.setPassword("1");
        user.setSalt("1");
        user.setEmail("19384@163.com");
        user.setCreatedBy("李帝豪");
        user.setCreatedTime(new Date());
        user.setUpdatedBy("李帝豪");
        user.setUpdatedTime(new Date());
        userMapper.save(user);
    }


}
