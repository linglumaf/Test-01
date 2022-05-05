package com.miao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.miao.dao.UserMapper;
import com.miao.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class ApplicationTests {

    @Resource
    private UserMapper userMapper;

    @Test
    void testSql() {
        List<User> userList = userMapper.selectList(null);
        userList.forEach(System.out::println);
    }

    @Test
    public void testInsert(){

        //自动生成主键(UUID，自增id，雪花算法，redis，zookeeper)
        //由于王五，赵六，id属性更改为long类型，使用的是雪花算法
        //张三，李四，使用的id是integer类型，插入的类型也是Integer类型
        User user = new User();
        user.setName("陆十一");
        user.setAge(10);
        user.setEmail("asgwe@qq.com");
        int result = userMapper.insert(user);
        System.out.println(result);
        System.out.println(user);
    }

    @Test
    public void testUpdate(){
        //自动拼接SQL动态语句
        User user = new User();
        user.setId(1521867598092840961l);
        user.setName("测试更新01");
        user.setEmail("test@qq.com");
        int i = userMapper.updateById(user);
        System.out.println(i);
    }

    @Test
    public void testLeLock(){
        //测试乐观锁时，要先进行查询，后更新，才会更新版本号，否则直接更新，则更新失败，因为版本号默认是0
        User user = userMapper.selectById(1l);
        user.setName("测试乐观锁55");
        System.out.println(user);

        //中途插入一条线程
        User user1 = userMapper.selectById(1l);
        user1.setName("测试乐观锁66");
        int result2 = userMapper.updateById(user1);

        //这里应该更新失败(自旋锁多次尝试提交)
        int result1 = userMapper.updateById(user);
        //且这个user和上面的user版本号不一样，上面是未更新过的，这里是已更新过的
        System.out.println(user);

    }


    //单条查询
    @Test
    public void testSelect(){
        User user = userMapper.selectById(1l);
        System.out.println(user);
    }

    //批量查询
    @Test
    public void testSelectBatch(){
        List<User> users = userMapper.selectBatchIds(Arrays.asList(1, 2, 3));
        users.forEach(System.out::println);
    }

    //按照条件map，简单查询
    @Test
    public void testSelectMap(){
        Map<String,Object> map = new HashMap<>();
        map.put("email","asgwe@qq.com");
        map.put("age",10);
        List<User> users = userMapper.selectByMap(map);
        users.forEach(System.out::println);
    }

    //测试分页查询
    @Test
    public void testLimit(){
        //limit 是 limit (pageNo-1)*pageSize , pageSize 其实就是从第n个，向后数x个
        Page<User> page = new Page<>(2,3);
        long current = userMapper.selectPage(page, null).getCurrent();
        long total = userMapper.selectPage(page, null).getTotal();
        long size = userMapper.selectPage(page, null).getSize();
        long pages = userMapper.selectPage(page, null).getPages();
        System.out.println(current); //当前页，这里就是第2页
        System.out.println(total);   //总条数，16
        System.out.println(size);    //当前页显示的记录数，这是3
        System.out.println(pages);   //共有多少页，6页
    }

    //删除测试
    @Test
    public void testDelete(){
        userMapper.deleteById(-2080374782l);
    }

    //批量删除
    @Test
    public void testDeleteBatch(){
        userMapper.deleteBatchIds(Arrays.asList(1521867598092840961l,1521870028398522369l));
    }

    //逻辑删除
    @Test
    public void testLogicDelete(){
       userMapper.deleteById(5);
       userMapper.selectById(5);
    }

    //测试性能分析
    @Test
    public void testFenxi(){
        userMapper.selectList(null);
    }



}
