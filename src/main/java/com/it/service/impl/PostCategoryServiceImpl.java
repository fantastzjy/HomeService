package com.it.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.it.entity.PostCategory;
import com.it.mapper.PostCategoryMapper;
import com.it.service.PostCategoryService;
import com.it.util.ItdragonUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 〈新闻分类实现接口〉<br>
 *
 * @author
 * @create 2020/2/15 17:53
 * @since 1.0.0
 */
@Service
public class PostCategoryServiceImpl implements PostCategoryService {
    @Resource
    private PostCategoryMapper postCategoryMapper;
    @Resource
    private ItdragonUtils itdragonUtils;


    @Override
    public Page<PostCategory> selectPage(PostCategory postCategory, int page, int limit) {
        EntityWrapper<PostCategory> searchInfo = new EntityWrapper<>();
        Page<PostCategory> pageInfo = new Page<>(page, limit);
        List<PostCategory> resultList = postCategoryMapper.selectPage(pageInfo, searchInfo);
        if (!resultList.isEmpty()) {
            pageInfo.setRecords(resultList);
        }
        return pageInfo;
    }

    @Override
    public boolean insert(PostCategory postCategory) {
        Integer insert = postCategoryMapper.insert(postCategory);
        if (insert > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean delById(String id) {
        Integer delete = postCategoryMapper.deleteById(id);
        if (delete > 0) {
            return true;
        }
        return false;
    }

    @Override
    public List<PostCategory> selectList() {
        List<PostCategory> newsCategoryList = postCategoryMapper.selectList(null);
        if (newsCategoryList.isEmpty()) {
            return new ArrayList<>();
        }
        return newsCategoryList;
    }


}