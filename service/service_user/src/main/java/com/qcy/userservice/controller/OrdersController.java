package com.qcy.userservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qcy.commonutils.R;
import com.qcy.userservice.entity.*;
import com.qcy.userservice.service.OrderitemService;
import com.qcy.userservice.service.OrdersService;
import com.qcy.userservice.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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
@Api(tags = "订单接口")
@RestController
@RequestMapping("/userservice/orders")
@CrossOrigin
public class OrdersController {
    @Autowired
    private OrderitemService orderitemService;
    @Autowired
    private OrdersService ordersService;
    @Autowired
    private ProductService productService;

    /**
     * 只有提交订单的适合，才去set属性oid的值
     *
     * @param
     * @return
     */
    @ApiOperation(value = "提交订单")
    @PostMapping("/addOrder")
    public R addOrder(@RequestBody Orderpayvo orderpayvo) {
        QueryWrapper<Orders> wrapper = new QueryWrapper<>();
        Orders orders = new Orders();
        orders.setTel(orderpayvo.getTel());
        orders.setAddress(orderpayvo.getAddress());
        orders.setTotal(orderpayvo.getTotal());
        orders.setBid("1279088273653854209");
        orders.setTime(LocalDateTime.now());
        orders.setState(0);
        //先创建订单
        ordersService.save(orders);
        String oid = orders.getOid();
        List<Orderitem> orderitemlist = orderpayvo.getOrderitemlist();
        for (Orderitem o:orderitemlist){
            //与订单绑定
            o.setOid(oid);
            // 写订单项入表
            orderitemService.save(o);
        }
        wrapper.eq("oid", oid);
        ordersService.update(orders, wrapper);
        return R.ok().data("orders", orders);
    }

    /**
     * @param oid
     * @return
     */
    @ApiOperation(value = "删除订单")
    @DeleteMapping("{oid}")
    public R removeOrder(@PathVariable String oid) {
        boolean flag = ordersService.removeById(oid);
        if (flag) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    /**
     * 支付成功订单state 0变1
     *
     * @param oid
     * @return
     */
    @ApiOperation(value = "支付订单")
    @GetMapping("payOrder/{oid}")
    public R payOrder(@PathVariable String oid) {
        Orders orders = ordersService.getById(oid);
        System.out.println(orders.getOid());
        orders.setState(1);
        ordersService.updateById(orders);
        return R.ok();
    }

    /**
     * @return
     */
    @ApiOperation(value = "订单列表(给我用户id)")
    @GetMapping("getOrderList/{bid}")
    public R getOrderList(@PathVariable String bid) {
        System.out.println(bid);
        QueryWrapper<Orders> wrapper = new QueryWrapper<>();
        wrapper.eq("bid", bid);
        //给前端的vo
        List<Ordervo> list = new ArrayList<>();
        //查询订单
        List<Orders> ordersList = ordersService.list(wrapper);
        System.out.println(ordersList);
        //循环，往每一个订单封装订单项
        for (Orders orders : ordersList) {
            String oid = orders.getOid();
            QueryWrapper<Orderitem> orderitemWrapper  = new QueryWrapper<>();
            orderitemWrapper.eq("oid",oid);
            //根据oid拿到订单项

            List<Orderitem> orderitemList = orderitemService.list(orderitemWrapper);

            List<OrderItemvo> orderItemvoList =new ArrayList<>();
            //根据订单项的pid，封装product
            for (Orderitem oi:orderitemList){
            Product product = productService.getById(oi.getPid());
            OrderItemvo orderItemvo = new OrderItemvo();
            orderItemvo.setProduct(product);
            orderItemvo.setCount(oi.getCount());
            orderItemvo.setSubtotal(oi.getSubtotal());
            orderItemvoList.add(orderItemvo);
            }
            // 组装
            Ordervo ordervo = new Ordervo();
            ordervo.setOrderItemvos(orderItemvoList);
            ordervo.setAddress(orders.getAddress());
            ordervo.setState(orders.getState());
            ordervo.setTel(ordervo.getTel());
            ordervo.setTime(orders.getTime());
            ordervo.setTotal(orders.getTotal());

            list.add(ordervo);
        }
        return R.ok().data("list", list);
    }
}

