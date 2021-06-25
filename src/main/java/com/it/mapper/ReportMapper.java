package com.it.mapper;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.it.entity.Report;
import org.apache.ibatis.annotations.Mapper;

@Mapper
/**
 * 数据访问层
 */
public interface ReportMapper extends BaseMapper<Report> {

}
