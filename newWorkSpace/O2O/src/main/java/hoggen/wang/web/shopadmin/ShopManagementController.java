package hoggen.wang.web.shopadmin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.fasterxml.jackson.databind.ObjectMapper;

import hoggen.wang.dto.ShopExecution;
import hoggen.wang.entity.Area;
import hoggen.wang.entity.Shop;
import hoggen.wang.entity.ShopCategory;
import hoggen.wang.enums.ShopStateEnum;
import hoggen.wang.service.AreaService;
import hoggen.wang.service.ShopCategoryService;
import hoggen.wang.service.ShopService;
import hoggen.wang.util.HttpServletRequestUtil;

@Controller
@RequestMapping("/shopadmin")
public class ShopManagementController {
	@Autowired
	private ShopService shopService;
	@Autowired
	private ShopCategoryService shopCategoryService;
	@Autowired
	private AreaService areaService;

	@RequestMapping(value = "/getshopinitinfo", method = RequestMethod.GET)
	@ResponseBody
	private Map<String, Object> getShopInitInfo() {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		List<Area> list = new ArrayList<Area>();
		List<ShopCategory> shopCategories = new ArrayList<ShopCategory>();
		try {
			ShopCategory sc = new ShopCategory();
			sc.setShopCategoryDesc("描述");
			list = areaService.getAreaList();
			shopCategories = shopCategoryService.getShopCategoryList(sc);
			modelMap.put("areaList", list);
			modelMap.put("shopCategoryList", shopCategories);
			modelMap.put("success", true);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			modelMap.put("sucess", false);
			modelMap.put("errMsg", e.toString());
		}
		return modelMap;

	}

	@RequestMapping(value = "/registershop", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> registerShop(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		// 1.接收并转化相应的参数
		String shopStr = HttpServletRequestUtil.getString(request, "shopStr");
		ObjectMapper mapper = new ObjectMapper();
		Shop shop = null;
		try {
			shop = mapper.readValue(shopStr, Shop.class);
		} catch (Exception e) {
			modelMap.put("sucess", false);
			modelMap.put("errMsg", e.getMessage());
			return modelMap;
			// TODO: handle exception
		}
		CommonsMultipartFile shopImg = null;
		CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		if (commonsMultipartResolver.isMultipart(request)) {
			MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
			shopImg = (CommonsMultipartFile) multipartHttpServletRequest.getFile("shopImg");
		} else {
			modelMap.put("sucess", false);
			modelMap.put("errMsg", "上传图片不能为空");
			return modelMap;
		}
		// 2.注册店铺
		if (shop != null && shopImg != null) {

			shop.setOwnerId(1l);
			ShopExecution sExecution = shopService.addShop(shop, shopImg);
			if (sExecution.getState() == ShopStateEnum.CHECK.getState()) {
				modelMap.put("sucess", true);
			} else {
				modelMap.put("sucess", false);
				modelMap.put("errMsg", sExecution.getStateInfo());
			}

		} else {
			modelMap.put("sucess", false);
			modelMap.put("errMsg", "没有店铺信息");
			return modelMap;
		}

		// 3.返回结构

		return modelMap;

	}

}
