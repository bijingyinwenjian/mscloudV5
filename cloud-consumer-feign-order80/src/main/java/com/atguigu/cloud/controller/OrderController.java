package com.atguigu.cloud.controller;

import com.atguigu.cloud.apis.PayFeignApi;
import com.atguigu.cloud.entities.PayDTO;
import com.atguigu.cloud.resp.ResultData;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @Resource
    private PayFeignApi payFeignApi;

    @GetMapping("/consumer/pay/add")
    public ResultData addOrder(PayDTO payDTO) {
        return payFeignApi.addPay(payDTO);
    }

    @GetMapping("/consumer/pay/get/{id}")
    public ResultData getPayInfo(@PathVariable("id") Integer id) {
        return payFeignApi.getById(id);
    }

    @GetMapping(value = "/consumer/pay/get/info")
    private String getInfoByConsul() {
        return payFeignApi.getInfoByConsul();
    }
}
