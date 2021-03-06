package top.madianpeng.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import top.madianpeng.pojo.BcStaff;
import top.madianpeng.pojo.PageBean;
import top.madianpeng.pojo.ReturnValue;
import top.madianpeng.service.StaffService;
import top.madianpeng.utils.POIUtils;

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
		return "/basic/staff/modifyform";
		
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
	
	/**
	 * 删除取派员
	 * @param ids
	 * @return
	 */
	@RequestMapping("/delstaff")
	@ResponseBody
	public ReturnValue delStaff(String[] ids) {
		List<String> idsList = Arrays.asList(ids);
		ReturnValue returnValue = staffService.delStaff(idsList);
		return returnValue;
	}
	
	/**
	 * 为定区查询取派员
	 * @return
	 */
	@RequestMapping("/queryfordecide")
	@ResponseBody
	public List<BcStaff> queryForDecide(){
		List<BcStaff> list = staffService.queryForDecide();
		return list;
	}
}
