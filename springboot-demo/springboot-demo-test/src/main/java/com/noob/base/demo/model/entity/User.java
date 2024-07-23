package com.noob.base.demo.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * User 实体类
 */
@Data
@TableName("t_user")
public class User {
    @TableId("id")
    private Integer id;

    @TableField("name")
    private String name;

    @TableField("age")
    private Integer age;

    public User(){}
    public User(Integer id,String name,Integer age){
        this.id = id;
        this.name = name;
        this.age = age;
    }
}
