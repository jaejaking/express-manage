package com.eightbyte.service;

import com.eightbyte.domain.User;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public interface UserService {

    int insertUser(User user);

    int insertUser(String userName, String password, int questionId, String answer, int keyId);

    User selectUserByUserName(String userName);

    boolean questionAnswerRight(String userName, Integer questionId, String answer);

    int updateUserPassword(String userName, String password);

    int updateUserPassword(String userName, String oldPassword, String newPassword);


    List<User> searchAllCarrier();

}
