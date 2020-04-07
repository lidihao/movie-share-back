package com.hao.movieshareback.recommend.utils;

public class CosineSimilarityComputer {

    public double compute(double[] v1, double[] v2){
        if (v1==null||v2==null){
            throw new RuntimeException("v1 or v2 is null");
        }
        if (v1.length!=v2.length){
            throw new RuntimeException("length not equal");
        }

        return vectorMultiple(v1, v2)/(computeLen(v1)*computeLen(v2));
    }

    private double computeLen(double[] v){
        double sum = 0;
        for (double value : v) {
            sum += value * value;
        }
        return Math.sqrt(sum);
    }

    private double vectorMultiple(double[] v1, double[] v2){
        double sum = 0;
        for (int i=0;i<v1.length;i++){
            sum+=v1[i]*v2[i];
        }
        return sum;
    }
}
