package com.qcy.userservice.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
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
@ApiModel(value="Orderitem对象", description="")
public class Orderitem implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "itemid", type = IdType.ID_WORKER_STR)
    private String itemid;

    private String pid;

    private String oid;

    private Integer count;


}
