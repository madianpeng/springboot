package top.madianpeng.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import top.madianpeng.pojo.BcArea;
import top.madianpeng.service.AreaService;

@Controller
@RequestMapping("/area")
public class AreaController {
	@Autowired
	private AreaService service;
	
	/**
	 * 查询地区
	 * @param area
	 * @return
	 */
	@RequestMapping("/query")
	@ResponseBody
	public List<BcArea> query(BcArea area) {
		List<BcArea> list = service.queryAear(area);
		return list;
	}
}
