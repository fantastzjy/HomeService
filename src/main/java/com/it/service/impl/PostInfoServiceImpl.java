package com.it.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.it.entity.*;
import com.it.mapper.*;
import com.it.service.PostInfoService;
import com.it.util.DateUtil;
import com.it.util.ItdragonUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class PostInfoServiceImpl implements PostInfoService {
    @Resource
    private PostInfoMapper postInfoMapper;
    @Resource
    private ItdragonUtils itdragonUtils;
    @Resource
    private UserMapper userMapper;
    @Resource
    private CommentMapper commentMapper;
    @Resource
    private CollectMapper collectMapper;
    @Resource
    private ReportMapper reportMapper;
    @Resource
    private OrderMapper orderMapper;

    @Override
    public Page<PostInfo> selectPage(PostInfo postInfo, int page, int limit) {
        EntityWrapper<PostInfo> searchInfo = new EntityWrapper<>();
        if (ItdragonUtils.stringIsNotBlack(postInfo.getName())) {
            searchInfo.like("name", postInfo.getName());
        }
        if (ItdragonUtils.stringIsNotBlack(postInfo.getCategoryName())) {
            searchInfo.eq("categoryName", postInfo.getCategoryName());
        }
        if (ItdragonUtils.stringIsNotBlack(postInfo.getCreateTime())) {
            searchInfo.between("createTime", postInfo.getCreateTime().split(" ~ ")[0], postInfo.getCreateTime().split(" ~ ")[1]);
        }
        searchInfo.orderBy("createTime desc");
        Page<PostInfo> pageInfo = new Page<>(page, limit);
        List<PostInfo> resultList = postInfoMapper.selectPage(pageInfo, searchInfo);
        if (!resultList.isEmpty()) {
            pageInfo.setRecords(resultList);
        }
        return pageInfo;
    }

    @Override
    public Page<Report> selectPage(Report report, int page, int limit) {
        EntityWrapper<Report> searchInfo = new EntityWrapper<>();
        if (ItdragonUtils.stringIsNotBlack(report.getPtId())) {
            searchInfo.eq("ptId", report.getPtId());
        }
        searchInfo.orderBy("time desc");
        Page<Report> pageInfo = new Page<>(page, limit);
        List<Report> resultList = reportMapper.selectPage(pageInfo, searchInfo);
        if (!resultList.isEmpty()) {
            pageInfo.setRecords(resultList);
        }
        return pageInfo;
    }

    @Override
    public Page<Order> selectPage(Order order, int page, int limit) {
        EntityWrapper<Order> searchInfo = new EntityWrapper<>();
        if (ItdragonUtils.stringIsNotBlack(order.getCategory())) {
            searchInfo.eq("category", order.getCategory());
        }
        searchInfo.orderBy("createTime desc");
        Page<Order> pageInfo = new Page<>(page, limit);
        List<Order> resultList = orderMapper.selectPage(pageInfo, searchInfo);
        if (!resultList.isEmpty()) {
            pageInfo.setRecords(resultList);
        }
        return pageInfo;
    }

    @Override
    public boolean insert(PostInfo postInfo) {
        postInfo.setCreateTime(DateUtil.getNowDateSS());
        postInfo.setPageView(0);
        postInfo.setObserver(0);
        postInfo.setState("待审核");
        postInfo.setUserId(itdragonUtils.getSessionUser().getId());
        postInfo.setUserName(itdragonUtils.getSessionUser().getUserName());
        Integer insert = postInfoMapper.insert(postInfo);
        if (insert > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean insert(Report report) {
        report.setUserName(itdragonUtils.getSessionUser().getId());
        report.setTime(DateUtil.getNowDateSS());
        Integer insert = reportMapper.insert(report);
        if (insert > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean insert(Order order) {
        order.setUserName(itdragonUtils.getSessionUser().getUserName());
        order.setCreateTime(DateUtil.getNowDateSS());
        Integer insert = orderMapper.insert(order);
        if (insert > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean delById(String id) {
        Integer delete = postInfoMapper.deleteById(id);
        if (delete > 0) {
            //删除后删除下的评论
            EntityWrapper<Comment> wrapper = new EntityWrapper<>();
            wrapper.eq("postId", id);
            commentMapper.delete(wrapper);
            return true;
        }
        return false;
    }

    @Override
    public boolean delReportById(String id) {
        Integer delete = reportMapper.deleteById(id);
        if (delete > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean delOrderById(String id) {
        Integer delete = orderMapper.deleteById(id);
        if (delete > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean editById(PostInfo postInfo) {
        Integer update = postInfoMapper.updateById(postInfo);
        if (update > 0) {
            return true;
        }
        return false;
    }

    @Override
    public PostInfo getOne(String id) {
        return postInfoMapper.selectById(id);
    }

    @Override
    public List<PostInfo> getList(String categoryName, String searchName) {
        EntityWrapper<PostInfo> wrapper = new EntityWrapper<>();
        if (ItdragonUtils.stringIsNotBlack(categoryName)) {
            wrapper.eq("categoryName", categoryName);
        }
        if (ItdragonUtils.stringIsNotBlack(searchName)) {
            wrapper.like("name", searchName);
        }
        wrapper.eq("state", "通过");
        wrapper.orderBy("createTime desc");
        List<PostInfo> postInfos = postInfoMapper.selectList(wrapper);
        return postInfos;
    }

    @Override
    public List<PostInfo> getListOrderTime() {
        EntityWrapper<PostInfo> wrapper = new EntityWrapper<>();
        wrapper.orderBy("createTime desc");
        wrapper.eq("state", "通过");
        List<PostInfo> postInfos = postInfoMapper.selectList(wrapper);
        return postInfos;

    }

    @Override
    public List<PostInfo> getListOrderObs() {
        EntityWrapper<PostInfo> wrapper = new EntityWrapper<>();
        wrapper.orderBy("observer desc");
        wrapper.eq("state", "通过");
        List<PostInfo> postInfos = postInfoMapper.selectList(wrapper);
        return postInfos;
    }

    @Override
    public List<PostInfo> getMyList() {
        EntityWrapper<PostInfo> wrapper = new EntityWrapper<>();
        wrapper.eq("userId", itdragonUtils.getSessionUser().getId());
        return postInfoMapper.selectList(wrapper);

    }

    @Override
    public List<PostInfo> getList(String userId) {
        EntityWrapper<PostInfo> wrapper = new EntityWrapper<>();
        wrapper.eq("userId", userId);
        wrapper.eq("state", "通过");
        return postInfoMapper.selectList(wrapper);
    }

    @Override
    public List<Order> getOrderList(String userName) {
        EntityWrapper<Order> wrapper = new EntityWrapper<>();
        wrapper.eq("userName", userName);
        return orderMapper.selectList(wrapper);
    }


    @Override
    public boolean insertCollect(String postId) {
        Collect collect = new Collect();
        collect.setUserId(itdragonUtils.getSessionUser().getId());
        collect.setPostId(postId);
        Integer insert = collectMapper.insert(collect);
        if (insert > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean delCollect(String postId) {
        EntityWrapper<Collect> wrapper = new EntityWrapper<>();
        wrapper.eq("postId", postId);
        wrapper.eq("userId", itdragonUtils.getSessionUser().getId());
        Integer delete = collectMapper.delete(wrapper);
        if (delete > 0) {
            return false;
        }
        return false;
    }

    @Override
    public boolean isCollect(String postId) {
        Collect collect = new Collect();
        collect.setPostId(postId);
        collect.setUserId(itdragonUtils.getSessionUser().getId());
        Collect one = collectMapper.selectOne(collect);
        if (one != null) {
            return true;
        }
        return false;
    }

    @Override
    public List<Collect> getCollectList() {
        EntityWrapper<Collect> wrapper = new EntityWrapper<>();
        wrapper.eq("userId", itdragonUtils.getSessionUser().getId());
        List<Collect> collectList = collectMapper.selectList(wrapper);
        ArrayList<Collect> resultList = new ArrayList<>();
        for (Collect collect : collectList) {
            PostInfo postInfo = postInfoMapper.selectById(collect.getPostId());
            if (postInfo != null) {
                collect.setPostInfo(postInfo);
                resultList.add(collect);
            }

        }
        return resultList;
    }

    @Override
    public Integer getLikeNumber(String postId) {
        EntityWrapper<Collect> wrapper = new EntityWrapper<>();
        wrapper.eq("postId", postId);
        List<Collect> collectList = collectMapper.selectList(wrapper);
        return collectList.size();
    }
}
