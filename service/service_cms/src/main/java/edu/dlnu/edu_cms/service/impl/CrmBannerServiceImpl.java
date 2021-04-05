package edu.dlnu.edu_cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import edu.dlnu.edu_cms.entity.CrmBanner;
import edu.dlnu.edu_cms.mapper.CrmBannerMapper;
import edu.dlnu.edu_cms.service.CrmBannerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;


import java.util.List;

/**
 * <p>
 * 首页banner表 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-02-25
 */
@Service
public class CrmBannerServiceImpl extends ServiceImpl<CrmBannerMapper, CrmBanner> implements CrmBannerService {

    //查询所有banner

    @Cacheable(value = "banner",key = "'selectIndexList'")
    @Override
    public List<CrmBanner> selectIndexList() {
        System.out.println("++++++");
        //根据id进行降序排序，显示排列后的前8条记录
        QueryWrapper<CrmBanner> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        //last方法,拼接sql语句
        queryWrapper.last("limit 2");
        List<CrmBanner> crmBanners = baseMapper.selectList(queryWrapper);
        return crmBanners;
    }
}
