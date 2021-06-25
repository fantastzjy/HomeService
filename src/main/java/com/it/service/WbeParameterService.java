package com.it.service;

import com.it.entity.Arguments;
import com.it.entity.LoginReg;
import com.it.entity.WbeParameter;

public interface WbeParameterService {


    /**
     * 修改
     *
     * @param wbeParameter
     * @return
     */
    boolean editById(WbeParameter wbeParameter);

    /**
     * 修改登录注册参数设置
     *
     * @param loginReg
     * @return
     */
    boolean editById(LoginReg loginReg);

    /**
     * 修改网站主题样式参数设置
     *
     * @param arguments
     * @return
     */
    boolean editById(Arguments arguments);

    /**
     * 得到登录注册参数设置
     *
     * @return
     */
    LoginReg getLoginReg();

    /**
     * 得到网站参数设置
     *
     * @return
     */
    WbeParameter getWbeParameter();

    /**
     * 得到网站主题样式参数设置
     *
     * @return
     */
    Arguments getArguments();

}
