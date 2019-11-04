package com.hoggen.sublimation.config.controller;


import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class TokenInterceptor implements HandlerInterceptor {

//    @Autowired
//    UserDao rUserDao;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
//        boolean flag;
//        String token = request.getHeader("token");
//        // String token2 = HttpServletRequestUtil.getString(request, "token");
//        Map<String, Object> modelMap = new HashMap<String, Object>();
//        Map<String, Object> modelMapData = new HashMap<String, Object>();
//        // String token3 = (token == null) ? token2 : token;
//        ObjectMapper mapper = new ObjectMapper();
//        if (token == null || token.length() <= 0){
//            modelMap.put("errno", "-10002");
//            modelMap.put("errmsg", "未登录用户");
//            modelMap.put("data", modelMapData);
//            response.setContentType("application/json; charset=utf-8");
//            PrintWriter writer = response.getWriter();
//            writer.print(mapper.writeValueAsString(modelMap));
//            writer.close();
//            response.flushBuffer();
//            return false;
//        }
//        Long userid = Long.valueOf(JwtUtil.getLoginUserID(token));
//        User loginUser = rUserDao.queryTokenUserId(userid);
//        if (loginUser == null || !token.equals(loginUser.getToken())) {
//            modelMap.put("errno", "-10001");
//            modelMap.put("errmsg", "非法用户");
//            modelMap.put("data", modelMapData);
//            response.setContentType("application/json; charset=utf-8");
//            PrintWriter writer = response.getWriter();
//            writer.print(mapper.writeValueAsString(modelMap));
//            writer.close();
//            response.flushBuffer();
//            return false;
//        }
        return true;
    }


}
