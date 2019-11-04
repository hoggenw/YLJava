package com.hoggen.sublimation.config.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class RoleInterceptor implements HandlerInterceptor {

//    @Autowired
//    UserDao rUserDao;
//
//    private static final Logger logger = LoggerFactory.getLogger(StockController.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        Map<String, Object> modelMapData = new HashMap<String, Object>();
        ObjectMapper mapper = new ObjectMapper();
        boolean flag = false;
//
//        if(AuthorityUtil.ifSuperManager(request)){
//            return true;
//        }
//
//        logger.info("request.getPathInfo"+ request.getRequestURL());
//        if(request.getRequestURL().toString().contains("api/stock")
//                && !AuthorityUtil.ifStocker(request)){
//            logger.info("stock 非法用户");
//            flag = true;
//        }
//
//        if(request.getRequestURL().toString().contains("api/operation")
//                && !AuthorityUtil.ifOperator(request)){
//            logger.info("operation 非法用户");
//            flag = true;
//        }
//
//        if( request.getRequestURL().toString().contains("api/driver")
//                && !AuthorityUtil.ifDriver(request)){
//            logger.info("driver 非法用户");
//            flag = true;
//        }
//
//        if(flag){
//            modelMap.put("errno", StockStatusEnum.OFFLINE.getState());
//            modelMap.put("errmsg", StockStatusEnum.OFFLINE.getStateInfo());
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
