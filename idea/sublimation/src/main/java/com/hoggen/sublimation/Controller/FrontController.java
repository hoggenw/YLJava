package com.hoggen.sublimation.Controller;


import com.hoggen.sublimation.entity.TestModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class FrontController {

    @RequestMapping(value = "/info",method = RequestMethod.GET)
    public Map<String, Object> infoTest() {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        modelMap.put("ErrorCode", "0");
        modelMap.put("Message", "操作成功");
        return modelMap;
    }


    @RequestMapping(value = "/model",method = RequestMethod.GET)
    public TestModel infoModel() {
        TestModel model = new TestModel();
        model.setName("hoggen668");
        model.setFoo(true);
        model.setUserId(1002);
        return model;
    }
}
