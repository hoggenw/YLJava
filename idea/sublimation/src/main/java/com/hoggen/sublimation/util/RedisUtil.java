package com.hoggen.sublimation.util;


import com.hoggen.sublimation.config.redis.ChatRedis;
import com.hoggen.sublimation.config.redis.ChatRedis2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * 默认 redisTemplate封装
 */
@Slf4j
public class RedisUtil {

    /**
     * 封装的工具类：默认 127.0.0.1
     */
    @Autowired
    ChatRedis redisUtil;

    /**
     * 封装的工具类2：192.168.2.169
     */
    @Autowired
    ChatRedis2 redisUtil2;

    private  static final String  header = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9";

    /**
     *
     * @Param phone
     * @Param token
     * @Param type
     * @Author:hoggen
     * @Date:17:09 2019-11-14
     */
    public String saveLoginStatus(String userId ,String token ,int type){

        String returnString = null;
        if (type == 1){
            String[] result = token.split("\\.");
            returnString = result[2];
            String midString =  result[1];
            if (redisUtil.set(returnString,midString )){
                if (redisUtil.set(userId,returnString )){
                    return  returnString;
                }
            }
        }else if (type == 2){
            String[] result = token.split("\\.");
            returnString = result[2];
            String midString =  result[1];
            if (redisUtil2.set(midString, returnString)){
                if (redisUtil2.set(userId, midString)){
                    return  returnString;
                }
            }
        }
        return  returnString;


    }

    public Boolean ifLogin(String userId ,String token ,int type){

        String returnString = null;
        if (type == 1){
            String lastString = redisUtil.get(userId);
            if (lastString == null){
                return  false;
            }
            String middleString =  redisUtil.get(lastString);
            if (middleString == null){
                return  false;
            }

            String signToken = header+"."+middleString+"."+lastString;
            String signUserId = JwtUtil.getLoginUserID(signToken);

            return  signUserId.equals(userId);

        }else if (type == 2){
            String lastString = redisUtil2.get(userId);
            if (lastString == null){
                return  false;
            }
            String middleString =  redisUtil2.get(lastString);
            if (middleString == null){
                return  false;
            }

            String signToken = header+"."+middleString+"."+lastString;
            String signUserId = JwtUtil.getLoginUserID(signToken);

            return  signUserId.equals(userId);
        }
        return  false;


    }

}
