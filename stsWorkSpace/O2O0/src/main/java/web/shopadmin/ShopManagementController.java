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
import hoggen.wang.util.CodeUtil;
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

	@RequestMapping(value = "/getshopmanagementinfo", method = RequestMethod.GET)
	@ResponseBody
	private Map<String, Object> getShopManagementInfo(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		long shopId = HttpServletRequestUtil.getLong(request, "shopId");
		if (shopId > 0) {
			try {
				Shop currentShop = new Shop();
				currentShop.setShopId(shopId);
				request.getSession().setAttribute("currentShop", currentShop);
				modelMap.put("redirect", false);
			} catch (Exception e) {
				// TODO: handle exception
				modelMap.put("success", false);
				modelMap.put("errMsg", e.toString());
			}

		} else {
			Object currentShop = request.getSession().getAttribute("currentShop");
			if (currentShop == null) {
				modelMap.put("redirect", true);
				modelMap.put("url", "'/O2O/shopadmin/shoplist'");
			} else {
				Shop currentShop1 = (Shop) currentShop;
				modelMap.put("redirect", false);
				modelMap.put("shopId", currentShop1.getShopId());
			}

		}

		return modelMap;
	}

	@RequestMapping(value = "/getshoplist", method = RequestMethod.GET)
	@ResponseBody
	private Map<String, Object> getShopList(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		long ownerId = HttpServletRequestUtil.getLong(request, "ownerId");
		// long pageIndex = HttpServletRequestUtil.getLong(request, "pageIndex");
		// long pageSize = HttpServletRequestUtil.getLong(request, "pageSize");
		// List<Shop> shopList = new ArrayList<Shop>();
		if (ownerId > 0) {
			try {
				Shop shopCondition = new Shop();
				shopCondition.setOwnerId(ownerId);
				ShopExecution shopExecution = shopService.getShopList(shopCondition, 0, 100);
				modelMap.put("shopList", shopExecution.getShopList());
				modelMap.put("ownerId", ownerId);
				modelMap.put("success", true);
			} catch (Exception e) {
				// TODO: handle exception
				modelMap.put("success", false);
				modelMap.put("errMsg", e.toString());
			}

		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "ownerId id is invalid");
		}

		return modelMap;
	}

	@RequestMapping(value = "/getshopbyid", method = RequestMethod.GET)
	@ResponseBody
	private Map<String, Object> getShopById(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		long shopId = HttpServletRequestUtil.getLong(request, "shopId");
		if (shopId > 0) {
			try {
				Shop shop = shopService.getByShopId(shopId);
				List<Area> areas = areaService.getAreaList();
				modelMap.put("shop", shop);
				modelMap.put("areaList", areas);
				modelMap.put("success", true);
			} catch (Exception e) {
				// TODO: handle exception
				modelMap.put("success", false);
				modelMap.put("errMsg", e.toString());
			}

		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "shop id is valid");
		}

		return modelMap;
	}

	@RequestMapping(value = "/modifyshop", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> modifyShop(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		if (!CodeUtil.checkVerifyCode(request)) {
			modelMap.put("success", false);
			modelMap.put("errMsg", "验证码错误");
			return modelMap;
		}
		// 1.接收并转化相应的参数
		String shopStr = HttpServletRequestUtil.getString(request, "shopStr");
		if (shopStr == null || shopStr == "") {
			modelMap.put("success", false);
			modelMap.put("errMsg", "请输入店铺信息");
			return modelMap;
		}
		ObjectMapper mapper = new ObjectMapper();
		Shop shop = null;
		try {
			shop = mapper.readValue(shopStr, Shop.class);
		} catch (Exception e) {
			e.printStackTrace();
			modelMap.put("success", false);
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
		}
		// 2.修改店铺信息
		if (shop != null && shop.getShopId() != null) {
			// session TODO
			// long ownerId = (long) request.getSession().getAttribute("user");

			shop.setOwnerId(1l);
			ShopExecution sExecution = shopService.modifyShop(shop, shopImg);
			if (sExecution.getState() == ShopStateEnum.SUCESS.getState()) {
				modelMap.put("success", true);
			} else {
				modelMap.put("success", false);
				modelMap.put("errMsg", sExecution.getStateInfo());
			}

		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "没有店铺ID信息");
			return modelMap;
		}

		// 3.返回结构
		return modelMap;
	}

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
			modelMap.put("success", false);
			modelMap.put("errMsg", e.toString());
		}
		return modelMap;

	}

	@RequestMapping(value = "/registershop", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> registerShop(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		if (!CodeUtil.checkVerifyCode(request)) {
			modelMap.put("success", false);
			modelMap.put("errMsg", "验证码错误");
			return modelMap;
		}
		// 1.接收并转化相应的参数
		String shopStr = HttpServletRequestUtil.getString(request, "shopStr");
		if (shopStr == null || shopStr == "") {
			modelMap.put("success", false);
			modelMap.put("errMsg", "请输入店铺信息");
			return modelMap;
		}
		ObjectMapper mapper = new ObjectMapper();
		Shop shop = null;
		try {
			shop = mapper.readValue(shopStr, Shop.class);
		} catch (Exception e) {
			e.printStackTrace();
			modelMap.put("success", false);
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
			modelMap.put("success", false);
			modelMap.put("errMsg", "上传图片不能为空");
			return modelMap;
		}
		// 2.注册店铺
		if (shop != null && shopImg != null) {

			shop.setOwnerId(1l);
			ShopExecution sExecution = shopService.addShop(shop, shopImg);
			if (sExecution.getState() == ShopStateEnum.CHECK.getState()) {
				modelMap.put("success", true);
				// 该用户可以操作的店铺列表
				@SuppressWarnings("unchecked")
				List<Shop> shopList = (List<Shop>) request.getSession().getAttribute("shopList");
				if (shopList == null || shopList.size() == 0) {
					shopList = new ArrayList<Shop>();
				}
				shopList.add(sExecution.getShop());
				request.getSession().setAttribute("shopList", shopList);
			} else {
				modelMap.put("success", false);
				modelMap.put("errMsg", sExecution.getStateInfo());
			}

		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "没有店铺信息");
			return modelMap;
		}

		// 3.返回结构
		return modelMap;
	}

}
