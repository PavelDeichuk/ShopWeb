package com.pavel.shopweb.Service.impl;

import com.pavel.shopweb.Service.TotpService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
class TotpServiceImplTest {

    @MockBean
    private TotpService totpService;

    @Test
    void generateSecret() {
        String secret = "secret";
        BDDMockito.given(totpService.generateSecret()).willReturn(secret);
        String SecretTest = totpService.generateSecret();
        assertEquals(SecretTest, secret);
        verify(totpService).generateSecret();
    }

    @Test
    void getUriForImage() {
        String uri = "test";
        BDDMockito.given(totpService.getUriForImage("test")).willReturn(uri);
        String uriTest = totpService.getUriForImage("test");
        assertEquals(uriTest, uri);
        verify(totpService).getUriForImage("test");
    }

    @Test
    void verifyCode() {
        Boolean verify = true;
        BDDMockito.given(totpService.verifyCode("test", "test")).willReturn(verify);
        Boolean verifyTest = totpService.verifyCode("test", "test");
        assertEquals(verifyTest, verify);
        verify(totpService).verifyCode("test", "test");
    }
}