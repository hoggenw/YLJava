package com.hoggen.sublimation.dto;

import com.hoggen.sublimation.proto.MessageModel;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class FriendshipDTO {

    // ID
    private long id;

    private Integer status;

//    //是否已经删除
//    private Integer delete;

    private List<UserModelDTO> users;

}
