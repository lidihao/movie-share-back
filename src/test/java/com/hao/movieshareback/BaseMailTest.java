package com.hao.movieshareback;


import com.hao.movieshareback.utils.VideoUtils;
import org.junit.jupiter.api.Test;

public class BaseMailTest {
    @Test
    public void testVideo(){
        try {
            VideoUtils.getScreenshot("/home/lidihao/upload/video/root/VID_20190207_125532.mp4");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
