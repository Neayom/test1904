package edu.dlnu.edu_ucenter.service;

import edu.dlnu.edu_ucenter.entity.RegisterVo;
import edu.dlnu.edu_ucenter.entity.UcenterMember;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author testjava
 * @since 2021-03-04
 */
public interface UcenterMemberService extends IService<UcenterMember> {

    String login(UcenterMember ucenterMember);

    void register(RegisterVo registerVo);
}
