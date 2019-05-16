package top.madianpeng.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import top.madianpeng.pojo.BcStaff;
import top.madianpeng.pojo.PageBean;
import top.madianpeng.service.StaffService;

@Controller
@RequestMapping("/staff")
public class StaffController {
	@Autowired
	private StaffService staffService;
	
	/**
	 * 取派员分页查询
	 * @param staff
	 * @return
	 */
	@RequestMapping("/querylist")
	@ResponseBody
	public PageBean<BcStaff> queryStaff(BcStaff staff) {
		PageBean<BcStaff> pageBean = staffService.queryStaff(staff);
		return pageBean;
	}
}
