package com.it.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.io.Serializable;

/**
 * 帖子实体类
 *
 * @author itdragon
 */
@Data
//@TableName("gm_postInfo")
@TableName("gm_postinfo")
public class PostInfo implements Serializable {
    /**
     * 自增长主键
     */
    @TableId(value = "id", type = IdType.UUID)
    private String id;
    /**
     * 标题
     */
    private String name;
    /**
     * 内容
     */
    private String content;
    /**
     * 内容
     */
    private String img;
    /**
     * 介绍
     */
    private String introduce;
    /**
     * 浏览数
     */
    private Integer pageView;
    /**
     * 评论数
     */
    private Integer observer;
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 用户id
     */
    private String userId;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 分类名
     */
    private String categoryName;
    /**
     * 状态
     */
    private String state;
    private Float price;
    private String idName;
    private String pass;
    /**
     * 赞的数目
     */
    @TableField(exist = false)
    private Integer likeNumber;

}