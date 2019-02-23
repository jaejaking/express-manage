package com.eightbyte.service;

import com.eightbyte.domain.User;
import org.apache.commons.lang3.StringUtils;

public interface UserService {

    int insertUser(User user);

    int insertUser(String userName, String password, int questionId, String answer, int keyId);

    User selectUserByUserName(String userName);

}
