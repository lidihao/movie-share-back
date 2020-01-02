package com.hao.movieshareback.utils;

public class FileTypeUtils {
    private static final String MP4_SUFFIX=".mp4";
    private static final String MP4_TYPE="video/mp4\n";

    public static String getFileType(String suffix){
        String type = "";
        switch (suffix){
            case MP4_SUFFIX:return MP4_TYPE;
            default:
        }
        return type;
    }
}
