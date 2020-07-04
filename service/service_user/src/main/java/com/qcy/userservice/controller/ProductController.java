package com.qcy.userservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qcy.commonutils.R;
import com.qcy.userservice.entity.Product;
import com.qcy.userservice.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
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
    public R getAllProductList() {
        List<Product> products = productService.list(null);
        return R.ok().data("productlist", products);
    }

    @ApiOperation(value = "搜索商品")
    @GetMapping("getProductByStr/{key}")
    public R getProductByStr(@PathVariable String key) {
        QueryWrapper<Product> wrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(key)) {
            wrapper.like("pname", key);

        }
        List<Product> products = productService.list(wrapper);
        return R.ok().data("products", products);
    }
    @ApiOperation(value = "查询商品")
    @GetMapping("getProductById/{pid}")
    public R getProductById(@PathVariable String pid) {
        Product product = productService.getById(pid);
        return R.ok().data("product",product);
    }

    @ApiOperation(value = "卖家上架商品")
    @PostMapping("addProduct")
    public R addProduct(@RequestBody Product product) {
        boolean save = productService.save(product);
        if (save) {
            return R.ok();
        } else {
            return R.error();

        }
    }
    //修改
    @ApiOperation(value = "卖家修改商品")
    @PostMapping("updateProduct")
    public R updateProduct(@RequestBody Product product) {
        boolean flag = productService.updateById(product);
        if (flag) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    @ApiOperation(value = "卖家下架商品")
    @DeleteMapping("{pid}")
    public R removeProduct(@PathVariable String pid) {
        boolean flag = productService.removeById(pid);
        if (flag) {
            return R.ok();
        } else {
            return R.error();
        }
    }


}

