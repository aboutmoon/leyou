package com.leyou.auth.test;

import com.leyou.common.pojo.UserInfo;
import com.leyou.common.utils.JwtUtils;
import com.leyou.common.utils.RsaUtils;
import org.junit.Before;
import org.junit.Test;

import java.security.PrivateKey;
import java.security.PublicKey;

public class JwtTest {

    private static final String pubKeyPath = "/home/hb/file/rsa.pub";
    private static final String priKeyPath = "/home/hb/file/rsa.pri";

    private PublicKey publicKey;
    private PrivateKey privateKey;

    @Test
    public void testRsa() throws Exception {
        RsaUtils.generateKey(pubKeyPath, priKeyPath, "234");
    }

    @Before
    public void testGetRsa() throws Exception {
        this.publicKey = RsaUtils.getPublicKey(pubKeyPath);
        this.privateKey = RsaUtils.getPrivateKey(priKeyPath);
    }

    @Test
    public void testGenerateToken() throws Exception {
        String token = JwtUtils.generateToken(new UserInfo(20L, "jack"), privateKey, 5);
        System.out.println("token = " + token);
    }

    @Test
    public void testParseToken() throws Exception {
        String token = "eyJhbGciOiJSUzI1NiJ9.eyJpZCI6MjAsInVzZXJuYW1lIjoiamFjayIsImV4cCI6MTU5NTQ4NjUxOH0.a2eXUtjKJdSo8IA28nfTw_qSbDIeLyzMSVwGug-3pZGlmqVeYC04vFhFfn2NahYo5tvNN4AqDDVfAn_x4cItoNcePrzcLg0min9jloDkriFgZwH6i3Hr8RMg0soVzAvyOFHZsrhLTCMmwA9eljvG23fnI7XitD0Ht58RZfns4YA";

        // 解析token
        UserInfo userInfo = JwtUtils.getInfoFromToken(token, publicKey);

        System.out.println("id: " + userInfo.getId());
        System.out.println("username: " + userInfo.getUsername());
    }
}
