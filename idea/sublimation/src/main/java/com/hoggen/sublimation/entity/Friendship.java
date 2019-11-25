package com.hoggen.sublimation.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
public class Friendship {

    // ID
    private long id;

    // ID
    private String userId;

    // ID
    private String friendId;

    // 创建时间
    private Date createTime;

}
