package com.miao.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @TableId(type = IdType.ID_WORKER)
    private Long id;
    private String name;
    private Integer age;
    private String email;

    @Version
    private int version;

    @TableLogic
    private int deleted;

    @TableField(value = "create_time",fill = FieldFill.INSERT)
    private Date createTime;

    //插入时也要添加数据
    @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
}
