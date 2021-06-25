package com.it.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.io.Serializable;

/**
 * 举报实体类
 *
 * @author itdragon
 */
@Data
@TableName("gm_report")
public class Report implements Serializable {
    /**
     * 自增长主键
     */
    @TableId(value = "id", type = IdType.UUID)
    private String id;
    /**
     * ptId
     */
    private String ptId;
    /**
     * 举报内容
     */
    private String content;
    /**
     * 举报时间
     */
    private String time;
    /**
     * 发布用户
     */
    private String userName;

}