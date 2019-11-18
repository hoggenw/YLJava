package com.hoggen.sublimation.util;


import com.hoggen.sublimation.service.httpsevice.Impl.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * 默认 redisTemplate封装
 */
@Slf4j
public class RedisUtil {

    /**
     * 封装的工具类：默认 127.0.0.1
     *
     */



    @Autowired
    private RedisService redisService;

    //private static ChatRedis redisUtil1 = new ChatRedis() ;


    private  static final String  header = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9";





    /**
     *
     * @Param phone
     * @Param token
     * @Param type
     * @Author:hoggen
     * @Date:17:09 2019-11-14
     */
    public  String saveLoginStatus(String userId ,String token ){

        String returnString = null;
        String[] result = token.split("\\.");
        returnString = result[2];
        String midString =  result[1];
        if (redisService.set(returnString,midString )){
            if (redisService.set(userId,returnString )){
                return  returnString;
            }
        }
        return  returnString;

    }

    public static Boolean ifLogin(String userId ,String token ){

//        String returnString = null;
//        String lastString = (String)chatRedis.get(userId);
//        if (lastString == null){
//            return  false;
//        }
//        String middleString =  (String)chatRedis.get(lastString);
//        if (middleString == null){
//            return  false;
//        }
//
//        String signToken = header+"."+middleString+"."+lastString;
//        String signUserId = JwtUtil.getLoginUserID(signToken);
//
//        return  signUserId.equals(userId);
        return  true;


    }

}
