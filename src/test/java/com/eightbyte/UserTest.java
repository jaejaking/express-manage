package com.eightbyte;

import com.eightbyte.domain.ExpressTraceRecord;
import com.eightbyte.mapper.ExpressTraceRecordMapper;
import com.eightbyte.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserTest {

    @Autowired
    private UserService userService;

    @Autowired
    private ExpressTraceRecordMapper traceRecordMapper;

    @Test
    public void testChangePassword() {
        int rst = userService.updateUserPassword("admin", "admin");
        System.out.println(rst);
    }

    @Test
    public void testInsertTraceRecord() {
        ExpressTraceRecord record = new ExpressTraceRecord();
        record.setCreateTime(new Date());
        record.setUpdateTime(new Date());
        record.setExpressOrderNo("1111111111111");
        int insert = traceRecordMapper.insert(record);
        System.out.println(insert);
    }
}
