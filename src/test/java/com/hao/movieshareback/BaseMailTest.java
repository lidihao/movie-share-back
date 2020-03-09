package com.hao.movieshareback;


import com.hao.movieshareback.utils.NetworkInterfaceUtil;
import com.hao.movieshareback.utils.VideoUtils;
import org.junit.jupiter.api.Test;

import java.util.List;

public class BaseMailTest {
    @Test
    public void testVideo(){
        try {
            List<String> list= NetworkInterfaceUtil.getIp4Addresses();
            System.out.println(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
