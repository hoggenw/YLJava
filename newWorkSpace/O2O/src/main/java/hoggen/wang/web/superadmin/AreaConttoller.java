package hoggen.wang.web.superadmin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import hoggen.wang.entity.Area;
import hoggen.wang.service.AreaService;

@Controller
@RequestMapping("/superadmin")
public class AreaConttoller {

	@Autowired
	private AreaService areaService;

	@RequestMapping(value = "/listarea", method = RequestMethod.GET)
	// 转换为json
	@ResponseBody
	private Map<String, Object> listArea() {
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
		return modelMap;
	}
}
