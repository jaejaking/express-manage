package com.eightbyte.controller;

import com.alibaba.fastjson.JSON;
import com.eightbyte.domain.*;
import com.eightbyte.mapper.QuestionMapper;
import com.eightbyte.mapper.RegisterKeyMapper;
import com.eightbyte.service.UserService;
import com.eightbyte.util.Md5Util;
import com.eightbyte.vo.ResultVo;
import io.swagger.annotations.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author yanghaoran@ehomepay.com.cn
 * @createDate 2019/2/21
 * @description 用户操作控制器
 */
@RestController
@RequestMapping("/user")
@Api("用户控制器")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private RegisterKeyMapper registerKeyMapper;

    @Autowired
    private QuestionMapper questionMapper;

    @PostMapping("/login")
    @ApiOperation(value = "用户登录")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "userName", required = true, dataType = "string", paramType = "query"),
                    @ApiImplicitParam(name = "password", required = true, dataType = "string", paramType = "query")

            }
    )
    public ResultVo login(String userName, String password) {
        logger.info("login参数，username:{},password:{}", userName, password);
        if (StringUtils.isBlank(userName) || userName.length() > 32 || userName.length() < 6) {
            return error("用户名不合法!");
        }
        if (StringUtils.isBlank(password) || password.length() != 32) {
            return error("密码不合法！");
        }

        User user = userService.selectUserByUserName(userName);
        if (user == null) {
            return error("用户名或者密码错误!");
        }
        if (Md5Util.getMD5(password).equalsIgnoreCase(user.getPassword())) {
            return success("登录成功!");
        }
        return error("用户名或者密码错误");

    }

    @PostMapping("/register")
    public ResultVo register(String userName, String password, Integer questionId, String answer, String key) {
        logger.info("register请求参数userName:{},passowrd:{},key:{}", userName, password, key);
        if (StringUtils.isEmpty(userName) || userName.length() > 32 || userName.length() < 6) {
            return error("用户名不合法!");
        }
        if (StringUtils.isEmpty(password) || password.length() != 32) {

            return error("密码不合法！");
        }
        if (questionId == null || StringUtils.isEmpty(answer)) {
            return error("密保相关信息有误！");
        }
        RegisterKeyExample registerKeyExample = new RegisterKeyExample();
        registerKeyExample.createCriteria().andKeyNameEqualTo(key).andStatusEqualTo(0);
        List<RegisterKey> registerKeys = registerKeyMapper.selectByExample(registerKeyExample);
        if (registerKeys.isEmpty()) {
            return error("key不合法！");
        }

        User user = userService.selectUserByUserName(userName);
        if (user != null) {
            return error("用户名已存在!");
        }
        int rst = userService.insertUser(userName, password, questionId,answer,registerKeys.get(0).getId());
        if (rst > 0) {
            return success("注册成功!");
        }

        return error("注册失败！");
    }

    @GetMapping("/getPasswordQuestionInfos")
    public ResultVo passwordQuestionInfos() {
        QuestionExample questionExample = new QuestionExample();
        questionExample.createCriteria().andIdGreaterThan(0);
        List<Question> questions = questionMapper.selectByExample(questionExample);
        logger.info("获取密保问题信息结果集:{}", JSON.toJSONString(questions));
        return success("查询成功!", questions);
    }
}
