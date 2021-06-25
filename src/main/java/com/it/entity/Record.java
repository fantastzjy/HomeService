package com.it.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.io.Serializable;

/**
 * 投票实体类
 *
 * @author itdragon
 */
@Data
@TableName("gm_record")
public class Record implements Serializable {
    /**
     * 自增长主键
     */
    @TableId(value = "id", type = IdType.UUID)
    private String id;
    /**
     * 活动id
     */
    private String ptId;
    /**
     * 活动名称
     */
    private String ptName;
    /**
     * 所投项
     */
    private String subName;
    /**
     * 投票用户
     */
    private String userName;
    /**
     * 投票时间
     */
    private String time;

}