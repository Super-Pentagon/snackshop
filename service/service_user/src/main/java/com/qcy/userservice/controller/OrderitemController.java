package com.qcy.userservice.controller;


import com.qcy.commonutils.R;
import com.qcy.userservice.entity.Orderitem;
import com.qcy.userservice.entity.Product;
import com.qcy.userservice.entity.ShoppingCart;
import com.qcy.userservice.service.OrderitemService;
import com.qcy.userservice.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 前端控制器
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
    private ProductService productService;
    @Autowired
    private OrderitemService orderitemService;

    private List<ShoppingCart> cartList = new ArrayList<>();

    @ApiOperation(value = "加入购物车")
    @GetMapping("addOrderitem/{pid}/{count}")
    public R addOrderitem(@PathVariable String pid, @PathVariable int count) {
        if (pid == null) {
            return R.error();
        }
        Product product = productService.getById(pid);
        ShoppingCart shoppingCart = new ShoppingCart(product, count);
        cartList.add(shoppingCart);
        Orderitem orderitem = new Orderitem();
        orderitem.setCount(count);
        orderitem.setPid(pid);
        orderitemService.save(orderitem);
        return R.ok();
    }

    @ApiOperation(value = "购物车列表")
    @GetMapping
    public R showShoppingCart() {

        return R.ok().data("cartList",cartList);
    }

}

