package com.cnki.common.entity;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "user")
public class User extends BaseEntity{

    private String id;

    private String name;

    private String salt;

    private String password;

    private String email;

    private String phone;

    @TableLogic
    private Integer deleted;

}
