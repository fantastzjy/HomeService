package com.it.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.it.entity.ChdClassify;
import com.it.entity.Classify;
import com.it.service.ClassifyService;
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
 * 〈分类实现接口〉<br>
 */
@Controller
@RequestMapping("/classify")
public class ClassifyController {
    @Autowired
    private ClassifyService classifyService;

    /**
     * 分类管理界面跳转
     *
     * @param mv
     * @return
     */
    @RequestMapping("/index.do")
    public ModelAndView enIndex(ModelAndView mv) {
        mv.setViewName("classify/index");
        return mv;
    }

    /**
     * 分类列表加载接口
     *
     * @param classify
     * @param page
     * @param limit
     * @return
     */
    @ResponseBody
    @GetMapping("classifyTable.do")
    public TableResultResponse classifyTable(Classify classify, int page, int limit) {
        List<Map<String, Object>> infoList = new ArrayList<>();
        Page<Classify> pageInfo = classifyService.selectPage(classify, page, limit);
        for (Classify record : pageInfo.getRecords()) {
            Map<String, Object> resultMap = new HashMap<>(16);
            resultMap.put("id", record.getId());
            String chdName = "";
            //得到所有子分类
            List<ChdClassify> chdClassifies = classifyService.selectChdClassifyList(record.getId());
            for (ChdClassify chdClassify : chdClassifies) {
                //拿到子分类名称循环添加到chdName,用空格隔开
                chdName += "&nbsp;&nbsp;" + chdClassify.getName();
            }
            resultMap.put("chdName", chdName);
            resultMap.put("name", record.getName());
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
    @DeleteMapping("/classify.do")
    public ResultResponse delClassify(String id) {
        boolean result = classifyService.delById(id);
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
    @RequestMapping("/addClassify.do")
    public ModelAndView addClassify(ModelAndView mv) {
        mv.setViewName("classify/addClassify");
        return mv;
    }

    /**
     * 新增分类
     *
     * @param classify
     * @return
     */
    @ResponseBody
    @PostMapping("/classify.do")
    public ResultResponse insertClassify(Classify classify) {
        boolean result = classifyService.insert(classify);
        if (!result) {
            return Result.resuleError("新增失败");
        }
        return Result.resuleSuccess();
    }

    /**
     * 新增子分类界面跳转接口
     *
     * @param mv
     * @return
     */
    @RequestMapping("/addChdClassify.do")
    public ModelAndView addChdClassify(ModelAndView mv, String ptId) {
        mv.addObject("ptId", ptId);
        mv.setViewName("classify/addChdClassify");
        return mv;
    }

    /**
     * 新增子分类
     *
     * @param chdClassify
     * @return
     */
    @ResponseBody
    @PostMapping("/chdClassify.do")
    public ResultResponse insertChdClassify(ChdClassify chdClassify) {
        boolean result = classifyService.insert(chdClassify);
        if (!result) {
            return Result.resuleError("新增失败");
        }
        return Result.resuleSuccess();
    }

    /**
     * 查看子分类界面跳转接口
     *
     * @param mv
     * @return
     */
    @RequestMapping("/lookChdClassify.do")
    public ModelAndView lookChdClassify(ModelAndView mv, String ptId) {
        List<ChdClassify> chdClassifieList = classifyService.selectChdClassifyList(ptId);
        mv.addObject("chdClassifieList", chdClassifieList);
        mv.setViewName("classify/lookChdClassify");
        return mv;
    }

    /**
     * 删除子分类分类
     *
     * @param id
     * @return
     */
    @ResponseBody
    @DeleteMapping("/chdClassify.do")
    public ResultResponse delChdClassify(String id) {
        boolean result = classifyService.deChdClassifylById(id);
        if (!result) {
            return Result.resuleError("删除失败");
        }
        return Result.resuleSuccess();
    }

    /**
     * 新增子分类
     *
     * @param ptId
     * @return
     */
    @ResponseBody
    @PostMapping("/getChdClassifys.do")
    public List<ChdClassify> getChdClassifys(String ptId) {
        List<ChdClassify> chdClassifieList = classifyService.selectChdClassifyList(ptId);
        return chdClassifieList;
    }
}