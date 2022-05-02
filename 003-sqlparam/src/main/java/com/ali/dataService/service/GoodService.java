package com.ali.dataService.service;


import java.util.List;
import java.util.Map;

/**
 * Description:
 */
public interface GoodService {
    boolean updateGoodByList(List<Map<String, Integer>> goodList);
}
