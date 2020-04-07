package com.hao.movieshareback.recommend.utils;

import java.util.HashMap;
import java.util.Map;

public class VideoTagSimilarityComputer {
    private Map<Integer ,Integer> tagIndex;
    private int vectorLen;
    private CosineSimilarityComputer cosineSimilarityComputer;

    public VideoTagSimilarityComputer(int[] tagIdList,CosineSimilarityComputer cosineSimilarityComputer){
        this.cosineSimilarityComputer=cosineSimilarityComputer;
        this.tagIndex = new HashMap<>();
        this.vectorLen=tagIdList.length;
        for (int i=0;i<tagIdList.length;i++){
            tagIndex.put(tagIdList[i],i);
        }
    }

    public double computeVideoSimilarity(int[] video1Tag,int[] video2Tag){
        double[] v1 = tagToVector(video1Tag);
        double[] v2 = tagToVector(video2Tag);
        return cosineSimilarityComputer.compute(v1,v2);
    }

    private double[] tagToVector(int[] tagList){
        double[] v = new double[vectorLen];
        for (int value : tagList) {
            int idx = tagIndex.get(value);
            v[idx] = 1.0;
        }
        return v;
    }
}
