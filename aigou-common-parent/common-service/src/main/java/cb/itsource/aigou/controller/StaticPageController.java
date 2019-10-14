package cb.itsource.aigou.controller;

import cb.itsource.aigou.util.VelocityUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StaticPageController {
    /**
       * 公共的接口-使用模板技术静态化页面
       * 模板路径，数据，目标文件路径
       */
    @PostMapping("/page")
    public void generateStaticPage(
        @RequestParam("templatePath") String templatePath,
        @RequestParam("targetPath")String targetPath,
        @RequestBody Object model){

        /*System.out.println("templatePath:"+templatePath);
        System.out.println("targetPath:"+targetPath);
        System.out.println(model);*/
        VelocityUtils.staticByTemplate(model,templatePath,targetPath);
    }
}
