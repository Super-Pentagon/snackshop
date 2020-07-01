package com.qcy.userservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qcy.commonutils.R;
import com.qcy.userservice.entity.Orderitem;
import com.qcy.userservice.entity.Orders;
import com.qcy.userservice.entity.Product;
import com.qcy.userservice.service.OrderitemService;
import com.qcy.userservice.service.OrdersService;
import com.qcy.userservice.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
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
            Product product =productService.getById(pid);
            BigDecimal price = product.getPrice();
            int n = list.get(i).getCount();
            BigDecimal num = new BigDecimal(n);
            total = total.add(price.multiply(num));
            wrapper.eq("itemid",list.get(i).getItemid());
            orderitemService.update(list.get(i),wrapper);
        }
        orders.setState(0);
        orders.setAddress("默认地址");
        orders.setTel("13000000000");
        orders.setTotal(total);
        orders.setTime(LocalDateTime.now());
        orders.setBid("1111");
        wrapper1.eq("oid",oid);
        ordersService.update(orders,wrapper1);
        return R.ok().data("orders",orders);
    }

    /**
     * 支付成功订单state 0变1
     * @param oid
     * @return
     */
    @ApiOperation(value = "支付订单")
    @GetMapping("payOrder/{oid}")
    private R payOrder(@PathVariable String oid){
        QueryWrapper<Orders> wrapper = new QueryWrapper<>();
        Orders orders=ordersService.getById(oid);
        wrapper.eq("oid",oid);
        orders.setState(1);
        ordersService.update(orders,wrapper);
    return R.ok();
    }
}

