package com.qcy.userservice.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author CodeHunter_qcy
 * @date 2020/7/3 - 23:05
 */

/**
 * 给前端的购物车信息
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ShoppingCart {
    private Product product;
    private int count;

    public ShoppingCart(Product product,int count){
        this.product = product;
        this.count = count;
    }
}
