package com.hao.movieshareback.utils;

import com.hao.movieshareback.vo.ScreenPicture;
import com.hao.movieshareback.vo.VideoFileRawInfo;
import org.apache.commons.lang3.StringUtils;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class VideoUtils {
    private static final Logger LOG = LoggerFactory.getLogger(VideoUtils.class);

    public static final String SCREEN_SHOT_FORMAT="jpg";
    /**
     * @Description: 获取视频截图
     * @throws IOException  void
     */
    public static VideoFileRawInfo getScreenshot(String filePath) throws Exception{

        LOG.info("截取视频截图开始："+ System.currentTimeMillis());
        FFmpegFrameGrabber grabber = FFmpegFrameGrabber.createDefault(filePath);

        // 第一帧图片存储位置
        String targerFilePath = filePath.substring(0, filePath.lastIndexOf(File.separator));
        // 视频文件名
        String fileName = filePath.substring(filePath.lastIndexOf(File.separator) + 1);
        // 图片名称
        String targetFileName = fileName.substring(0, fileName.lastIndexOf("."));

        grabber.start();
        //设置视频截取帧（默认取第一帧）
        Frame frame = grabber.grabImage();
        //视频旋转度
        String rotate = grabber.getVideoMetadata("rotate");
        Java2DFrameConverter converter = new Java2DFrameConverter();
        //绘制图片
        BufferedImage bi = converter.getBufferedImage(frame);
        if (rotate != null) {
            // 旋转图片
            bi = rotate(bi, Integer.parseInt(rotate));
        }
        //图片的完整路径
        String imagePath = targerFilePath + File.separator + targetFileName + "." + SCREEN_SHOT_FORMAT;
        //创建文件
        File output = new File(imagePath);
        ImageIO.write(bi, SCREEN_SHOT_FORMAT, output);

        long duration = grabber.getLengthInTime() / (1000 * 1000);

        ScreenPicture screenPicture = new ScreenPicture(bi.getHeight(),bi.getWidth(),output.length(),
                output.getAbsolutePath(),grabber.getFormat(),StringUtils.isBlank(rotate)? "0" : rotate);
        LOG.info("视频的宽:" + bi.getWidth());
        LOG.info("视频的高:" + bi.getHeight());
        LOG.info("视频的旋转度：" + rotate);
        LOG.info("视频的格式：" + grabber.getFormat());
        LOG.info("此视频时长（s/秒）：" + duration);
        grabber.stop();
        LOG.info("截取视频截图结束："+ System.currentTimeMillis());
        return new VideoFileRawInfo(duration,screenPicture);
    }

    /**
     * @Description: 根据视频旋转度来调整图片
     * @param src
     * @param angel	视频旋转度
     * @return  BufferedImage
     */
    public static BufferedImage rotate(BufferedImage src, int angel) {
        int src_width = src.getWidth(null);
        int src_height = src.getHeight(null);
        int type = src.getColorModel().getTransparency();
        Rectangle rect_des = calcRotatedSize(new Rectangle(new Dimension(src_width, src_height)), angel);
        BufferedImage bi = new BufferedImage(rect_des.width, rect_des.height, type);
        Graphics2D g2 = bi.createGraphics();
        g2.translate((rect_des.width - src_width) / 2, (rect_des.height - src_height) / 2);
        g2.rotate(Math.toRadians(angel), src_width / 2, src_height / 2);
        g2.drawImage(src, 0, 0, null);
        g2.dispose();
        return bi;
    }


    /**
     * @Description: 计算图片旋转大小
     * @param src
     * @param angel
     * @return  Rectangle
     */
    public static Rectangle calcRotatedSize(Rectangle src, int angel) {
        if (angel >= 90) {
            if (angel / 90 % 2 == 1) {
                int temp = src.height;
                src.height = src.width;
                src.width = temp;
            }
            angel = angel % 90;
        }
        double r = Math.sqrt(src.height * src.height + src.width * src.width) / 2;
        double len = 2 * Math.sin(Math.toRadians(angel) / 2) * r;
        double angel_alpha = (Math.PI - Math.toRadians(angel)) / 2;
        double angel_dalta_width = Math.atan((double) src.height / src.width);
        double angel_dalta_height = Math.atan((double) src.width / src.height);
        int len_dalta_width = (int) (len * Math.cos(Math.PI - angel_alpha - angel_dalta_width));
        int len_dalta_height = (int) (len * Math.cos(Math.PI - angel_alpha - angel_dalta_height));
        int des_width = src.width + len_dalta_width * 2;
        int des_height = src.height + len_dalta_height * 2;
        return new java.awt.Rectangle(new Dimension(des_width, des_height));
    }
}
