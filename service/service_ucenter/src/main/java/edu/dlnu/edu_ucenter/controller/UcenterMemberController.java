package edu.dlnu.edu_ucenter.controller;


import edu.dlnu.commonutils.JwtUtils;
import edu.dlnu.commonutils.R;
import edu.dlnu.edu_ucenter.entity.RegisterVo;
import edu.dlnu.edu_ucenter.entity.UcenterMember;
import edu.dlnu.edu_ucenter.service.UcenterMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-03-04
 */
@RestController
@RequestMapping("/edu_ucenter/ucenter-member")
public class UcenterMemberController {
    @Autowired
    private UcenterMemberService memberService;

    //登录
    @PostMapping("login")
    public R login(@RequestBody UcenterMember ucenterMember){
        //调用service方法实现登录
        //返回token值
        String token = memberService.login(ucenterMember);
        return R.ok().data("token",token);
    }
    //注册
    @PostMapping("register")
    public R register(@RequestBody RegisterVo registerVo){
        memberService.register(registerVo);
        return R.ok();
    }
    //通过token获取用户信息,以便在前端头像处显示
    @GetMapping("getUserInfoByToken")
    public R getUserInfoByToken(HttpServletRequest request){
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        UcenterMember userInfo = memberService.getById(memberId);
        return R.ok().data("userInfo",userInfo);
    }

}

