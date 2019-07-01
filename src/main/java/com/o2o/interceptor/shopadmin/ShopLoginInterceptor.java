package com.o2o.interceptor.shopadmin;

import com.o2o.entity.PersonInfo;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class ShopLoginInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object userObject=request.getSession().getAttribute("user");
        if(userObject!=null){
            PersonInfo user= (PersonInfo) userObject;
            if(user!=null&&user.getUserId()>0&&user.getEnableStatus()==1){
                return true;
            }
        }
        PrintWriter out = response.getWriter();//todo:拦截
        out.println("<html>");
        out.println("<script>");
        out.println("window.open ('" + request.getContextPath() + "/local/login?usertype=2','_self')");
        out.println("</script>");
        out.println("</html>");
        return false;
    }
}
