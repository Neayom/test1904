package edu.dlnu.msmservice.service.impl;

import com.alibaba.nacos.client.naming.utils.StringUtils;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;

import com.tencentcloudapi.sms.v20190711.SmsClient;
import com.tencentcloudapi.sms.v20190711.models.SendSmsRequest;
import com.tencentcloudapi.sms.v20190711.models.SendSmsResponse;
import edu.dlnu.msmservice.service.MsmService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class MsmServiceImpl implements MsmService {
    @Override
    public boolean send(String phone, String code) {
        //param包含code
        if(StringUtils.isEmpty(phone)) return false;
        Credential cred = new Credential("AKIDO4hfE8acM2YDeEusNckDDyUjCQQN8OCm", "jYBYYO6YkXrwrXeXBYa9JYAj9j4AAxd6");//自己的腾讯key

        HttpProfile httpProfile = new HttpProfile();
        httpProfile.setEndpoint("sms.tencentcloudapi.com");

        ClientProfile clientProfile = new ClientProfile();
        clientProfile.setHttpProfile(httpProfile);

        SmsClient client = new SmsClient(cred, "ap-guangzhou", clientProfile);

        SendSmsRequest req = new SendSmsRequest();

        String[] phoneNumberSet1 = {"+86"+phone};//电话
        String[] templateParamSet1 = {code};//验证码
        req.set("PhoneNumberSet",phoneNumberSet1);//电话
        req.set("TemplateParamSet",templateParamSet1);//验证码
        req.set("TemplateID","882697");//模板
        req.set("SmsSdkAppid","1400491689");//在添加应用看生成的实际SdkAppid
        req.set("Sign","秋招进大厂公众号");//签名

        try {
            SendSmsResponse resp = client.SendSms(req);//发送请求
            System.out.println(SendSmsResponse.toJsonString(resp));
        } catch (TencentCloudSDKException e) {
            e.printStackTrace();
        }
        return true;

    }
}
