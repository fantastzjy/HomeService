package com.it.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.it.entity.*;
import com.it.service.CommentService;
import com.it.service.PostCategoryService;
import com.it.service.PostInfoService;
import com.it.service.UserService;
import com.it.util.Result;
import com.it.util.ResultResponse;
import com.it.util.TableResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 〈相关实现接口〉<br>
 *
 */
@Controller
@RequestMapping("/postInfo")
public class PostInfoController {
    @Autowired
    private PostCategoryService postCategoryService;
    @Autowired
    private PostInfoService postInfoService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private UserService userService;

    /**
     * 分类管理界面跳转
     *
     * @param mv
     * @return
     */
    @RequestMapping("/categoryIndex.do")
    public ModelAndView categoryIndex(ModelAndView mv) {
        mv.setViewName("postInfo/categoryIndex");
        return mv;
    }

    /**
     * 订单管理界面跳转
     *
     * @param mv
     * @return
     */
    @RequestMapping("/orderIndex.do")
    public ModelAndView orderIndex(ModelAndView mv) {
        List<PostCategory> postCategoryList = postCategoryService.selectList();
        mv.addObject("postCategoryList", postCategoryList);
        mv.setViewName("postInfo/orderIndex");
        return mv;
    }

    /**
     * 分类列表加载接口
     *
     * @param postCategory
     * @param page
     * @param limit
     * @return
     */
    @ResponseBody
    @GetMapping("postCategoryTable.do")
    public TableResultResponse newsCategoryTable(PostCategory postCategory, int page, int limit) {
        List<Map<String, Object>> infoList = new ArrayList<>();
        Page<PostCategory> pageInfo = postCategoryService.selectPage(postCategory, page, limit);
        for (PostCategory record : pageInfo.getRecords()) {
            Map<String, Object> resultMap = new HashMap<>(16);
            resultMap.put("id", record.getId());
            resultMap.put("name", record.getName());
            infoList.add(resultMap);
        }
        return Result.tableResule(pageInfo.getTotal(), infoList);
    }

    /**
     * 分类列表加载接口
     *
     * @param order
     * @param page
     * @param limit
     * @return
     */
    @ResponseBody
    @GetMapping("order.do")
    public TableResultResponse orderTable(Order order, int page, int limit) {
        List<Map<String, Object>> infoList = new ArrayList<>();
        Page<Order> pageInfo = postInfoService.selectPage(order, page, limit);
        for (Order record : pageInfo.getRecords()) {
            Map<String, Object> resultMap = new HashMap<>(16);
            resultMap.put("id", record.getId());
            resultMap.put("userName", record.getUserName());
            resultMap.put("price", record.getPrice());
            resultMap.put("category", record.getCategory());
            resultMap.put("createTime", record.getCreateTime() == null ? "" : record.getCreateTime().substring(0, 19));
            resultMap.put("hours", record.getHours());
            resultMap.put("totalPrice", record.getTotalPrice());
            PostInfo one = postInfoService.getOne(record.getPostId());
            resultMap.put("name", one == null ? "账号已下架" : one.getName());
            resultMap.put("sendUser", one == null ? "账号已下架" : one.getUserName());
            resultMap.put("pass", one == null ? "账号已下架" : one.getPass());
            resultMap.put("idName", one == null ? "账号已下架" : one.getIdName());
            infoList.add(resultMap);
        }
        return Result.tableResule(pageInfo.getTotal(), infoList);
    }

    /**
     * 删除分类
     *
     * @param id
     * @return
     */
    @ResponseBody
    @DeleteMapping("/postCategory.do")
    public ResultResponse delNewsCategory(String id) {
        boolean result = postCategoryService.delById(id);
        if (!result) {
            return Result.resuleError("删除失败");
        }
        return Result.resuleSuccess();
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @ResponseBody
    @DeleteMapping("/order.do")
    public ResultResponse delOrder(String id) {
        boolean result = postInfoService.delOrderById(id);
        if (!result) {
            return Result.resuleError("删除失败");
        }
        return Result.resuleSuccess();
    }

    /**
     * 新增分类界面跳转接口
     *
     * @param mv
     * @return
     */
    @RequestMapping("/addPostCategory.do")
    public ModelAndView addClassify(ModelAndView mv) {
        mv.setViewName("postInfo/addPostCategory");
        return mv;
    }

    /**
     * 新增分类
     *
     * @param postCategory
     * @return
     */
    @ResponseBody
    @PostMapping("/postCategory.do")
    public ResultResponse insertNewsCategory(PostCategory postCategory) {
        boolean result = postCategoryService.insert(postCategory);
        if (!result) {
            return Result.resuleError("新增失败");
        }
        return Result.resuleSuccess();
    }

    /**
     * 账号发布界面跳转
     *
     * @param mv
     * @return
     */
    @RequestMapping("/addPage.do")
    public ModelAndView addNews(ModelAndView mv) {
        List<PostCategory> postCategorys = postCategoryService.selectList();
        mv.addObject("postCategorys", postCategorys);
        mv.setViewName("postInfo/addPage");
        return mv;
    }

    /**
     * 发布
     *
     * @param postInfo
     * @return
     */
    @ResponseBody
    @PostMapping("/postInfo.do")
    public ResultResponse insertNews(PostInfo postInfo) {
        boolean result = postInfoService.insert(postInfo);
        if (!result) {
            return Result.resuleError("新增失败");
        }
        return Result.resuleSuccess();
    }

    /**
     * 帖子管理界面跳转
     *
     * @param mv
     * @return
     */
    @RequestMapping("/index.do")
    public ModelAndView index(ModelAndView mv) {
        List<PostCategory> postCategorys = postCategoryService.selectList();
        mv.addObject("postCategorys", postCategorys);
        mv.setViewName("postInfo/index");
        return mv;
    }

    /**
     * 评论管理界面跳转
     *
     * @param mv
     * @return
     */
    @RequestMapping("/reportIndex.do")
    public ModelAndView reportIndex(ModelAndView mv, String postId) {
        mv.addObject("postId", postId);
        mv.setViewName("postInfo/reportIndex");
        return mv;
    }

    /**
     * 管理界面跳转
     *
     * @param mv
     * @return
     */
    @RequestMapping("/commentIndex.do")
    public ModelAndView commentIndex(ModelAndView mv, String postId) {
        mv.addObject("postId", postId);
        mv.setViewName("postInfo/commentIndex");
        return mv;
    }

    /**
     * 评论加载接口
     *
     * @param comment
     * @param page
     * @param limit
     * @return
     */
    @ResponseBody
    @GetMapping("commentTable.do")
    public TableResultResponse commentTable(Comment comment, int page, int limit) {
        List<Map<String, Object>> infoList = new ArrayList<>();
        Page<Comment> pageInfo = commentService.selectPage(comment, page, limit);
        for (Comment record : pageInfo.getRecords()) {
            Map<String, Object> resultMap = new HashMap<>(16);
            resultMap.put("id", record.getId());
            resultMap.put("userName", userService.selectByPrimaryKey(record.getUserId())
                    == null ? "用户已删除" : userService.selectByPrimaryKey(record.getUserId()).getUserName());
            resultMap.put("content", record.getContent());
            resultMap.put("time", record.getTime() == null ? "" : record.getTime().substring(0, 19));
            infoList.add(resultMap);
        }
        return Result.tableResule(pageInfo.getTotal(), infoList);
    }

    /**
     * 列表加载接口
     *
     * @param postInfo
     * @param page
     * @param limit
     * @return
     */
    @ResponseBody
    @GetMapping("postInfoTable.do")
    public TableResultResponse postInfoTable(PostInfo postInfo, int page, int limit) {
        List<Map<String, Object>> infoList = new ArrayList<>();
        Page<PostInfo> pageInfo = postInfoService.selectPage(postInfo, page, limit);
        for (PostInfo record : pageInfo.getRecords()) {
            Map<String, Object> resultMap = new HashMap<>(16);
            resultMap.put("id", record.getId());
            resultMap.put("name", record.getName());
            resultMap.put("categoryName", record.getCategoryName());
            resultMap.put("content", record.getContent());
            resultMap.put("introduce", record.getIntroduce());
            resultMap.put("userName", record.getUserName());
            resultMap.put("categoryName", record.getCategoryName());
            resultMap.put("observer", record.getObserver());
            resultMap.put("pageView", record.getPageView());
            resultMap.put("state", record.getState());
            resultMap.put("createTime", record.getCreateTime() == null ? "" : record.getCreateTime().substring(0, 19));
            infoList.add(resultMap);
        }
        return Result.tableResule(pageInfo.getTotal(), infoList);
    }

    /**
     * 评论加载接口
     *
     * @param report
     * @param page
     * @param limit
     * @return
     */
    @ResponseBody
    @GetMapping("reportTable.do")
    public TableResultResponse reportTable(Report report, int page, int limit) {
        List<Map<String, Object>> infoList = new ArrayList<>();
        Page<Report> selectPage = postInfoService.selectPage(report, page, limit);
        for (Report record : selectPage.getRecords()) {
            Map<String, Object> resultMap = new HashMap<>(16);
            resultMap.put("id", record.getId());
            resultMap.put("content", record.getContent());
            resultMap.put("userName", userService.selectByPrimaryKey(record.getUserName()) == null ? "用户不存在" : userService.selectByPrimaryKey(record.getUserName()).getUserName());
            resultMap.put("time", record.getTime() == null ? "" : record.getTime().substring(0, 19));
            infoList.add(resultMap);
        }
        return Result.tableResule(selectPage.getTotal(), infoList);
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @ResponseBody
    @DeleteMapping("/postInfo.do")
    public ResultResponse delNews(String id) {
        boolean result = postInfoService.delById(id);
        if (!result) {
            return Result.resuleError("删除失败");
        }
        return Result.resuleSuccess();
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @ResponseBody
    @DeleteMapping("/report.do")
    public ResultResponse delReport(String id) {
        boolean result = postInfoService.delReportById(id);
        if (!result) {
            return Result.resuleError("删除失败");
        }
        return Result.resuleSuccess();
    }

    /**
     * 删除评论
     *
     * @param id
     * @return
     */
    @ResponseBody
    @DeleteMapping("/comment.do")
    public ResultResponse delComment(String id) {
        boolean result = commentService.delById(id);
        if (!result) {
            return Result.resuleError("删除失败");
        }
        return Result.resuleSuccess();
    }

    /**
     * 编辑界面跳转
     *
     * @param mv
     * @return
     */
    @RequestMapping("/editPage.do")
    public ModelAndView editPage(ModelAndView mv, String id) {
        PostInfo postInfo = postInfoService.getOne(id);
        mv.addObject("postInfo", postInfo);
        List<PostCategory> postCategorys = postCategoryService.selectList();
        mv.addObject("postCategorys", postCategorys);
        mv.setViewName("postInfo/editPage");
        return mv;
    }

    /**
     * 修改
     *
     * @param postInfo
     * @return
     */
    @ResponseBody
    @PutMapping("/postInfo.do")
    public ResultResponse updataNews(PostInfo postInfo) {
        boolean result = postInfoService.editById(postInfo);
        if (!result) {
            return Result.resuleError("修改失败");
        }
        return Result.resuleSuccess();
    }

    /**
     * 新增举报信息
     *
     * @param report
     * @return
     */
    @ResponseBody
    @PostMapping("/report.do")
    public ResultResponse report(Report report) {
        boolean result = postInfoService.insert(report);
        if (!result) {
            return Result.resuleError("修改失败");
        }
        return Result.resuleSuccess();
    }
}