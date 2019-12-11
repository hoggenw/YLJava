package com.hoggen.sublimation.Controller.friend;


import com.hoggen.sublimation.dto.RegisterDTO;
import com.hoggen.sublimation.dto.ReturnUserDTO;
import com.hoggen.sublimation.dto.UserExecution;
import com.hoggen.sublimation.entity.FriendshipApply;
import com.hoggen.sublimation.entity.User;
import com.hoggen.sublimation.enums.UserStateEnum;
import com.hoggen.sublimation.service.httpsevice.FriendshipApplyService;
import com.hoggen.sublimation.util.ResponedUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value="/api/apply/friend")
@Api(tags = "用户关系模块")
@Slf4j
public class FriendshipApplyController {
    @Autowired
    private FriendshipApplyService applyService;


    @RequestMapping(value = "/creat", method = RequestMethod.POST)
    @ApiOperation(value = "好友申请")
    @ResponseBody
    private Map<String, Object> createApply( @Validated @RequestBody FriendshipApply apply)  {
        Map<String, Object> modelMap = new HashMap<String, Object>();

        List<FriendshipApply> friendshipApplies = applyService.queryApplyList(apply.getUserId());
        for (FriendshipApply model: friendshipApplies) {
            if (model.getUserId().equals(apply.getUserId()) ){
                //已经存在
                if (model.getFriendId().equals(apply.getFriendId())){
                    if (model.getStatus() == 0){

                        return ResponedUtils.returnCode(UserStateEnum.APPLYALREADY.getState(),UserStateEnum.APPLYALREADY.getStateInfo(),modelMap);
                    }

                    if (model.getStatus() == 1){

                        return ResponedUtils.returnCode(UserStateEnum.AGREEALREADY.getState(),UserStateEnum.AGREEALREADY.getStateInfo(),modelMap);
                    }

                }

            }else if (model.getFriendId().equals(apply.getUserId())){

                if (model.getUserId().equals(apply.getFriendId())){
                    //对方已经申请
                    if (model.getStatus() == 0){
                        model.setStatus(1);
                        int effect = applyService.updateFriendship(model);
                        if (effect < 0) {
                            return ResponedUtils.returnCode(UserStateEnum.APPLYFRIENDFAILED.getState(), UserStateEnum.APPLYFRIENDFAILED.getStateInfo(), modelMap);
                        }
                        return ResponedUtils.returnCode(UserStateEnum.SUCCESS.getState(),UserStateEnum.SUCCESS.getStateInfo(),modelMap);
                    }

                    if (model.getStatus() == 1){

                        return ResponedUtils.returnCode(UserStateEnum.AGREEALREADY.getState(),UserStateEnum.AGREEALREADY.getStateInfo(),modelMap);
                    }

                }
            }
        }

        int effect = applyService.insertFriendship(apply);
        if (effect < 0){
            //
            return ResponedUtils.returnCode(UserStateEnum.APPLYFRIENDFAILED.getState(),UserStateEnum.APPLYFRIENDFAILED.getStateInfo(),modelMap);
        }

        return ResponedUtils.returnCode(UserStateEnum.SUCCESS.getState(),UserStateEnum.SUCCESS.getStateInfo(),modelMap);
    }


}
