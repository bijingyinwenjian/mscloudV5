package com.atguigu.cloud.controller;

import com.atguigu.cloud.entities.Pay;
import com.atguigu.cloud.entities.PayDTO;
import com.atguigu.cloud.resp.ResultData;
import com.atguigu.cloud.service.PayService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Tag(name = "支付接口")
public class PayController {

    @Resource
    private PayService payService;

    @PostMapping("/pay/add")
    @Operation(summary = "新增接口")
    public ResultData<String> addPay(@RequestBody PayDTO payDTO) {
        Pay pay = new Pay();
        BeanUtils.copyProperties(payDTO,pay);
        int add = payService.add(pay);
        return ResultData.success("成功插入记录,返回值：" + add);
    }

    @DeleteMapping("/pay/del/{id}")
    public ResultData<Integer> deletePay(@PathVariable("id") Integer id) {
        int i = 1 / 0;
        int delete = payService.delete(id);
        return ResultData.success(delete);
    }

    @PutMapping("/pay/update")
    public ResultData<String> updatePay(@RequestBody Pay pay) {
        int update = payService.update(pay);
        return ResultData.success("成功更新记录,返回值：" + update);
    }

    @GetMapping("/pay/get/{id}")
    public ResultData<PayDTO> getById(@PathVariable("id") Integer id) {
        Pay pay = payService.getById(id);
        PayDTO payDTO = new PayDTO();
        BeanUtils.copyProperties(pay,payDTO);
        return ResultData.success(payDTO);
    }

    @GetMapping("/pay/getAll")
    public ResultData<List<PayDTO>> getAll() {
        List<PayDTO> payDTOList = payService.getAll().stream().map(pay -> {
            PayDTO payDTO = new PayDTO();
            BeanUtils.copyProperties(pay, payDTO);
            return payDTO;
        }).collect(Collectors.toList());

        return ResultData.success(payDTOList);
    }

    @Value("${server.port}")
    private String port;

    @GetMapping(value = "/pay/get/info")
    private String getInfoByConsul(@Value("${atguigu.info}") String atguiguInfo) {
        return "atguiguInfo: " + atguiguInfo + "port: " + port;
    }
}
