package edu.dlnu.edu_ucenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import edu.dlnu.commonutils.JwtUtils;
import edu.dlnu.commonutils.MD5;
import edu.dlnu.edu_ucenter.entity.RegisterVo;
import edu.dlnu.edu_ucenter.entity.UcenterMember;
import edu.dlnu.edu_ucenter.mapper.UcenterMemberMapper;
import edu.dlnu.edu_ucenter.service.UcenterMemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.dlnu.servicebase.ExceptionHandler.GuliException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-03-04
 */
@Service
public class UcenterMemberServiceImpl extends ServiceImpl<UcenterMemberMapper, UcenterMember> implements UcenterMemberService {
    @Autowired
    private  RedisTemplate redisTemplate;
    @Override
    public String login(UcenterMember ucenterMember) {
        //ucenterMember只有手机号和密码
        //获取登录手机号和密码
        String mobile = ucenterMember.getMobile();
        String password = ucenterMember.getPassword();
        if(StringUtils.isEmpty(mobile)||StringUtils.isEmpty(password)){
            throw new GuliException(20001,"登录失败");
        }
        //判断手机号是否正确
        QueryWrapper<UcenterMember> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("mobile",mobile);
        UcenterMember mobileMember = baseMapper.selectOne(queryWrapper);
        if(mobileMember == null){
            //数据库中没有这个手机号
            throw new GuliException(20001,"登陆失败");
        }
        //判断密码

        if(!MD5.encrypt(password).equals(mobileMember.getPassword())){
            throw new GuliException(20001,"登陆失败");
        }
        //判断用户是否禁用
        if(mobileMember.getIsDeleted()){
            throw new GuliException(20001,"登陆失败");
        }
        //登陆成功
        //生成token字符串，使用jwt工具
            String jwtToken = JwtUtils.getJwtToken(mobileMember.getId(), mobileMember.getNickname());
            return jwtToken;

    }

    @Override
    public void register(RegisterVo registerVo) {
        String code = registerVo.getCode();//获取验证码
        String mobile = registerVo.getMobile();
        String nickname = registerVo.getNickname();
        String password = registerVo.getPassword();
        if(StringUtils.isEmpty(code)||StringUtils.isEmpty(mobile)||StringUtils.isEmpty(nickname)||
                StringUtils.isEmpty(password)){
            throw new GuliException(20001,"不能为空");
        }
        //判断验证码
        String rediscode = (String)redisTemplate.opsForValue().get(mobile);
        if(!code.equals(rediscode)){
            throw new GuliException(20001,"code与redis中的不对呀,或者失效了5分钟");
        }
        //判断手机号是否重复
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("mobile", mobile);
        Integer count = baseMapper.selectCount(queryWrapper);
        //count==0 则数据库中还没有,count==1 则数据库中有
        if(count>0){
            throw new GuliException(20001,"手机号重复");
        }
        //最终添加
        UcenterMember ucenterMember = new UcenterMember();
        ucenterMember.setMobile(mobile);
        ucenterMember.setNickname(nickname);
        ucenterMember.setIsDisabled(false);
        ucenterMember.setPassword(MD5.encrypt(password));//密码需要加密
        ucenterMember.setAvatar("http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJ9CsqApybcs7f3Dyib9IxIh0sBqJb7LicbjU4WticJFF0PVwFvHgtbFdBwfmk3H2t3NyqmEmVx17tRA/132");
        baseMapper.insert(ucenterMember);

    }
}
