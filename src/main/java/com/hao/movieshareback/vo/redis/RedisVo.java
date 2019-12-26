package com.hao.movieshareback.vo.redis;

import javax.validation.constraints.NotBlank;

public class RedisVo {
    @NotBlank
    private String key;

    @NotBlank
    private String value;

    public RedisVo(@NotBlank String key, @NotBlank String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
