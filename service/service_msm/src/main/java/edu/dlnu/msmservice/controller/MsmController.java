package edu.dlnu.msmservice.controller;

import edu.dlnu.commonutils.R;
import edu.dlnu.msmservice.service.MsmService;
import edu.dlnu.msmservice.utils.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("edumsm/msm")
@CrossOrigin
public class MsmController {
    @Autowired
    private MsmService msmService;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;
    @GetMapping("send/{phone}")
    public R sendMsm(@PathVariable String phone) {
        //1、从redis中获取验证码
        String codes = redisTemplate.opsForValue().get(phone);
        if(!StringUtils.isEmpty(codes)){
            return R.ok();
        }
        //生成随机值,传递阿里云进行发送
        String code = RandomUtil.getFourBitRandom();//通过工具类得到4个随机值
        boolean isSend = msmService.send(phone,code);
        //判断
        if (isSend) {
            redisTemplate.opsForValue().set(phone,code,5, TimeUnit.MINUTES);
            return R.ok();
        } else {
            return R.error().message("短信发送失败");
        }
    }
}
