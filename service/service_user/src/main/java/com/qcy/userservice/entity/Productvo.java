package com.qcy.userservice.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * @author CodeHunter_qcy
 * @date 2020/7/5 - 16:25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Product对象给搜索页面", description="")
public class Productvo {
    private String id;//商品id
    //商品价格
    private BigDecimal price;
    //卖家id
    private String sellerId;
    //商品描述
    private String des;
    //图
    private String pictureUrl;
    //缩略图
    private String thumbnailUrl;
    //商品名称
    private String name;
    //月销售
    private int monthlySales;
    //
    private int deliveryTime;
}
