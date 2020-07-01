package com.qcy.userservice.controller;


import com.qcy.commonutils.R;
import com.qcy.userservice.entity.Orderitem;
import com.qcy.userservice.entity.Product;
import com.qcy.userservice.service.OrderitemService;
import com.qcy.userservice.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author CodeHunter_qcy
 * @since 2020-06-20
 */
@Api(tags = "提交商品接口(提交到购物车)")
@RestController
@RequestMapping("/userservice/orderitem")
public class OrderitemController {
    @Autowired
    private OrderitemService orderitemService;

    @ApiOperation(value = "加入购物车")
    @GetMapping
    public R addOrderitem(String pid,int count){
        Orderitem orderitem = new Orderitem();
        orderitem.setCount(count);
        orderitem.setPid(pid);

        orderitemService.save(orderitem);
        return R.ok();
    }

}

