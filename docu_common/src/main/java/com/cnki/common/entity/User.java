package com.cnki.common.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@TableName(value = "user")
public class User extends BaseEntity{

    @NotNull
    @NotBlank(message = "账号不得为空")
    private String id;

    @NotNull
    @NotBlank(message = "名称不得为空")
    private String name;

    private String salt;

    @NotNull
    @NotBlank(message = "密码不得为空")
    @Length(min = 6, message = "密码长度必须大于6")
    private String password;

    @Email(message = "邮箱格式不符合要求")
    private String email;

    @NotBlank(message = "手机号码不能为空")
    @NotNull(message = "手机号不能为空")
    @Length(min = 11, max = 11, message = "手机号只能为11位")
    @Pattern(regexp = "^[1][3,4,5,6,7,8,9][0-9]{9}$", message = "手机号格式有误")
    private String phone;

    @TableField(value = "deleted", fill = FieldFill.INSERT)
    private Integer deleted;

}
