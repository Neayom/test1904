package edu.dlnu.edu_cms.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.dlnu.commonutils.R;
import edu.dlnu.edu_cms.entity.CrmBanner;
import edu.dlnu.edu_cms.service.CrmBannerService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 首页banner表 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-02-25
 */
@RestController
@RequestMapping("/educms/bannerAdmin")
public class BannerAdminController {
    @Autowired
    private CrmBannerService crmBannerService;

    //1. 分页查询banner
    @ApiOperation(value = "分页查询Banner")
    @GetMapping("pageBanner/{page}/{limit}")
    public R pageBanner(@PathVariable long page,@PathVariable long limit){
        Page<CrmBanner> pageBanner = new Page<>(page,limit);
        crmBannerService.page(pageBanner,null);
        return R.ok().data("total",pageBanner.getTotal()).data("records",pageBanner.getRecords());

    }

    //2. 通过id获取banner
    @ApiOperation(value = "通过id获取Banner")
    @GetMapping("get/{bannerId}")
    public R getById(@PathVariable String bannerId){
        System.out.println("通过id获取");
        CrmBanner crmbanner = crmBannerService.getById(bannerId);
        return R.ok().data("crmbanner",crmbanner);
    }

    //3.新增banner
    @ApiOperation(value = "新增Banner")
    @PostMapping("save")
    public R save(@RequestBody CrmBanner crmBanner){
        boolean save = crmBannerService.save(crmBanner);
        return save? R.ok():R.error();
    }

    //4.修改banner
    @ApiOperation(value = "修改Banner")
    @PutMapping("update")
    public R updateById(@RequestBody CrmBanner banner) {
        crmBannerService.updateById(banner);
        return R.ok();
    }

    @ApiOperation(value = "删除Banner")
    @DeleteMapping("remove/{id}")
    public R remove(@PathVariable String id) {
        crmBannerService.removeById(id);
        return R.ok();
    }
}

