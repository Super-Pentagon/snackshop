package com.qcy.userservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qcy.commonutils.MD5;
import com.qcy.servicebase.exceptionhandler.QcyException;
import com.qcy.userservice.entity.Buyer;
import com.qcy.userservice.mapper.BuyerMapper;
import com.qcy.userservice.service.BuyerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author CodeHunter_qcy
 * @since 2020-06-20
 */
@Service
public class BuyerServiceImpl extends ServiceImpl<BuyerMapper, Buyer> implements BuyerService {

    @Override
    public Buyer login(Buyer buyer) {
        String username = buyer.getUsername();
        String password = buyer.getPassword();
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            throw new QcyException(20001, "账号或者密码为空，登录失败！");
        }
        QueryWrapper<Buyer> wrapper = new QueryWrapper<>();
        wrapper.eq("username",username);
        Buyer buyervo =baseMapper.selectOne(wrapper);
        if (buyervo==null){
            throw new QcyException(20001, "账号不存在");
        }
        if (!MD5.encrypt(password).equals(buyervo.getPassword())){
            throw new QcyException(20001, "密码error");
        }
        return buyervo;
    }
}
