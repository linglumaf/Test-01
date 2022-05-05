package com.miao;


import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.ArrayList;

/**
 * Description:
 */
public class CodeGenerator {


    public static void main(String[] args) {

        //构建一个代码自动生成器对象
        AutoGenerator mpg = new AutoGenerator();

        //配置策略

        //1、全局配置
        GlobalConfig gc = new GlobalConfig();
        //当前项目的绝对路径（也就是当前工程，项目的父路径）
        String property = System.getProperty("user.dir");
        gc.setOutputDir(property + "/004-plus-test/src/main/java");
        gc.setAuthor("maf");
        gc.setOpen(false);
        gc.setFileOverride(false); //是否覆盖
        gc.setServiceName("%sService"); //去Service的I前缀
        gc.setIdType(IdType.ID_WORKER);
        gc.setDateType(DateType.ONLY_DATE);
        gc.setSwagger2(true);

        mpg.setGlobalConfig(gc);

        //2、设置数据源
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://localhost:3306/mybatis_plus?useUnicode=true&characterEncoding=UTF-8&serverTimeZone=GMT%2B8&allowMultiQueries=true");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("root");
        dsc.setDbType(DbType.MYSQL);
        mpg.setDataSource(dsc);


        //3、包的配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName("blog");
        pc.setParent("com.miao");
        pc.setEntity("pojo");
        pc.setMapper("mapper");
        pc.setService("service");
        pc.setController("controller");
        mpg.setPackageInfo(pc);


        //4、策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setInclude("user"); // 设置要映射的表名，可以一次多个
        strategy.setNaming(NamingStrategy.underline_to_camel);  //设置行列下划线转为驼峰
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);  // 自动lombok
        strategy.setLogicDeleteFieldName("deleted"); // 逻辑删除

        //自动填充
        TableFill createTime = new TableFill("create_time", FieldFill.INSERT);
        TableFill update_time = new TableFill("update_time", FieldFill.INSERT_UPDATE);
        ArrayList<TableFill> tableFills = new ArrayList<>();
        tableFills.add(createTime);
        tableFills.add(update_time);
        strategy.setTableFillList(tableFills);
        //乐观锁
        strategy.setVersionFieldName("version");
        strategy.setControllerMappingHyphenStyle(true); //开启，下划线映射模式localhost:8080/hello_id_2
        mpg.setStrategy(strategy);


        mpg.execute();

    }
}
