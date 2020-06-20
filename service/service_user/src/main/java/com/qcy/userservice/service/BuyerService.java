package com.qcy.userservice.service;

import com.qcy.userservice.entity.Buyer;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author CodeHunter_qcy
 * @since 2020-06-20
 */
public interface BuyerService extends IService<Buyer> {

    Buyer login(Buyer buyer);
}
