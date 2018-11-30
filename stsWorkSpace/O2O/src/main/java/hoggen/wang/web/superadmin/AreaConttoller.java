package hoggen.wang.web.superadmin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ch.qos.logback.classic.Logger;
import hoggen.wang.entity.Area;
import hoggen.wang.service.AreaService;

@Controller
@RequestMapping("/superadmin")
public class AreaConttoller {

	Logger Logger = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger(AreaConttoller.class);

	@Autowired
	private AreaService areaService;

	@RequestMapping(value = "/listarea", method = RequestMethod.GET)
	// 转换为json
	@ResponseBody
	private Map<String, Object> listArea() {

		Logger.info("=====start=====");
		Long startTime = System.currentTimeMillis();// 获取毫秒数
		Map<String, Object> modelMap = new HashMap<String, Object>();
		List<Area> list = new ArrayList<Area>();
		try {
			list = areaService.getAreaList();
			modelMap.put("rows", list);
			modelMap.put("total", list.size());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			modelMap.put("sucess", false);
			modelMap.put("errMsg", e.toString());
		}
		Logger.error("test error");
		Long endTime = System.currentTimeMillis();// 获取毫秒数
		Logger.debug("costTime:[{}ms]", endTime - startTime);
		Logger.info("=====end=====");
		return modelMap;
	}

	@RequestMapping(value = "/index")
	public String shopOperation() {
		return "index";
	}
}
