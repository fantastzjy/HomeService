package com.it.mapper;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.it.entity.Record;
import org.apache.ibatis.annotations.Mapper;

@Mapper
/**
 * 数据访问层
 */
public interface RecordMapper extends BaseMapper<Record> {

}
