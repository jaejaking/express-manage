package com.eightbyte;

import com.eightbyte.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserTest {

    @Autowired
    private UserService userService;

    @Test
    public void testChangePassword() {
        int rst = userService.updateUserPassword("admin", "admin");
        System.out.println(rst);
    }
}
