package com.ali.dataService.service.impl;

import com.ali.dataService.mapper.GoodMapper;
import com.ali.dataService.service.GoodService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description:
 */
@Service
public class GoodMapperImpl implements GoodService {

    @Resource
    private GoodMapper goodMapper;

    @Override
    public boolean updateGoodByList(List<Map<String, Integer>> goodList) {

        Map<Integer, Integer> map = new HashMap<>();
        for (Map<String, Integer> stringIntegerMap : goodList) {
            map.put(stringIntegerMap.get("goodId"),stringIntegerMap.get("number"));
        }

        Integer number = goodMapper.updateGoodByList(map);
        System.out.println("本次更新了" + number + "条数据");
        if(number == 1){
            return true;
        }
        return false;

    }
}
