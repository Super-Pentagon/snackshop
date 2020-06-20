package com.qcy.userservice.controller;


import com.qcy.userservice.entity.Buyer;
import com.qcy.userservice.service.BuyerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author CodeHunter_qcy
 * @since 2020-06-20
 */
@RestController
@RequestMapping("/buyerservice/buyer")
public class BuyerController {
    @Autowired
    private BuyerService buyerService;

    @GetMapping("findAll")
    public List<Buyer> findAll() {
    List<Buyer> list = buyerService.list(null);
    return list;

    }

}

