package com.qcy.userservice.controller;


import com.qcy.commonutils.R;
import com.qcy.userservice.entity.Product;
import com.qcy.userservice.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author CodeHunter_qcy
 * @since 2020-06-20
 */
@Api(tags = "商品接口")
@CrossOrigin
@RestController
@RequestMapping("/userservice/product")
public class ProductController {
    @Autowired
    private ProductService productService;
    @ApiOperation(value = "查询所有商品")
    @GetMapping
    public R getAllProductList(){
        List<Product> products = productService.list(null);
        return R.ok().data("productlist",products);
    }


}

