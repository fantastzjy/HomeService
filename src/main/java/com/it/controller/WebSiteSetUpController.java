package com.it.controller;


import com.it.entity.Arguments;
import com.it.entity.LoginReg;
import com.it.entity.WbeParameter;
import com.it.service.WbeParameterService;
import com.it.util.ItdragonUtils;
import com.it.util.Result;
import com.it.util.ResultResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @version:
 * @Description: 网站设置相关接口
 */
@RequestMapping("/wbeSet")
@Controller
public class WebSiteSetUpController {
    private static final transient Logger log = LoggerFactory.getLogger(WebSiteSetUpController.class);
    @Autowired
    private WbeParameterService wbeParameterService;

    /**
     * 网站参数设置页面跳转接口
     *
     * @param mv
     * @return
     */
    @GetMapping("/wbeParameter.do")
    public ModelAndView wbeParameter(ModelAndView mv) {
        WbeParameter wbeParameter = wbeParameterService.getWbeParameter();
        mv.addObject("wbeParameter", wbeParameter);
        mv.setViewName("/wbeParameter/index");
        return mv;
    }

    /**
     * 登录注册参数设置页面跳转接口
     *
     * @param mv
     * @return
     */
    @GetMapping("/loginReg.do")
    public ModelAndView loginReg(ModelAndView mv) {
        LoginReg loginReg = wbeParameterService.getLoginReg();
        mv.addObject("loginReg", loginReg);
        mv.setViewName("/wbeParameter/loginRegIndex");
        return mv;
    }

    /**
     * 网站前台主题参数设置页面跳转接口
     *
     * @param mv
     * @return
     */
    @GetMapping("/arguments.do")
    public ModelAndView arguments(ModelAndView mv) {
        Arguments arguments = wbeParameterService.getArguments();
        mv.addObject("arguments", arguments);
        mv.setViewName("/wbeParameter/argumentsIndex");
        return mv;
    }

    /**
     * 修改网站参数的接口
     *
     * @param wbeParameter
     * @return
     */
    @ResponseBody
    @PutMapping("/wbeParameter.do")
    public ResultResponse editWbeParameter(WbeParameter wbeParameter) {
        boolean result = wbeParameterService.editById(wbeParameter);
        if (!result) {
            return Result.resuleError("操作失败");
        }
        return Result.resuleSuccess();
    }

    /**
     * 修改网站参数的接口
     *
     * @param arguments
     * @return
     */
    @ResponseBody
    @PutMapping("/arguments.do")
    public ResultResponse editWbeParameter(Arguments arguments) {
        boolean result = wbeParameterService.editById(arguments);
        if (!result) {
            return Result.resuleError("操作失败");
        }
        return Result.resuleSuccess();
    }

    /**
     * 修改网站参数的接口
     *
     * @param loginReg
     * @return
     */
    @ResponseBody
    @PutMapping("/loginReg.do")
    public ResultResponse editWbeParameter(LoginReg loginReg) {
        if (!ItdragonUtils.stringIsNotBlack(loginReg.getIsAddress())) {
            loginReg.setIsAddress("off");
        }
        if (!ItdragonUtils.stringIsNotBlack(loginReg.getIsEmail())) {
            loginReg.setIsEmail("off");
        }
        if (!ItdragonUtils.stringIsNotBlack(loginReg.getIsIdCard())) {
            loginReg.setIsIdCard("off");
        }
        if (!ItdragonUtils.stringIsNotBlack(loginReg.getIsRealName())) {
            loginReg.setIsRealName("off");
        }
        if (!ItdragonUtils.stringIsNotBlack(loginReg.getIsSex())) {
            loginReg.setIsSex("off");
        }
        if (!ItdragonUtils.stringIsNotBlack(loginReg.getIsImg())) {
            loginReg.setIsImg("off");
        }
        if (!ItdragonUtils.stringIsNotBlack(loginReg.getIsPhone())) {
            loginReg.setIsPhone("off");
        }
        boolean result = wbeParameterService.editById(loginReg);
        if (!result) {
            return Result.resuleError("操作失败");
        }
        return Result.resuleSuccess();
    }
}
