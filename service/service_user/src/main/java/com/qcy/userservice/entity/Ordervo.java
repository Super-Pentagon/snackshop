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
 * @date 2020/7/1 - 22:59
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "Orders给前端的对象", description = "")
public class Ordervo {

    private String oid;

    private LocalDateTime time;

    private Buyer buyer;

    private BigDecimal total;

    private Integer state;

    private String tel;

    private String address;

    List<OrderItemvo> orderItemvos = new ArrayList<>();
}

