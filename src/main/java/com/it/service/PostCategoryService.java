package com.it.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.it.entity.PostCategory;

import java.util.List;

public interface PostCategoryService {
    /**
     * 分页查询
     *
     * @param entity
     * @param page
     * @param limit
     * @return
     */
    Page<PostCategory> selectPage(PostCategory entity, int page, int limit);

    /**
     * 新增
     *
     * @param entity
     * @return
     */
    boolean insert(PostCategory entity);

    /**
     * 删除
     */
    boolean delById(String id);

    /**
     * 得到新闻分类集合
     *
     * @return
     */
    List<PostCategory> selectList();


}
