package com.it.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.it.entity.Collect;
import com.it.entity.Order;
import com.it.entity.PostInfo;
import com.it.entity.Report;

import java.util.List;

public interface PostInfoService {
    /**
     * 分页查询
     *
     * @param postInfo
     * @param page
     * @param limit
     * @return
     */
    Page<PostInfo> selectPage(PostInfo postInfo, int page, int limit);

    Page<Report> selectPage(Report report, int page, int limit);

    Page<Order> selectPage(Order order, int page, int limit);

    /**
     * 新增
     *
     * @param postInfo
     * @return
     */
    boolean insert(PostInfo postInfo);

    boolean insert(Report report);

    boolean insert(Order order);

    /**
     * 删除
     */
    boolean delById(String id);

    boolean delReportById(String id);

    boolean delOrderById(String id);

    /**
     * 编辑
     */
    boolean editById(PostInfo postInfo);

    /**
     * 根据id得到单个
     */
    PostInfo getOne(String id);


    List<PostInfo> getList(String categoryName, String searchName);

    List<PostInfo> getListOrderTime();

    List<PostInfo> getListOrderObs();

    List<PostInfo> getMyList();

    List<PostInfo> getList(String userId);

    List<Order> getOrderList(String userName);


    /**
     * 收藏
     *
     * @param videoId
     * @return
     */
    boolean insertCollect(String videoId);

    /**
     * 取消搜藏
     *
     * @param videoId
     * @return
     */
    boolean delCollect(String videoId);

    /**
     * 判断当前用户是否收藏
     */
    boolean isCollect(String postId);

    /**
     * 得到收藏文章集合
     */
    List<Collect> getCollectList();

    /**
     * 获得改文章的赞的总数
     *
     * @param postId
     * @return
     */
    Integer getLikeNumber(String postId);
}
