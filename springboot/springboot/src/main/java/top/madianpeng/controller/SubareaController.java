package top.madianpeng.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import top.madianpeng.pojo.Area;
import top.madianpeng.pojo.BcRegion;
import top.madianpeng.pojo.BcSubarea;
import top.madianpeng.pojo.PageBean;
import top.madianpeng.pojo.ReturnValue;
import top.madianpeng.service.RegionService;
import top.madianpeng.service.SubareaService;
import top.madianpeng.utils.POIUtils;
import top.madianpeng.utils.PinYin4jUtils;

@Controller
@RequestMapping("/subarea")
public class SubareaController {
	@Autowired
	private SubareaService subareaService;

	/**
	 * 分区列表查询
	 * 
	 * @param region
	 * @return
	 */
	@RequestMapping("/querylist")
	@ResponseBody
	public PageBean<BcSubarea> querySubarea(BcSubarea subarea) {
		PageBean<BcSubarea> pageBean = subareaService.querySubarea(subarea);
		return pageBean;
	}

	/**
	 * 添加分区
	 * 
	 * @param subarea
	 * @return
	 */
	@RequestMapping("/addsubarea")
	@ResponseBody
	public ReturnValue addSubarea(BcSubarea subarea) {
		ReturnValue returnValue = subareaService.addSubarea(subarea);
		return returnValue;
	}

	/**
	 * 去修改页
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/modifypage")
	public String modifyPage(String id, Model model) {
		BcSubarea subarea = subareaService.findById(id);
		model.addAttribute("subarea", subarea);
		return "/basic/subarea/modifyform";
	}

	/**
	 * 修改分区
	 * 
	 * @param subarea
	 * @return
	 */
	@RequestMapping("/modifysubarea")
	@ResponseBody
	public ReturnValue update(BcSubarea subarea) {
		ReturnValue returnValue = subareaService.update(subarea);
		return returnValue;
	}

	/**
	 * 删除
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping("/delsubarea")
	@ResponseBody
	public ReturnValue delSubarea(String[] ids) {
		List<String> id = Arrays.asList(ids);
		ReturnValue returnValue = subareaService.delSubarea(id);
		return returnValue;
	}
	/**
	 * 省市区树形结构查询
	 * @return
	 */
	@RequestMapping("/querycity")
	@ResponseBody
	public List<Area> queryCity() {
		List<Area> queryCity = subareaService.queryCity();
		return queryCity;
	}
	/**
	 * 为定区查询
	 * @return
	 */
	@RequestMapping("/queryfordecide")
	@ResponseBody
	public List<BcSubarea> queryForDecide(String id) {
		List<BcSubarea> list = subareaService.queryForDecide(id);
		return list;
	}
}
