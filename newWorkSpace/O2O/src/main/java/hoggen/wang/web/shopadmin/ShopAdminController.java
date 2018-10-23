package hoggen.wang.web.shopadmin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "shopadmin", method = RequestMethod.GET)
public class ShopAdminController {
	@RequestMapping(value = "/shopoperation")
	public String shopOperation() {
		return "shop/shopoperation";
	}

	@RequestMapping(value = "/shoplist")
	public String shopList() {
		return "shop/shoplist";
	}

	@RequestMapping(value = "/shopmanage")
	public String shopManage() {
		return "shop/shopmanage";
	}

	@RequestMapping(value = "/productmanage")
	public String productManage() {
		return "shop/productmanage";
	}

	@RequestMapping(value = "/productcategorymanage")
	public String shopCategoryManage() {
		return "shop/productcategorymanage";
	}

	@RequestMapping(value = "/productdetail")
	public String productdetail() {
		return "frontend/productdetail";
	}

	@RequestMapping(value = "/index")
	public String index() {
		return "frontend/index";
	}

	@RequestMapping(value = "/productedit")
	public String productEdit() {
		return "shop/productedit";
	}

}
