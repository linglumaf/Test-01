package com.ali.controller;

import com.ali.dataService.service.GoodService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Description:
 */
@RestController
public class GoodController {


    @Resource
    private GoodService goodService;
    /**
     *  list+map 形式： {“1”，6}，{“2”，5} 一号产品六个，二号产品五个，
     */
    @RequestMapping("/good/test")
    public String TestGood(@RequestBody List<Map<String,Integer>> goodList){

        //直接通车，不进行验证
        boolean flag = goodService.updateGoodByList(goodList);

        return "删除对应数据成功";
    }
}
