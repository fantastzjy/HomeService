package com.it.controller;

import com.it.entity.*;
import com.it.service.*;
import com.it.service.impl.LeaveWordServiceImpl;
import com.it.util.ItdragonUtils;
import com.it.util.Result;
import com.it.util.ResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/**
 * 〈〉<br>
 */
@Controller
@RequestMapping("/front")
public class FrontController {
    @Autowired
    private LogService logService;
    @Autowired
    private UserService userService;
    @Autowired
    private WbeParameterService wbeParameterService;
    @Autowired
    private ItdragonUtils itdragonUtils;
    @Autowired
    private ClassifyService classifyService;
    @Autowired
    private LeaveWordServiceImpl leaveWordService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private PostInfoService postInfoService;
    @Autowired
    private PostCategoryService postCategoryService;


    /**
     * 首页界面跳转
     *
     * @param mv
     * @return
     */
    @RequestMapping("/index.do")
    public ModelAndView enIndex(ModelAndView mv) {
        CommonMethods("index", mv);
        List<Classify> classifyList = classifyService.selectList();
        mv.addObject("classifyList", classifyList);
        List<ChdClassify> chdClassifyList = classifyService.selectChdClassifyList();
        mv.addObject("chdClassifyList", chdClassifyList);
        List<PostInfo> postInfoList = postInfoService.getListOrderObs();
        mv.addObject("postInfoList", postInfoList);
        mv.setViewName("front/index");
        return mv;
    }


    /**
     * 在线留言页面跳转接口
     *
     * @param mv
     * @return
     */
    @RequestMapping("/leaveWord.do")
    public ModelAndView leaveWord(ModelAndView mv) {
        CommonMethods("leaveWord", mv);
        //获取留言内容
        List<LeaveWord> leaveWordList = leaveWordService.selectList();
        for (LeaveWord leaveWord : leaveWordList) {
            //将留言下的回复存放入leaveWord对象中
            List<Answering> answerings = leaveWordService.selectListAnswering(leaveWord.getId());
            leaveWord.setAnsweringList(answerings);
            leaveWord.setCount(answerings.size());

        }
        mv.addObject("leaveWordList", leaveWordList);
        mv.addObject("leaveWordListNum", leaveWordList.size());
        mv.setViewName("/front/leaveWord");
        return mv;
    }

    /**
     * 留言信息弹窗页面
     *
     * @param mv
     * @return
     */
    @RequestMapping("/addAnswering.do")
    public ModelAndView buyTicketPage(ModelAndView mv, String leaveWordId) {
        mv.addObject("leaveWordId", leaveWordId);
        mv.setViewName("front/addAnswering");
        return mv;
    }

    /**
     * 博客界面跳转
     *
     * @param mv
     * @return
     */
    @RequestMapping("/postInfoList.do")
    public ModelAndView postInfo(ModelAndView mv, String categoryName, String searchName) {
        CommonMethods("postInfoList", mv);
        List<PostInfo> postInfoList = postInfoService.getList(categoryName, searchName);
        mv.addObject("postInfoList", postInfoList);
        //最新
        List<PostInfo> listOrderTime = postInfoService.getListOrderTime();
        //最热
        List<PostInfo> listOrderObs = postInfoService.getListOrderObs();
        mv.addObject("listOrderTime", listOrderTime);
        mv.addObject("listOrderObs", listOrderObs);
        mv.setViewName("front/postInfoList");
        return mv;
    }

    /**
     * 博客详情界面跳转
     *
     * @param mv
     * @return
     */
    @RequestMapping("/postInfoDetail.do")
    public ModelAndView postInfoDetail(ModelAndView mv, String id) {
        CommonMethods("postInfoDetail", mv);
        PostInfo postInfo = postInfoService.getOne(id);
        mv.addObject("postInfo", postInfo);
        //最新
        List<PostInfo> listOrderTime = postInfoService.getListOrderTime();
        //最热
        List<PostInfo> listOrderObs = postInfoService.getListOrderObs();
        mv.addObject("listOrderTime", listOrderTime);
        mv.addObject("listOrderObs", listOrderObs);
        //查询评论
        List<Comment> commentList = commentService.getList(id);
        for (Comment comment : commentList) {
            if (itdragonUtils.isGogin()) {
                if (comment.getUserId().equals(itdragonUtils.getSessionUser().getId())) {
                    comment.setIsMe("1");
                }
            }

            comment.setUser(userService.selectByPrimaryKey(comment.getUserId()));
        }
        if (itdragonUtils.isGogin()) {
            mv.addObject("isCollect", postInfoService.isCollect(id));
        }

        mv.addObject("commentList", commentList);
        mv.setViewName("front/postInfoDetail");
        return mv;
    }


    /**
     * 添加评论
     *
     * @param
     * @return
     */
    @ResponseBody
    @PostMapping("/addComment.do")
    public ResultResponse addComment(Comment comment) {
        PostInfo postInfo = postInfoService.getOne(comment.getPostId());
        boolean insert = commentService.insert(comment);
        if (!insert) {
            return Result.resuleError("失败");
        }
        //帖子评论数加1
        postInfo.setObserver(postInfo.getObserver() + 1);
        postInfoService.editById(postInfo);
        return Result.resuleSuccess();
    }

    /**
     * 删除评论
     *
     * @param
     * @return
     */
    @ResponseBody
    @PostMapping("/delComment.do")
    public ResultResponse delComment(String id) {
        Comment comment = commentService.getOne(id);
        PostInfo postInfo = postInfoService.getOne(comment.getPostId());
        boolean insert = commentService.delById(id);
        if (!insert) {
            return Result.resuleError("失败");
        }
        //帖子评论数减1
        postInfo.setObserver(postInfo.getObserver() - 1);
        postInfoService.editById(postInfo);
        return Result.resuleSuccess();
    }

    /**
     * 收藏功能接口
     *
     * @param postId
     * @return
     */
    @ResponseBody
    @PostMapping("/collect.do")
    public ResultResponse collect(String postId) {
        boolean result = postInfoService.isCollect(postId);
        if (result) {
            postInfoService.delCollect(postId);
            return Result.resuleSuccess(null, "取消收藏成功");
        } else {
            postInfoService.insertCollect(postId);
            return Result.resuleSuccess(null, "收藏成功");
        }
    }

    /**
     * 举报信息弹窗页面
     *
     * @param mv
     * @return
     */
    @RequestMapping("/addReport.do")
    public ModelAndView addReport(ModelAndView mv, String postId) {
        mv.addObject("postId", postId);
        mv.setViewName("front/addReport");
        return mv;
    }

    /**
     * 时间选择弹窗
     *
     * @param mv
     * @return
     */
    @RequestMapping("/buyPage.do")
    public ModelAndView buyPage(ModelAndView mv, String postId) {
        mv.addObject("postId", postId);
        mv.setViewName("front/buyPage");
        return mv;
    }

    /**
     * 租号接口
     *
     * @param postId
     * @return
     */
    @ResponseBody
    @PostMapping("/buy.do")
    public ResultResponse buy(String postId, Integer hours) {
        PostInfo info = postInfoService.getOne(postId);
        User user = userService.selectByPrimaryKey(itdragonUtils.getSessionUser().getId());
        if (user.getBalance() < info.getPrice() * hours) {
            return Result.resuleError("账号余额不足!");
        }
        Order order = new Order();
        order.setPrice(info.getPrice());
        order.setTotalPrice(info.getPrice() * hours);
        order.setCategory(info.getCategoryName());
        order.setHours(hours);
        order.setPostId(postId);
        boolean result = postInfoService.insert(order);
        if (result) {
            return Result.resuleSuccess("success");
        }
        return Result.resuleError("error");
    }
    // =============================================个人信息界面===========================================================================

    /**
     * 个人中心跳转接口
     *
     * @param mv
     * @return
     */
    @RequestMapping("/userCenter.do")
    public ModelAndView userCenter(ModelAndView mv) {
        CommonMethods("userCenter", mv);
        //查询自己发表的帖子
        List<PostInfo> myList = postInfoService.getMyList();
        for (PostInfo postInfo : myList) {
            postInfo.setLikeNumber(postInfoService.getLikeNumber(postInfo.getId()));
        }
        mv.addObject("myList", myList);
        mv.setViewName("/front/userCenter");
        return mv;
    }

    /**
     * 个人信息跳转接口
     *
     * @param mv
     * @return
     */
    @RequestMapping("/userInfo.do")
    public ModelAndView userInfo(ModelAndView mv) {
        CommonMethods("userCenter", mv);
        User user = userService.selectByPrimaryKey(itdragonUtils.getSessionUser().getId());
        mv.addObject("user", user);
        mv.setViewName("/front/userInfo");
        return mv;
    }

    /**
     * 修改密码跳转接口
     *
     * @param mv
     * @return
     */
    @RequestMapping("/changePas.do")
    public ModelAndView changePas(ModelAndView mv) {
        CommonMethods("changePas", mv);
        User user = userService.selectByPrimaryKey(itdragonUtils.getSessionUser().getId());
        mv.addObject("user", user);
        mv.setViewName("/front/changePas");
        return mv;
    }

    /**
     * 发表博客界面
     *
     * @param mv
     * @return
     */
    @RequestMapping("/addPostInfoPage.do")
    public ModelAndView addPostInfoPage(ModelAndView mv) {
        CommonMethods("userCenter", mv);
        mv.setViewName("/front/addPostInfoPage");
        return mv;
    }

    /**
     * 修改博客界面
     *
     * @param mv
     * @return
     */
    @RequestMapping("/editPostInfoPage.do")
    public ModelAndView editPostInfoPage(ModelAndView mv, String postId) {
        CommonMethods("userCenter", mv);
        PostInfo postInfo = postInfoService.getOne(postId);
        mv.addObject("postInfo", postInfo);
        mv.setViewName("/front/editPostInfoPage");
        return mv;
    }

    /**
     * 我的收藏夹
     *
     * @param mv
     * @return
     */
    @RequestMapping("/myCollect.do")
    public ModelAndView myCollect(ModelAndView mv) {
        CommonMethods("userCenter", mv);
        //查询自己发表的帖子
        List<Collect> collectList = postInfoService.getCollectList();
        mv.addObject("collectList", collectList);
        mv.setViewName("/front/myCollect");
        return mv;
    }

    /**
     * 充值弹窗
     *
     * @param mv
     * @return
     */
    @RequestMapping("/topUpPage.do")
    public ModelAndView topUpPage(ModelAndView mv) {
        mv.setViewName("front/topUpPage");
        return mv;
    }

    /**
     * 租号接口
     *
     * @param price
     * @return
     */
    @ResponseBody
    @PostMapping("/topUp.do")
    public ResultResponse topUp(Float price) {
        User user = userService.selectByPrimaryKey(itdragonUtils.getSessionUser().getId());
        user.setBalance(user.getBalance() + price);
        boolean result = userService.updateByPrimaryKey(user);
        if (result) {
            return Result.resuleSuccess("success");
        }
        return Result.resuleError("error");
    }

    /**
     * 我的订单
     *
     * @param mv
     * @return
     */
    @RequestMapping("/myOrder.do")
    public ModelAndView muOrder(ModelAndView mv) {
        CommonMethods("userCenter", mv);
        List<Order> orderList = postInfoService.getOrderList(itdragonUtils.getSessionUser().getUserName());
        for (Order order : orderList) {
            order.setPostInfo(postInfoService.getOne(order.getPostId()));
        }
        mv.addObject("orderList", orderList);
        mv.setViewName("/front/myOrder");
        return mv;
    }
//=================================公共方法=========================================================================================

    /**
     * 前端页面有一些公用需求在这里抽取出来
     *
     * @param which 是选中哪一个页面顶部标签参数
     * @param mv
     */
    public void CommonMethods(String which, ModelAndView mv) {
        //判断用户是否登录
        boolean gogin = itdragonUtils.isGogin();
        boolean admin = false;
        boolean isUser = false;
        if (gogin) {
            //如果登录将用户信息放入前台
            mv.addObject("userInfo", userService.selectByPrimaryKey(itdragonUtils.getSessionUser().getId()));
            //平且判断是否是管理员
            admin = userService.isAdmin(itdragonUtils.getSessionUser().getId());
            isUser = userService.isUser(itdragonUtils.getSessionUser().getId());

        }
        mv.addObject("go_in", gogin);
        mv.addObject("admin", admin);
        mv.addObject("isUser", isUser);
        //帖子标签分类
        List<PostCategory> postCategoryList = postCategoryService.selectList();
        mv.addObject("postCategoryList", postCategoryList);
        //网站参数
        WbeParameter wbeParameter = wbeParameterService.getWbeParameter();
        mv.addObject("wbeParameter", wbeParameter);
        //主题样式参数
        Arguments arguments = wbeParameterService.getArguments();
        mv.addObject("arguments", arguments);

        Map<Classify, List<ChdClassify>> classifyListMap = classifyService.selectChdClassifyMap();
        mv.addObject("classifyListMap", classifyListMap);
    }
}