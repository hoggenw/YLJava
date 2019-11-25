package com.hoggen.sublimation.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;


import java.util.Date;

@Data
@Accessors(chain = true)
public class FriendshipApply {
    // ID
    private long id;

    // ID
    private String userId;

    // ID
    private String friendId;


    private Integer status;


    // 创建时间
    private Date createTime;
}
