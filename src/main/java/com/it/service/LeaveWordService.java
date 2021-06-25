package com.it.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.it.entity.Answering;
import com.it.entity.LeaveWord;

import java.util.List;

public interface LeaveWordService {
    /**
     * 分页查询
     *
     * @param leaveWord
     * @param page
     * @param limit
     * @return
     */
    Page<LeaveWord> selectPage(LeaveWord leaveWord, int page, int limit);

    /**
     * 新增
     *
     * @param leaveWord
     * @return
     */
    boolean insert(LeaveWord leaveWord);

    /**
     * 删除
     */
    boolean delById(String id);

    /**
     * 得到留言集合
     *
     * @return
     */
    List<LeaveWord> selectList();

    /**
     * 新增回复
     *
     * @param answering
     * @return
     */
    boolean insert(Answering answering);

    /**
     * 得到留言回复集合
     *
     * @return
     */
    List<Answering> selectListAnswering(String leaveId);

    /**
     * 删除回复
     */
    boolean delAnsweringById(String id);

    /**
     * 获取留言条数
     */
    Integer getLeaveWordNumber();

}
