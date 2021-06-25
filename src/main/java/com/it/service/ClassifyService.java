package com.it.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.it.entity.ChdClassify;
import com.it.entity.Classify;

import java.util.List;
import java.util.Map;

public interface ClassifyService {
    /**
     * 分页查询
     *
     * @param classify
     * @param page
     * @param limit
     * @return
     */
    Page<Classify> selectPage(Classify classify, int page, int limit);

    /**
     * 新增
     *
     * @param classify
     * @return
     */
    boolean insert(Classify classify);

    /**
     * 删除
     */
    boolean delById(String id);

    /**
     * 得到分类集合
     *
     * @return
     */
    List<Classify> selectList();

    /**
     * 新增子分类
     *
     * @param chdClassify
     * @return
     */
    boolean insert(ChdClassify chdClassify);

    /**
     * 删除子分类
     */
    boolean deChdClassifylById(String id);

    /**
     * 得到子分类集合
     *
     * @return
     */
    List<ChdClassify> selectChdClassifyList(String ptId);

    List<ChdClassify> selectChdClassifyList();

    Map<Classify, List<ChdClassify>> selectChdClassifyMap();

    Classify getClassify(String id);

    Classify getClassifyByName(String name);

    ChdClassify getChdClassify(String id);


}
