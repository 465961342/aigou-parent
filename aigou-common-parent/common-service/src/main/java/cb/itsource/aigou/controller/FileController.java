package cb.itsource.aigou.controller;

import cb.itsource.aigou.util.FastDfsApiOpr;
import cn.itsource.basic.util.AjaxResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class FileController {
    /**
       * 文件上传
       * @param file SpringMVC对文件上传的封装
       * @return
       */
    @PostMapping("/file")
    public AjaxResult upload(MultipartFile file){
        try {
            byte[] content = file.getBytes();//获取文件内容
            //文件扩展名
            String fileName = file.getOriginalFilename();
            int index = fileName.lastIndexOf(".");
            String extName = fileName.substring(index + 1);
            String file_id = FastDfsApiOpr.upload(content,extName);
            return AjaxResult.me().setSuccess(true).setMessage("文件上传成功!").setRestObj(file_id);
        } catch (IOException e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("文件上传失败!"+e.getMessage());
        }
    }


    /**
       * 删除文件
       */
    @DeleteMapping("/file")
    public AjaxResult delete(String fileId){
        // fileId =/group1/M00/00/01/rBAEz12l5LGAYFq4AABT5bbowSw350.jpg
        // group = group1
        // fileName = M00/00/01/rBAEz12l5LGAYFq4AABT5bbowSw350.jpg
        System.out.println(fileId);
        try {
            //   group1/M00/00/01/rBAEz12l5LGAYFq4AABT5bbowSw350.jpg
            fileId = fileId.substring(1);
            int index = fileId.indexOf("/");
            String group = fileId.substring(0,index);
            String fileName = fileId.substring(index+1);
            System.out.println("group:" + group);
            System.out.println("fileNameoup:" + fileName);
            FastDfsApiOpr.delete(group, fileName);
            return AjaxResult.me().setSuccess(true).setMessage("文件删除成功!");
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("文件删除失败!"+e.getMessage());
        }
    }
}
