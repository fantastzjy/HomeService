package com.it.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.it.entity.Comment;
import com.it.mapper.CommentMapper;
import com.it.service.CommentService;
import com.it.util.DateUtil;
import com.it.util.ItdragonUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 *
 * @author
 * @create 2019/2/15 17:53
 * @since 1.0.0
 */
@Service
public class CommentServiceImpl implements CommentService {
    @Resource
    private CommentMapper commentMapper;
    @Resource
    private ItdragonUtils itdragonUtils;


    @Override
    public Page<Comment> selectPage(Comment comment, int page, int limit) {
        EntityWrapper<Comment> searchInfo = new EntityWrapper<>();
        if (ItdragonUtils.stringIsNotBlack(comment.getPostId())) {
            searchInfo.like("postId", comment.getPostId());
        }
        searchInfo.orderBy("time desc");
        Page<Comment> pageInfo = new Page<>(page, limit);
        List<Comment> resultList = commentMapper.selectPage(pageInfo, searchInfo);
        if (!resultList.isEmpty()) {
            pageInfo.setRecords(resultList);
        }
        return pageInfo;
    }

    @Override
    public boolean insert(Comment comment) {
        comment.setUserId(itdragonUtils.getSessionUser().getId());
        comment.setTime(DateUtil.getNowDateSS());
        Integer insert = commentMapper.insert(comment);
        if (insert > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean update(Comment comment) {
        Integer update = commentMapper.updateById(comment);
        if (update > 0) {
            return true;
        }
        return false;
    }

    @Override
    public List<Comment> getList(String postId) {
        EntityWrapper<Comment> wrapper = new EntityWrapper<>();
        wrapper.eq("postId", postId);
        return commentMapper.selectList(wrapper);
    }

    @Override
    public List<Comment> getListByUserId(String userId) {
        EntityWrapper<Comment> wrapper = new EntityWrapper<>();
        wrapper.eq("userId", userId);
        List<Comment> commentList = commentMapper.selectList(wrapper);
        if (commentList.isEmpty()) {
            return null;
        }
        return commentList;


    }

    @Override
    public boolean delById(String id) {
        Integer delete = commentMapper.deleteById(id);
        if (delete > 0) {
            return true;
        }
        return false;
    }

    @Override
    public Comment getOne(String id) {
        return commentMapper.selectById(id);
    }

    @Override
    public Integer getCommentNum(String articleId) {
        EntityWrapper<Comment> wrapper = new EntityWrapper<>();
        wrapper.eq("articleId", articleId);
        List<Comment> comments = commentMapper.selectList(wrapper);
        return comments.size();
    }
}