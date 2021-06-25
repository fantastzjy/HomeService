package com.it.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.it.entity.ChdClassify;
import com.it.entity.Classify;
import com.it.mapper.ChdClassifyMapper;
import com.it.mapper.ClassifyMapper;
import com.it.service.ClassifyService;
import com.it.util.ItdragonUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 〈分类实现接口〉<br>
 *
 * @author
 * @create 2019/2/15 17:53
 * @since 1.0.0
 */
@Service
public class ClassifyServiceImpl implements ClassifyService {
    @Resource
    private ClassifyMapper classifyMapper;
    @Resource
    private ChdClassifyMapper chdClassifyMapper;
    @Resource
    private ItdragonUtils itdragonUtils;


    @Override
    public Page<Classify> selectPage(Classify classify, int page, int limit) {
        EntityWrapper<Classify> searchInfo = new EntityWrapper<>();
        Page<Classify> pageInfo = new Page<>(page, limit);
        List<Classify> resultList = classifyMapper.selectPage(pageInfo, searchInfo);
        if (!resultList.isEmpty()) {
            pageInfo.setRecords(resultList);
        }
        return pageInfo;
    }

    @Override
    public boolean insert(Classify classify) {
        Integer insert = classifyMapper.insert(classify);
        if (insert > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean delById(String id) {
        //删除分类前先要删除给分类下所有的子分类
        EntityWrapper<ChdClassify> wrapper = new EntityWrapper<>();
        wrapper.eq("ptId", id);
        chdClassifyMapper.delete(wrapper);
        Integer delete = classifyMapper.deleteById(id);
        if (delete > 0) {
            return true;
        }
        return false;
    }

    @Override
    public List<Classify> selectList() {
        List<Classify> classifyList = classifyMapper.selectList(null);
        if (classifyList.isEmpty()) {
            return new ArrayList<>();
        }
        return classifyList;
    }

    @Override
    public boolean insert(ChdClassify chdClassify) {
        Integer insert = chdClassifyMapper.insert(chdClassify);
        if (insert > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean deChdClassifylById(String id) {
        Integer delete = chdClassifyMapper.deleteById(id);
        if (delete > 0) {
            return true;
        }
        return false;
    }

    @Override
    public List<ChdClassify> selectChdClassifyList(String ptId) {
        EntityWrapper<ChdClassify> wrapper = new EntityWrapper<>();
        wrapper.eq("ptId", ptId);
        List<ChdClassify> chdClassifyList = chdClassifyMapper.selectList(wrapper);
        if (chdClassifyList.isEmpty()) {
            return new ArrayList<>();
        }
        return chdClassifyList;
    }


    @Override
    public List<ChdClassify> selectChdClassifyList() {
        return chdClassifyMapper.selectList(null);
    }

    @Override
    public Map<Classify, List<ChdClassify>> selectChdClassifyMap() {
        List<Classify> classifyList = classifyMapper.selectList(null);
        HashMap<Classify, List<ChdClassify>> classifyListHashMap = new HashMap<>();
        for (Classify classify : classifyList) {
            EntityWrapper<ChdClassify> wrapper = new EntityWrapper<>();
            wrapper.eq("ptId", classify.getId());
            List<ChdClassify> chdClassifyList = chdClassifyMapper.selectList(wrapper);
            classifyListHashMap.put(classify, chdClassifyList);
        }
        return classifyListHashMap;

    }

    @Override
    public Classify getClassify(String id) {
        return classifyMapper.selectById(id);
    }

    @Override
    public Classify getClassifyByName(String name) {

        EntityWrapper<Classify> wrapper = new EntityWrapper<>();
        wrapper.eq("name", name);
        List<Classify> classifies = classifyMapper.selectList(wrapper);
        if (!classifies.isEmpty()) {
            return classifies.get(0);
        }
        return null;
    }

    @Override
    public ChdClassify getChdClassify(String id) {
        return chdClassifyMapper.selectById(id);
    }
}