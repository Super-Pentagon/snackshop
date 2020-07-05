package com.qcy.userservice.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author CodeHunter_qcy
 * @date 2020/7/5 - 18:52
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "Orders给前端支付的对象", description = "")
public class Orderpayvo {

    private BigDecimal total;

    private String tel;

    private String address;

    List<Orderitem> orderitemlist= new ArrayList<>();
}
