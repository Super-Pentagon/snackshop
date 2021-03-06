package com.qcy.userservice.controller;




import com.qcy.commonutils.MD5;
import com.qcy.commonutils.R;
import com.qcy.userservice.entity.Buyer;
import com.qcy.userservice.service.BuyerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author CodeHunter_qcy
 * @since 2020-06-20
 */
@Api(tags = "买家登录注册接口")
@RestController
@RequestMapping("/buyerservice/buyer")
@CrossOrigin
public class BuyerController {
    @Autowired
    private BuyerService buyerService;
    @ApiOperation(value = "登录")
    @PostMapping("login")
    public R loginBuyer(@RequestBody Buyer buyer){
        Buyer buyervo = buyerService.login(buyer);
        return R.ok().data("buyer",buyervo);
    }
    @ApiOperation(value = "注册")
    @PostMapping("register")
    public R registerBuy(@RequestBody Buyer buyer){
        buyerService.register(buyer);
        return R.ok();
    }
    @ApiOperation(value = "退出登录")
    @GetMapping("exit")
    public R exit(String bid){
        buyerService.exit(bid);
        return R.ok();
    }
    @ApiOperation(value = "修改密码")
    @GetMapping("updatePswd/{bid}/{pswd}/{newpswd}")
    public R updatePswd(@PathVariable  String bid,@PathVariable String pswd,@PathVariable String newpswd){

        if (buyerService.getById(bid).getPassword().equals(MD5.encrypt(pswd))) {
            buyerService.updatePswd(bid, newpswd);
            return R.ok();

        }
        return R.error();
    }
    @ApiOperation(value = "根据id查用户")
    @GetMapping("getBuyerById/{bid}")
    public R getBuyerById(@PathVariable String bid){
        Buyer buyervo = buyerService.getById(bid);
        return R.ok().data("buyer",buyervo);
    }



}

