package com.qcy.userservice.service;

import com.qcy.userservice.entity.Buyer;
import com.qcy.userservice.entity.Seller;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author CodeHunter_qcy
 * @since 2020-06-20
 */
public interface SellerService extends IService<Seller> {

    Seller login(Seller seller);

    void register(Seller seller);

    void updatePswd(String bid, String newpswd);
}
