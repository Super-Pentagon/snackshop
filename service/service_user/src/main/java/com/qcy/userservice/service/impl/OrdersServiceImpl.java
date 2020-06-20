package com.qcy.userservice.service.impl;

import com.qcy.userservice.entity.Orders;
import com.qcy.userservice.mapper.OrdersMapper;
import com.qcy.userservice.service.OrdersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author CodeHunter_qcy
 * @since 2020-06-20
 */
@Service
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders> implements OrdersService {

}
