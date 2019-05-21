package top.madianpeng.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import top.madianpeng.pojo.BcStaff;
import top.madianpeng.pojo.PageBean;
import top.madianpeng.pojo.ReturnValue;
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
	
	/**
	 * 添加取派员
	 * @param staff
	 * @return
	 */
	@RequestMapping("/addstaff")
	@ResponseBody
	public ReturnValue addStaff(BcStaff staff) {
		ReturnValue returnValue=staffService.addStaff(staff);
		return returnValue;
	}
	
	/**
	 * 去修改页面
	 * @param staff
	 * @param String
	 * @return
	 */
	@RequestMapping("/modifypage")
	public String modifyPage(BcStaff staff,Model model) {
		BcStaff pageinfo = staffService.queryByID(staff);
		model.addAttribute("staff", pageinfo);
		return "/basic/modifyform";
		
	}
	
	/**
	 * 修改取派员
	 * @param bcStaff
	 * @return
	 */
	@RequestMapping("/modifystaff")
	@ResponseBody
	public ReturnValue modifyStaff(BcStaff bcStaff) {
		ReturnValue returnValue=staffService.modifyStaff(bcStaff);
		return returnValue;
		
	}
	
	@RequestMapping("/delstaff")
	@ResponseBody
	public ReturnValue delStaff(String[] ids) {
		List<String> idsList = Arrays.asList(ids);
		ReturnValue returnValue = staffService.delStaff(idsList);
		return returnValue;
	}
	
}
