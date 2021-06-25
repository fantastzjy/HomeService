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
@TableName("gm_order")
public class Order implements Serializable {
    /**
     * 自增长主键
     */
    @TableId(value = "id", type = IdType.UUID)
    private String id;
    /**
     * 账号id
     */
    private String postId;
    private String category;
    private Integer hours;
    /**
     * 总价
     */
    private Float totalPrice;
    /**
     * 单价
     */
    private Float price;

    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 用户名
     */
    private String userName;
    @TableField(exist = false)
    private PostInfo postInfo;


}