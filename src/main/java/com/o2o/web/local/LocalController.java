package com.o2o.web.local;


import com.o2o.dto.LocalAuthExecution;
import com.o2o.entity.LocalAuth;
import com.o2o.entity.PersonInfo;
import com.o2o.enums.LocalAuthStateEnum;
import com.o2o.service.LocalAuthService;
import com.o2o.util.HttpServletRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/local", method = {RequestMethod.GET, RequestMethod.POST})
public class LocalController {//TODO:未被测试

    @Autowired
    private LocalAuthService localAuthService;

    @RequestMapping(value = "/bindlocalauth", method = RequestMethod.POST)
    @ResponseBody
    private Map<String, Object> bindLocalAuth(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>();
        String userName = HttpServletRequestUtil.getString(request, "userName");
        String password = HttpServletRequestUtil.getString(request, "password");
        PersonInfo personInfo = (PersonInfo) request.getSession().getAttribute("user");
        if (userName == null || password == null || personInfo == null || personInfo.getUserId() == null) {
            modelMap.put("success", false);
            modelMap.put("errMsg", "用户名和密码均不能为空");
        } else {
            LocalAuth localAuth = new LocalAuth();
            localAuth.setUserName(userName);
            localAuth.setPassword(password);
            localAuth.setPersonInfo(personInfo);
            LocalAuthExecution localAuthExecution = localAuthService.bindLocalAuth(localAuth);
            if (localAuthExecution.getState() == LocalAuthStateEnum.SUCCESS.getState()) {
                modelMap.put("success", true);
            } else {
                modelMap.put("success", false);
                modelMap.put("errMsg", localAuthExecution.getStateInfo());
            }
        }
        return modelMap;
    }

    @RequestMapping(value = "/changelocalpwd", method = RequestMethod.POST)
    @ResponseBody
    private Map<String, Object> changeLocalPwd(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>();

        String userName = HttpServletRequestUtil.getString(request, "userName");
        String password = HttpServletRequestUtil.getString(request, "password");
        String newPassword = HttpServletRequestUtil.getString(request, "newPassword");
        PersonInfo user = (PersonInfo) request.getSession().getAttribute("user");

        if (userName == null || password == null || newPassword == null ||
                user == null || password.equals(newPassword) || user.getUserId() == null) {
            modelMap.put("success", false);
            modelMap.put("errMsg", "用户信息不能为空");
            return modelMap;
        }
        try {
            LocalAuth localAuth = localAuthService.getLocalAuthByUserId(user.getUserId());
            if (localAuth == null || !localAuth.getUserName().equals(userName)) {
                modelMap.put("success", false);
                modelMap.put("errMsg", "输入的帐号非本次登录的帐号");
                return modelMap;
            }
            LocalAuthExecution localAuthExecution =
                    localAuthService.modifyLocalAuth(user.getUserId(), userName, password, newPassword);
            if (localAuthExecution.getState() == LocalAuthStateEnum.SUCCESS.getState()) {
                modelMap.put("success", true);
            } else {
                modelMap.put("success", false);
                modelMap.put("errMsg", localAuthExecution.getStateInfo());
            }
        } catch (Exception e) {
            modelMap.put("success", false);
            modelMap.put("errMsg", e.toString());
            return modelMap;
        }
        return modelMap;
    }

    @RequestMapping(value = "/logincheck", method = RequestMethod.POST)
    @ResponseBody
    private Map<String, Object> logincheck(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>();
        String userName = HttpServletRequestUtil.getString(request, "userName");
        String password = HttpServletRequestUtil.getString(request, "password");
        if (userName == null || password == null) {
            modelMap.put("success", false);
            modelMap.put("errMsg", "用户名或者密码错误");
            return modelMap;
        }
        LocalAuth localAuth = localAuthService.getLocalAuthByUsernameAndPwd(userName, password);
        if (localAuth != null) {
            modelMap.put("success", true);
            request.getSession().setAttribute("user", localAuth.getPersonInfo());
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "用户名或密码错误");
        }
        return modelMap;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    @ResponseBody
    private Map<String, Object> logout(HttpServletRequest request) {
        Map<String,Object> modelMap=new HashMap<>();
        request.getSession().setAttribute("user",null);
        modelMap.put("success", true);
        return modelMap;
    }
}
