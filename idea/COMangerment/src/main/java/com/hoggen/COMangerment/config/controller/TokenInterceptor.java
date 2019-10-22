package com.hoggen.COMangerment.config.controller;


import com.fasterxml.jackson.databind.ObjectMapper;

import com.hoggen.COMangerment.dao.AdminDao;
import com.hoggen.COMangerment.dao.UserDao;
import com.hoggen.COMangerment.entity.Admin;
import com.hoggen.COMangerment.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@Component
public class TokenInterceptor implements HandlerInterceptor {

    @Autowired
    AdminDao rUserDao;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        boolean flag;
        String token = request.getHeader("token");
        // String token2 = HttpServletRequestUtil.getString(request, "token");
        Map<String, Object> modelMap = new HashMap<String, Object>();
        Map<String, Object> modelMapData = new HashMap<String, Object>();
        // String token3 = (token == null) ? token2 : token;
        ObjectMapper mapper = new ObjectMapper();
        if (token == null || token.length() <= 0){
            modelMap.put("errno", "-10001");
            modelMap.put("errmsg", "illegal user");
            modelMap.put("data", modelMapData);
            response.setContentType("application/json; charset=utf-8");
            PrintWriter writer = response.getWriter();
            writer.print(mapper.writeValueAsString(modelMap));
            writer.close();
            response.flushBuffer();
            return false;
        }
        Long userid = Long.valueOf(JwtUtil.getLoginUserId(token));
        Admin loginUser = rUserDao.queryByUserId(userid);
        if (loginUser == null || !token.equals(loginUser.getToken())) {
            modelMap.put("errno", "-10001");
            modelMap.put("errmsg", "illegal user");
            modelMap.put("data", modelMapData);
            response.setContentType("application/json; charset=utf-8");
            PrintWriter writer = response.getWriter();
            writer.print(mapper.writeValueAsString(modelMap));
            writer.close();
            response.flushBuffer();
            return false;
        }
        return true;
    }
}
