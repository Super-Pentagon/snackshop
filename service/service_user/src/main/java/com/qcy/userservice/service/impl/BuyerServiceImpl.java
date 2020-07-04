package com.qcy.userservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
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
        wrapper.eq("username", username);
        Buyer buyervo = baseMapper.selectOne(wrapper);
        if (buyervo == null) {
            throw new QcyException(20001, "账号不存在");
        }
        if (!MD5.encrypt(password).equals(buyervo.getPassword())) {
            throw new QcyException(20001, "密码error");
        }
        UpdateWrapper<Buyer> buyerUpdateWrapper = new UpdateWrapper<>();
        buyerUpdateWrapper.eq("username",username).set("state",1);
        return buyervo;
    }

    @Override
    public void register(Buyer buyer) {
        String username = buyer.getUsername();
        String password = buyer.getPassword();
        System.out.println(username+"     " +password);
        if (StringUtils.isEmpty(password)
                || StringUtils.isEmpty(username)) {
            throw new QcyException(20001, "注册失败");
        }

        QueryWrapper<Buyer> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        Integer count = baseMapper.selectCount(wrapper);
        if (count > 0) {
            throw new QcyException(20001, "注册失败,账号已存在");
        }

        //数据添加数据库中
        Buyer buyervo = new Buyer();
        buyervo.setUsername(username);
        buyervo.setPassword(MD5.encrypt(password));
        buyervo.setAvatar("http://bpic.588ku.com/element_pic/01/58/79/95574840a476616.jpg");
        baseMapper.insert(buyervo);
    }

    @Override
    public void exit(String bid) {
        UpdateWrapper<Buyer> buyerUpdateWrapper = new UpdateWrapper<>();
        buyerUpdateWrapper.eq("bid",bid).set("state",0);
    }
}
