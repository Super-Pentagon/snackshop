package com.qcy.userservice.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author CodeHunter_qcy
 * @date 2020/7/3 - 23:57
 */
@Data
public class OrderItemvo {

    private String itemid;

    private Integer count;

    private BigDecimal subtotal;

    private Product product;


}
