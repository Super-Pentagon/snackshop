package com.qcy.userservice.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author CodeHunter_qcy
 * @since 2020-06-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Product对象", description="")
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "pid", type = IdType.ID_WORKER_STR)
    private String pid;//商品id
    //商品价格
    private BigDecimal price;
    //卖家id
    private String sid;
    //商品描述
    private String des;
    //图
    private String purl;
    //缩略图
    private String thumbnailurl;
    //商品名称
    private String pname;
    //月销售
    private int monthlysales;



}
