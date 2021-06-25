package com.it.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.it.entity.Answering;
import com.it.entity.LeaveWord;
import com.it.entity.User;
import com.it.mapper.AnsweringMapper;
import com.it.mapper.LeaveWordMapper;
import com.it.mapper.UserMapper;
import com.it.service.LeaveWordService;
import com.it.util.DateUtil;
import com.it.util.ItdragonUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 〈留言回复实现接口〉<br>
 *
 * @author
 * @create 2019/2/15 17:53
 * @since 1.0.0
 */
@Service
public class LeaveWordServiceImpl implements LeaveWordService {
    @Resource
    private LeaveWordMapper leaveWordMapper;
    @Resource
    private ItdragonUtils itdragonUtils;
    @Resource
    private AnsweringMapper answeringMapper;
    @Resource
    private UserMapper userMapper;


    @Override
    public Page<LeaveWord> selectPage(LeaveWord leaveWord, int page, int limit) {
        EntityWrapper<LeaveWord> searchInfo = new EntityWrapper<>();
        Page<LeaveWord> pageInfo = new Page<>(page, limit);
        //留言用户查询
        if (ItdragonUtils.stringIsNotBlack(leaveWord.getUserName())) {
            searchInfo.eq("userName", leaveWord.getUserName());
        }
        //留言时间查询
        if (ItdragonUtils.stringIsNotBlack(leaveWord.getTime())) {
            searchInfo.between("time", leaveWord.getTime().split(" ~ ")[0], leaveWord.getTime().split(" ~ ")[1]);
        }
        searchInfo.orderBy("time desc");
        List<LeaveWord> resultList = leaveWordMapper.selectPage(pageInfo, searchInfo);
        if (!resultList.isEmpty()) {
            pageInfo.setRecords(resultList);
        }
        return pageInfo;
    }

    @Override
    public boolean insert(LeaveWord leaveWord) {
        //获取当前留言用户的信息
        User user = itdragonUtils.getSessionUser();
        User user1 = userMapper.selectById(user.getId());
        leaveWord.setUserName(user1.getUserName());
        leaveWord.setUserImg(user1.getImgUrl());
        leaveWord.setTime(DateUtil.getNowDateSS());
        Integer insert = leaveWordMapper.insert(leaveWord);
        if (insert > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean delById(String id) {
        //删除留言前先要删除留言下所有的回复
        EntityWrapper<Answering> wrapper = new EntityWrapper<>();
        wrapper.eq("leaveId", id);
        answeringMapper.delete(wrapper);
        Integer delete = leaveWordMapper.deleteById(id);
        if (delete > 0) {
            return true;
        }
        return false;
    }

    @Override
    public List<LeaveWord> selectList() {
        List<LeaveWord> leaveWordList = leaveWordMapper.selectList(null);
        if (leaveWordList.isEmpty()) {
            return new ArrayList<>();
        }
        if (itdragonUtils.isGogin()) {
            for (LeaveWord leaveWord : leaveWordList) {
                //判断该留言是不是本人留言的
                if (itdragonUtils.getSessionUser().getUserName().equals(leaveWord.getUserName())) {
                    leaveWord.setIsMe("yes");
                } else {
                    leaveWord.setIsMe("no");
                }
            }
        }

        return leaveWordList;
    }

    @Override
    public boolean insert(Answering answering) {
        //获取当前留言回复用户的信息
        User user = itdragonUtils.getSessionUser();
        User user1 = userMapper.selectById(user.getId());
        answering.setUserName(user1.getUserName());
        answering.setUserImg(user1.getImgUrl());
        answering.setTime(DateUtil.getNowDateSS());
        Integer insert = answeringMapper.insert(answering);
        if (insert > 0) {
            return true;
        }
        return false;
    }

    @Override
    public List<Answering> selectListAnswering(String leaveId) {
        EntityWrapper<Answering> wrapper = new EntityWrapper<>();
        wrapper.eq("leaveId", leaveId);
        wrapper.orderBy("time desc");
        List<Answering> answeringList = answeringMapper.selectList(wrapper);
        if (answeringList.isEmpty()) {
            return new ArrayList<>();
        }
        for (Answering answering : answeringList) {
            if (itdragonUtils.isGogin()) {
                //判断该留言是不是本人留言的
                if (itdragonUtils.getSessionUser().getUserName().equals(answering.getUserName())) {
                    answering.setIsMe("yes");
                } else {
                    answering.setIsMe("no");
                }
            }
        }
        return answeringList;
    }

    @Override
    public boolean delAnsweringById(String id) {
        Integer delete = answeringMapper.deleteById(id);
        if (delete > 0) {
            return true;
        }
        return false;
    }


    @Override
    public Integer getLeaveWordNumber() {
        List<LeaveWord> leaveWordList = leaveWordMapper.selectList(null);
        return leaveWordList.size();
    }

}