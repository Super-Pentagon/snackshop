package com.qcy.userservice.controller;


import com.qcy.commonutils.R;
import com.qcy.userservice.entity.Buyer;
import com.qcy.userservice.entity.Seller;
import com.qcy.userservice.service.BuyerService;
import com.qcy.userservice.service.SellerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author CodeHunter_qcy
 * @since 2020-06-20
 */
@Api(tags = "卖家登录注册接口")
@RestController
@RequestMapping("/userservice/seller")
@CrossOrigin
public class SellerController {
    @Autowired
    private SellerService sellerService;
    @ApiOperation(value = "登录")
    @PostMapping("login")
    public R loginBuyer(@RequestBody Seller seller){
        Seller sellervo = sellerService.login(seller);
        return R.ok().data("sellervo",sellervo);
    }
    @ApiOperation(value = "注册")
    @PostMapping("register")
    public R registerBuy(@RequestBody Seller seller){
        sellerService.register(seller);
        return R.ok();
    }
}

