package cn.itsource.aigou.controller;

import cn.itsource.aigou.domain.User;
import cn.itsource.basic.util.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "登陆的cpntroller")
public class LoginController {
    /**
     * 登录接口
     */
    @ApiOperation(value = "登陆的接口")
    @PostMapping("/login")
    public AjaxResult login(@RequestBody User user){
        if("admin".equals(user.getUsername())&&"admin".equals(user.getPassword())){
            return AjaxResult.me().setSuccess(true).setMessage("恭喜，登陆成功！").setRestObj(user);
        };
        return AjaxResult.me().setSuccess(false).setMessage("登陆失败！");
    }
}
