package com.leyou.user.service;

import com.leyou.common.utils.NumberUtils;
import com.leyou.user.mapper.UserMapper;
import com.leyou.user.pojo.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private AmqpTemplate amqpTemplate;

    /**
     *
     * @param data
     * @param type 1:username, 2: phone
     * @return
     */
    public Boolean checkUser(String data, Integer type) {
        User record = new User();
        if (type == 1) {
            record.setUsername(data);
        } else if (type == 2) {
            record.setPhone(data);
        } else {
            return null;
        }
        return this.userMapper.selectCount(record) == 0;
    }



    public User queryUser(String username, String password) {
        User record = new User();

        us

        this.userMapper.select(record);
    }

    public void sendVerifyCode(String phone) {
        // 生成验证码
        if (StringUtils.isBlank(phone)) {
            return;
        }

        // 生成验证码
        String code = NumberUtils.generateCode(6);

        // 发送消息到rabbitMQ
        this.amqpTemplate.convertAndSend();


        // 把验证码保存到redis中

    }
}
