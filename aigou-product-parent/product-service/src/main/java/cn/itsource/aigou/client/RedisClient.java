package cn.itsource.aigou.client;

import cn.itsource.aigou.client.impl.RedisClientImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "AIGOU-COMMON",fallback =
        RedisClientImpl.class)
@Component
public interface RedisClient {
    /**
       * 获取缓存数据
       * @param key
       * @return
       */
    @GetMapping("/redis")
    public String get(@RequestParam("key") String key);

    /**
       * 设置缓存数据
       * @param key
       * @param value
       */
    @PostMapping("/redis")
    public void set(@RequestParam("key") String key,
                    @RequestParam("value") String value);
}
