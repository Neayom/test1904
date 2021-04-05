package edu.dlnu.edu_cms.service;

import edu.dlnu.edu_cms.entity.CrmBanner;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务类
 * </p>
 *
 * @author testjava
 * @since 2021-02-25
 */
public interface CrmBannerService extends IService<CrmBanner> {
   List<CrmBanner> selectIndexList();

}
