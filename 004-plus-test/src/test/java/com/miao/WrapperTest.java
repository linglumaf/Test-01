package com.miao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.miao.dao.UserMapper;
import com.miao.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * Description:
 */
@SpringBootTest
public class WrapperTest {

    @Resource
    private UserMapper userMapper;

    //测试wrapper
    @Test
    public void test01(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        QueryWrapper<User> queryWrapper = wrapper.isNotNull("name")
                // 使用like时，是%***%，是可以模糊查的
                // likeLeft 时， %***， 则后边一定是以***结尾的，右查询同样，***%，开头一定是以***开始的
                .like("email", "qq")
                .orderByAsc("id");
        userMapper.selectList(queryWrapper).forEach(System.out::println);

    }

    @Test
    public void test02(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        QueryWrapper<User> queryWrapper = wrapper.eq("name","李四");
        System.out.println(userMapper.selectOne(queryWrapper));

    }

    @Test
    public void test03(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        QueryWrapper<User> queryWrapper = wrapper.inSql("id","select id from user where id < 4");
        userMapper.selectList(queryWrapper).forEach(System.out::println);
    }




}
