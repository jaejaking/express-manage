package com.eightbyte.controller;

import com.alibaba.fastjson.JSON;
import com.eightbyte.constant.Constant;
import com.eightbyte.domain.*;
import com.eightbyte.enumration.ResultCode;
import com.eightbyte.mapper.QuestionMapper;
import com.eightbyte.mapper.RegisterKeyMapper;
import com.eightbyte.service.UserService;
import com.eightbyte.util.Md5Util;
import com.eightbyte.vo.ResultVo;
import com.eightbyte.vo.UserVo;
import io.swagger.annotations.*;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
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

    /**
     * 最大用户名长度
     */
    private static final Integer MAX_USERNAME_LENGTH = 32;

    /**
     * 最小用户名长度
     */
    private static final Integer MIN_USERNAME_LENGTH = 5;

    /**
     * MD5后的密码长度
     */
    private static final Integer MD5_PASSWORD_LENGTH = 32;

    @Autowired
    private UserService userService;

    @Autowired
    private RegisterKeyMapper registerKeyMapper;

    @Autowired
    private QuestionMapper questionMapper;


    private boolean checkUserName(String userName) {
        if (StringUtils.isEmpty(userName) || userName.length() > MAX_USERNAME_LENGTH || userName.length() < MIN_USERNAME_LENGTH) {
            return false;
        }

        return true;

    }

    private boolean checkPassword(String password) {
        if (StringUtils.isEmpty(password) || password.length() != MD5_PASSWORD_LENGTH) {

            return false;
        }
        return true;
    }


    private List<UserVo> buildUserVos(List<User> users) {
        List<UserVo> userVos = new ArrayList<>(8);
        if (users == null || users.isEmpty()) {
            return null;
        }
        for (User user : users) {
            logger.info(JSON.toJSONString(user));
            Integer tasks = null;
            UserVo uVo = userService.getUserTasksByUserId(user.getId());
            if (uVo != null) {
             tasks=uVo.getTasks();
            }
            UserVo userVo = UserVo.builder()
                    .id(user.getId())
                    .userName(user.getUsername())
                    .tasks(tasks == null ? 0 : tasks)
                    .build();
            userVos.add(userVo);

        }
        return userVos;
    }

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
        if (!checkUserName(userName)) {
            return error("用户名不合法!");
        }
        if (!checkPassword(password)) {
            return error("密码不合法！");
        }

        User user = userService.selectUserByUserName(userName);
        if (user == null) {
            return error("用户名错误!");
        }
        if (Md5Util.getMD5(password).equalsIgnoreCase(user.getPassword())) {
            //登录成功存储用户登录信息
            HttpSession session = request.getSession(true);
            session.setAttribute(Constant.LOGIN_SUCCESS_TOKEN, userName);
            session.setMaxInactiveInterval(60 * 60 * 10);
            return success("登录成功!");
        }
        return error("用户名或者密码错误");

    }

    @PostMapping("/register")
    public ResultVo register(String userName, String password, Integer questionId, String answer, String key) {
        logger.info("register请求参数userName:{},password:{},key:{}", userName, password, key);
        if (!checkUserName(userName)) {
            return error("用户名不合法!");
        }
        if (!checkPassword(password)) {

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
        int rst = userService.insertUser(userName, Md5Util.getMD5(password), questionId, answer, registerKeys.get(0).getId());
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

    @PostMapping("/findPassword")
    public ResultVo forgetPassword(String userName, Integer questionId, String answer, String newPassword) {
        logger.info("找回密码请求参数\t,userName:{},newPassword:{},questionId:{},answer:{}", userName, newPassword, questionId, answer);
        if (!checkUserName(userName)) {
            return error("用户名不合法！");
        }
        if (StringUtils.isEmpty(answer) || questionId == null) {
            return error("请传入密码问题答案!");
        }
        if (!checkPassword(newPassword)) {
            return error("新密码不合法！");
        }
        User user = userService.selectUserByUserName(userName);
        if (user == null) {
            return error("此用户名不存在!");
        }

        boolean right = userService.questionAnswerRight(userName, questionId, answer);
        if (!right) {
            return error("密保问题选择错误或密保问题答案错误！");
        }
        int updateRst = userService.updateUserPassword(userName, newPassword);
        if (updateRst > 0) {
            return success("更改密码成功!");
        }
        return error("更改密码失败！");


    }

    @PostMapping("/changePassword")
    public ResultVo changePassword(String oldPassword, String newPassword) {
        logger.info("修改密码参数\t,oldPassword:{},newPassword:{}", oldPassword, newPassword);
        if (!checkPassword(oldPassword) || !checkPassword(newPassword)) {
            return error("新密码或旧密码不符合格式!");
        }
        HttpSession session = request.getSession(true);
        Object attribute = session.getAttribute(Constant.LOGIN_SUCCESS_TOKEN);
        if (attribute == null) {
            return error("当前用户未登录!");
        }
        if (attribute instanceof String) {
            String userName = (String) attribute;
            int rst = userService.updateUserPassword(userName, oldPassword, newPassword);
            if (rst > 0) {
                return success("密码修改成功!");
            }

        }
        return error("密码修改失败!");
    }

    @GetMapping("/logout")
    public ResultVo logOut() {
        HttpSession session = request.getSession(true);
        session.invalidate();
        return success("登出成功!");

    }

    @GetMapping("/verifyUserLogin")
    public ResultVo verifyLogin() {
        HttpSession session = request.getSession(true);
        Object attribute = session.getAttribute(Constant.LOGIN_SUCCESS_TOKEN);
        if (attribute == null) {
            return new ResultVo(ResultCode.LOGOUT.getCode(), "用户未登录!");
        }

        return success("用户已登录！");
    }

    @GetMapping("/getAllCarrierAndTheirTasks")
    public ResultVo searchAllNotAdminUser() {
        List<User> users = userService.searchAllCarrier();
        logger.info("all carrier:{}", JSON.toJSONString(users));
        return success(buildUserVos(users));


    }

    @GetMapping("/getCurrentUserRole")
    public ResultVo getCurrentLoginUserRole() {
        HttpSession session = request.getSession(true);
        Object attribute = session.getAttribute(Constant.LOGIN_SUCCESS_TOKEN);
        if (attribute == null || !(attribute instanceof String)) {
            throw new RuntimeException("登录出现问题!");
        }
        String userName = (String) attribute;
        UserVo userVo = userService.selectUserVoRoleByUserName(userName);
        logger.info("userRoleVo:{}", JSON.toJSONString(userVo));
        return success(userVo);
    }


}
