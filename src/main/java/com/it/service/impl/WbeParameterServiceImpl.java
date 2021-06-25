package com.it.service.impl;

import com.it.entity.Arguments;
import com.it.entity.LoginReg;
import com.it.entity.WbeParameter;
import com.it.mapper.ArgumentsMapper;
import com.it.mapper.LoginRegMapper;
import com.it.mapper.WbeParameterMapper;
import com.it.service.WbeParameterService;
import com.it.util.ItdragonUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 网站参数设置接口
 */
@Service
public class WbeParameterServiceImpl implements WbeParameterService {
    @Resource
    private WbeParameterMapper wbeParameterMapper;
    @Resource
    private LoginRegMapper loginRegMapper;
    @Resource
    private ArgumentsMapper argumentsMapper;
    @Resource
    private ItdragonUtils itdragonUtils;

    @Override
    public boolean editById(WbeParameter wbeParameter) {
        Integer integer = wbeParameterMapper.updateById(wbeParameter);
        if (integer > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean editById(LoginReg loginReg) {
        Integer integer = loginRegMapper.updateById(loginReg);
        if (integer > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean editById(Arguments arguments) {
        Integer integer = argumentsMapper.updateById(arguments);
        if (integer > 0) {
            return true;
        }
        return false;
    }

    @Override
    public LoginReg getLoginReg() {
        List<LoginReg> wbeParameterList = loginRegMapper.selectList(null);
        if (wbeParameterList.isEmpty()) {
            return new LoginReg();
        }
        return wbeParameterList.get(0);
    }

    @Override
    public WbeParameter getWbeParameter() {
        List<WbeParameter> wbeParameterList = wbeParameterMapper.selectList(null);
        if (wbeParameterList.isEmpty()) {
            return new WbeParameter();
        }
        return wbeParameterList.get(0);
    }

    @Override
    public Arguments getArguments() {
        List<Arguments> wbeParameterList = argumentsMapper.selectList(null);
        if (wbeParameterList.isEmpty()) {
            return new Arguments();
        }
        return wbeParameterList.get(0);
    }
}
