package top.madianpeng.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import top.madianpeng.pojo.BcDecidedzone;
import top.madianpeng.pojo.BcRegion;
import top.madianpeng.pojo.BcSubarea;
import top.madianpeng.pojo.PageBean;
import top.madianpeng.pojo.ReturnValue;
import top.madianpeng.service.DecideZoneService;
import top.madianpeng.service.RegionService;
import top.madianpeng.service.SubareaService;
import top.madianpeng.utils.POIUtils;
import top.madianpeng.utils.PinYin4jUtils;

@Controller
@RequestMapping("/decidezone")
public class DecideZoneController {
	@Autowired
	private DecideZoneService decideZoneService;

	/**
	 * 分区列表查询
	 * 
	 * @param region
	 * @return
	 */
	@RequestMapping("/querylist")
	@ResponseBody
	public PageBean<BcDecidedzone> queryDecidedzone(BcDecidedzone decidedzone) {
		PageBean<BcDecidedzone> pageBean = decideZoneService.queryDecidedzone(decidedzone);
		return pageBean;
	}
	
	/**
	 * 查看关联分区
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/querySubarea")
	public String querySubarea(String id,Model model) {
		model.addAttribute("decidezoneid", id);
		return "/basic/decidezone/subarea";
	}
	
	/**
	 * 添加分区
	 * @return
	 */
	@RequestMapping("/adddecidezone")
	@ResponseBody
	public ReturnValue addDecidezone(BcDecidedzone decidedzone) {
		ReturnValue ReturnValue =decideZoneService.addDecidedzone(decidedzone);
		return ReturnValue;
	}
	
	/**
	 * 去修改页面
	 * @param id
	 * @return
	 */
	@RequestMapping("/modifypage")
	public String modifyPage(String id,Model model) {
		BcDecidedzone decidedzone = decideZoneService.findById(id);
		String[] subareaid = decideZoneService.findSubarea(id);
		model.addAttribute("decidedzone", decidedzone);
		model.addAttribute("subareaid", subareaid);
		return "/basic/decidezone/modifyform";
		
	}
}
