package com.qcy.userservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qcy.commonutils.MD5;
import com.qcy.servicebase.exceptionhandler.QcyException;
import com.qcy.userservice.entity.Buyer;
import com.qcy.userservice.entity.Seller;
import com.qcy.userservice.mapper.SellerMapper;
import com.qcy.userservice.service.SellerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author CodeHunter_qcy
 * @since 2020-06-20
 */
@Service
public class SellerServiceImpl extends ServiceImpl<SellerMapper, Seller> implements SellerService {

    @Override
    public Seller login(Seller seller) {
        String username = seller.getUsername();
        String password = seller.getPassword();
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            throw new QcyException(20001, "账号或者密码为空，登录失败！");
        }
        QueryWrapper<Seller> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        Seller sellervo= baseMapper.selectOne(wrapper);
        if (sellervo == null) {
            throw new QcyException(20001, "账号不存在");
        }
        if (!MD5.encrypt(password).equals(sellervo.getPassword())) {
            throw new QcyException(20001, "密码error");
        }
        return sellervo;
    }

    @Override
    public void register(Seller seller) {
        String username = seller.getUsername();
        String password = seller.getPassword();
        if (StringUtils.isEmpty(password)
                || StringUtils.isEmpty(username)) {
            throw new QcyException(20001, "注册失败");
        }

        QueryWrapper<Seller> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        Integer count = baseMapper.selectCount(wrapper);
        if (count > 0) {
            throw new QcyException(20001, "注册失败");
        }

        //数据添加数据库中
        Seller sellervo = new Seller();
        sellervo.setUsername(username);
        sellervo.setPassword(password);
        baseMapper.insert(sellervo);
    }
}
