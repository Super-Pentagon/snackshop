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

import java.math.BigDecimal;
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
     * @param list
     * @return
     */
    @ApiOperation(value = "提交订单")
    @RequestMapping(value = "add", method = RequestMethod.POST, consumes = "application/json")
    public R addOrder(@RequestBody List<Orderitem> list) {


        QueryWrapper<Orderitem> wrapper = new QueryWrapper<>();
        QueryWrapper<Orders> wrapper1 = new QueryWrapper<>();
        Orders orders = new Orders();
        ordersService.save(orders);
        String oid = orders.getOid();
        BigDecimal total = new BigDecimal("0.00");
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setOid(oid);
            String pid = list.get(i).getPid();
            Product product = productService.getById(pid);
            BigDecimal price = product.getPrice();
            int n = list.get(i).getCount();
            BigDecimal num = new BigDecimal(n);
            total = total.add(price.multiply(num));
            wrapper.eq("itemid", list.get(i).getItemid());
            orderitemService.update(list.get(i), wrapper);
        }
        orders.setState(0);
        orders.setAddress("默认地址");
        orders.setTel("13000000000");
        orders.setTotal(total);
        orders.setTime(LocalDateTime.now());
        orders.setBid("1111");
        wrapper1.eq("oid", oid);
        ordersService.update(orders, wrapper1);
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
    private R payOrder(@PathVariable String oid) {
        QueryWrapper<Orders> wrapper = new QueryWrapper<>();
        Orders orders = ordersService.getById(oid);
        wrapper.eq("oid", oid);
        orders.setState(1);
        ordersService.update(orders, wrapper);
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

