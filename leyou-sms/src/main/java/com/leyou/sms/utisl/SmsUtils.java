package com.leyou.sms.utisl;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.leyou.sms.config.SmsProperities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(SmsProperities.class)
public class SmsUtils {

    @Autowired
    private SmsProperities props;

    // 产品名
    static final String product = "Dysmsapi";

    // 域名
    static final String domain = "dysmsapi.aliyuncs.com";

    static final Logger logger = LoggerFactory.getLogger(SmsUtils.class);

    public SendSmsResponse sendSms(String phone, String code, String signName, String template) throws ClientException {
        // 调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        // 初始化acsClient，暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou",
                props.getAccessKeyId(), props.getAccessKeySecret());
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);

        SendSmsRequest request = new SendSmsRequest();

        request.setMethod(MethodType.POST);

        request.setPhoneNumbers(phone);

        request.setSignName(signName);

        request.setTemplateCode(template);

        request.setTemplateParam("{\"code\":\"" + code + "\"}");


        request.setOutId("123456");

        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);

        logger.info("send code: {}", sendSmsResponse.getCode());
        logger.info("send msg: {}", sendSmsResponse.getMessage());

        return sendSmsResponse;
    }
}
