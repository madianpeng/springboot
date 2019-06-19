package top.madianpeng.config;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class IndexController {
	
	@RequiresRoles("admin")
	@RequiresPermissions("index")
	@RequestMapping("/main")
	public String main() {
		return "index";
	}
	
	@RequiresRoles("admin")
	@RequiresPermissions("控制台")
	@RequestMapping("/console")
	public String console() {
		return "home/homepage2";
	}
}
