package edu.dlnu.edu_cms.controller;


import edu.dlnu.commonutils.R;
import edu.dlnu.edu_cms.entity.CrmBanner;
import edu.dlnu.edu_cms.service.CrmBannerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Api(description = "网站首页Banner列表")

@RestController
@RequestMapping("/educms/bannerFront")
@CrossOrigin //跨域
public class BannerFrontController {
        @Autowired
        private CrmBannerService bannerService;
        //查询所有
        @ApiOperation(value = "获取首页banner")
        @GetMapping("getAllBanner")

        public R index() {
            //查询banner(根据id降序，查询前8条)
            List<CrmBanner> list = bannerService.selectIndexList();
            return R.ok().data("bannerList", list);
        }


}
