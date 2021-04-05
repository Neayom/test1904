package edu.dlnu.edu_service.controller;

import edu.dlnu.commonutils.R;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

@Api(description = "登录管理")
@RestController//Responsbody，用于响应数据，一般返回json,RequestBody使用json传递数据，把json数据封装到对应对象里面，需要改成post提交
@RequestMapping("/eduservice/user")
@CrossOrigin
public class EduLoginController {
    //login
    @PostMapping("login")
    public R login(){
        return R.ok().data("token","admin");
    }
    //info
    @GetMapping("info")
    public R info(){
        return R.ok().data("roles","[admin]").data("name","admin").data("avatar","https://image.baidu.com/search/detail?ct=503316480&z=0&ipn=false&word=%E5%A4%B4%E5%83%8F&hs=0&pn=1&spn=0&di=124110&pi=0&rn=1&tn=baiduimagedetail&is=0%2C0&ie=utf-8&oe=utf-8&cl=2&lm=-1&cs=728127551%2C664296612&os=1557403125%2C3973522&simid=0%2C0&adpicid=0&lpn=0&ln=30&fr=ala&fm=&sme=&cg=&bdtype=11&oriquery=%E5%A4%B4%E5%83%8F&objurl=https%3A%2F%2Fgimg2.baidu.com%2Fimage_search%2Fsrc%3Dhttp%3A%2F%2Fc-ssl.duitang.com%2Fuploads%2Fitem%2F202006%2F27%2F20200627081338_hxcdu.thumb.400_0.jpeg%26refer%3Dhttp%3A%2F%2Fc-ssl.duitang.com%26app%3D2002%26size%3Df9999%2C10000%26q%3Da80%26n%3D0%26g%3D0n%26fmt%3Djpeg%3Fsec%3D1615435871%26t%3Dc3c97050bef50010fb3ecde53e813473&fromurl=ippr_z2C%24qAzdH3FAzdH3Fooo_z%26e3B17tpwg2_z%26e3Bv54AzdH3Fks52fAzdH3Fpw2AzdH3F%3Fgw4j%3D%25E0%25lB%25B9%25Ec%25bF%25l8%25Ec%25Ac%25Bn%26fpw6p%3D880m%26st4tp%3Dd9&gsm=2&islist=&querylist=");
    }
}
