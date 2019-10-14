package cn.itsource.aigou.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("AIGOU-COMMON")
@Component
public interface StaticPageClient {
    /**
       * 公共的接口-使用模板技术静态化页面
       * 模板路径，数据，目标文件路径
       */
    @PostMapping("/page")
    public void generateStaticPage(
            @RequestParam("templatePath") String templatePath,
            @RequestParam("targetPath")String targetPath,
            @RequestBody Object model);
}
