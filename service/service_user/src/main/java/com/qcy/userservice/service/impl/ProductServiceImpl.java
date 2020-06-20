package com.qcy.userservice.service.impl;

import com.qcy.userservice.entity.Product;
import com.qcy.userservice.mapper.ProductMapper;
import com.qcy.userservice.service.ProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author CodeHunter_qcy
 * @since 2020-06-20
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

}
