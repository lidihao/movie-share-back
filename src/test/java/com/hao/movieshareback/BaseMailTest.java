package com.hao.movieshareback;


import com.hao.movieshareback.model.VideoFile;
import com.hao.movieshareback.utils.NetworkInterfaceUtil;
import com.hao.movieshareback.utils.VideoUtils;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.FrameRecorder;
import org.junit.jupiter.api.Test;

import java.io.File;
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
