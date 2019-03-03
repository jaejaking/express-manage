package com.eightbyte.service.impl;

import com.eightbyte.domain.RegisterKey;
import com.eightbyte.domain.RegisterKeyExample;
import com.eightbyte.mapper.RegisterKeyMapper;
import com.eightbyte.service.RegisterKeyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class RegisterKeyServiceImpl implements RegisterKeyService {

    @Autowired
    private RegisterKeyMapper keyMapper;

    @Override
    public RegisterKey selectRegisterKeyByKeyName(String keyName) {
        RegisterKeyExample registerKeyExample = new RegisterKeyExample();
        registerKeyExample.createCriteria().andKeyNameEqualTo(keyName);
        List<RegisterKey> registerKeys = keyMapper.selectByExample(registerKeyExample);
        RegisterKey registerKey = null;
        if (!registerKeys.isEmpty()) {
            registerKey = registerKeys.get(0);
        }
        return registerKey;
    }
}
