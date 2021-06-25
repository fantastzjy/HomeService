package com.it.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.io.Serializable;

/**
 * 登录注册设置实体类
 *
 * @author itdragon
 */
@Data
//@TableName("gm_loginReg")
@TableName("gm_loginreg")
public class LoginReg implements Serializable {
    /**
     * 自增长主键
     */
    @TableId(value = "id", type = IdType.UUID)
    private String id;
    /**
     * 登录背景图
     */
    private String logBackground;
    /**
     * 注册背景图
     */
    private String regBackground;
    /**
     * 注册时是否添加头像
     */
    private String isImg;
    /**
     * 注册时是否添加真实姓名
     */
    private String isRealName;
    /**
     * 注册时是否添加性别
     */
    private String isSex;
    /**
     * 注册时是否添加邮箱
     */
    private String isEmail;
    /**
     * 注册时是否添加地址
     */
    private String isAddress;
    /**
     * 注册时是否添加身份证号
     */
    private String isIdCard;
    /**
     * 注册时是否添加身份证号
     */
    private String isPhone;
}