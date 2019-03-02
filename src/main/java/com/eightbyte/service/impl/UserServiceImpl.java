package com.eightbyte.service.impl;

import com.eightbyte.domain.RegisterKey;
import com.eightbyte.domain.RegisterKeyExample;
import com.eightbyte.domain.User;
import com.eightbyte.domain.UserExample;
import com.eightbyte.mapper.RegisterKeyMapper;
import com.eightbyte.mapper.UserMapper;
import com.eightbyte.service.UserService;
import com.eightbyte.util.Md5Util;
import com.eightbyte.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {


    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RegisterKeyMapper registerKeyMapper;

    @Override
    public int insertUser(User user) {
        return userMapper.insert(user);
    }

    @Override
    @Transactional(readOnly = false, transactionManager = "transactionManager", rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public int insertUser(String userName, String password, int questionId, String answer, int keyId) {
        User user = User.builder()
                .username(userName)
                .password(password)
                .registerKeyId(keyId)
                .questionId(questionId)
                .questionAnswer(answer)
                .roleId(5)
                .createTime(new Date())
                .updateTime(new Date())
                .build();
        int rst = insertUser(user);
        if (rst > 0) {
            RegisterKey registerKey = new RegisterKey();
            registerKey.setId(user.getRegisterKeyId());
            registerKey.setStatus(1);
            int result = registerKeyMapper.updateByPrimaryKeySelective(registerKey);
            log.info("更新注册key result:{}", result);
        }
        return rst;
    }


    @Override
    public User selectUserByUserName(String userName) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andUsernameEqualTo(userName);
        List<User> users = userMapper.selectByExample(userExample);
        if (users.isEmpty()) {
            return null;
        }
        return users.get(0);
    }

    @Override
    public boolean questionAnswerRight(String userName, Integer questionId, String answer) {
        UserExample userExample = new UserExample();
        userExample.createCriteria()
                .andUsernameEqualTo(userName)
                .andQuestionIdEqualTo(questionId)
                .andQuestionAnswerEqualTo(answer);
        return userMapper.selectByExample(userExample).size() != 0;
    }

    @Override
    public int updateUserPassword(String userName, String password) {
        User user = new User();
        user.setUpdateTime(new Date());
        user.setPassword(Md5Util.getMD5(password));
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUsernameEqualTo(userName);
        return userMapper.updateByExampleSelective(user, userExample);
    }

    @Override
    public int updateUserPassword(String userName, String oldPassword, String newPassword) {
        User user = new User();
        user.setPassword(Md5Util.getMD5(newPassword));
        user.setUpdateTime(new Date());
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUsernameEqualTo(userName)
                .andPasswordEqualTo(Md5Util.getMD5(oldPassword));
        return userMapper.updateByExampleSelective(user, userExample);
    }

    @Override
    public List<User> searchAllCarrier() {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andRoleIdEqualTo(5);
        return userMapper.selectByExample(userExample);
    }

    @Override
    public UserVo getUserTasksByUserId(Integer userId) {

        return userMapper.getUserTasksByUserId(userId);
    }

    @Override
    public UserVo selectUserVoRoleByUserName(String userName) {

        return userMapper.selectUserVoRoleByUserName(userName);
    }
}
