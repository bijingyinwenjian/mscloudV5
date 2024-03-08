package com.atguigu.cloud.apis;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("cloud-payment-service")
public interface PayFeignApi {

}
