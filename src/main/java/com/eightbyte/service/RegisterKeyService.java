package com.eightbyte.service;

import com.eightbyte.domain.RegisterKey;

public interface RegisterKeyService {
    RegisterKey selectRegisterKeyByKeyName(String keyName);
}
