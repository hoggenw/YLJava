package com.hoggen.COMangerment.util;

import java.util.HashMap;
import java.util.Map;

public class RetrunDataStructModelUtil {

    public static Map<String, Object> returnCode(Integer code, String errmsg, Object data){
        Map<String,Object> map = new HashMap<String,Object>();

        map.put("errno", code);
        map.put("errmsg", errmsg);
        map.put("data", data);
        return map;
    }
}
