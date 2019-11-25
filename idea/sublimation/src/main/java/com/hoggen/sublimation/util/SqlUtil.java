package com.hoggen.sublimation.util;

public class SqlUtil {


    public  static  String creatFrendshipApplySql(String tablename){
        String uSql = "create table IF NOT EXISTS `tab_"+tablename + "`"
                +"(`id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id 朋友关系id编号',"
                +"`user_id` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '用户id',"
                +"`friend_id` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '朋友id',"
                +"`status` int(2) NOT NULL DEFAULT '0' COMMENT '添加好友关系，0添加为完成，1添加已经完成',"
                +"`create_time` datetime DEFAULT NULL COMMENT '创建时间',"
                +"PRIMARY KEY (`id`) "
                +")ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='朋友关系申请表'";

        return uSql;
    }
}
