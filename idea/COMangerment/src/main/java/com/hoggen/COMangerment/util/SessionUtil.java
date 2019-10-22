package com.hoggen.COMangerment.util;

import com.hoggen.COMangerment.enums.LoginStateEnum;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

public class SessionUtil {

    public static boolean checkToken(HttpServletRequest request, Map<String, Object> modelMap, Map<String, Object> modelMapData) {
        String token = request.getHeader("token");
        HttpSession session = request.getSession();
        String sessionToken = (String)session.getAttribute("token");//在需要使获取token

        if (token == null || sessionToken == null ) {
            modelMap.put("ErrorCode", LoginStateEnum.NOTLOGIN.getState());
            modelMap.put("Message", LoginStateEnum.NOTLOGIN.getStateInfo());
            modelMap.put("Data", modelMapData);
            return true;
        }

        if (!token.equals(sessionToken)) {
            modelMap.put("ErrorCode", LoginStateEnum.USERNONE.getState());
            modelMap.put("Message", LoginStateEnum.USERNONE.getStateInfo());
            modelMap.put("Data", modelMapData);
        }
        return false;
    }
}
