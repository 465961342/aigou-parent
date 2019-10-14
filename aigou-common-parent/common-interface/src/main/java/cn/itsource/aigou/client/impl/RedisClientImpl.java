package cn.itsource.aigou.client.impl;

import cn.itsource.aigou.client.RedisClient;

public class RedisClientImpl implements RedisClient{
    /**
     *   * 获取缓存数据
     *   * @param key
     *   * @return
     *   
     *
     * @param key
     */
    @Override
    public String get(String key) {
        return "{\"message\":\"服务器异常!\"}";
    }

    /**
     *   * 设置缓存数据
     *   * @param key
     *   * @param value
     *   
     *
     * @param key
     * @param value
     */
    @Override
    public void set(String key, String value) {

    }
}
