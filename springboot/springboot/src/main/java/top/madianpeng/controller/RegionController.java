package top.madianpeng.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import top.madianpeng.pojo.BcRegion;
import top.madianpeng.pojo.PageBean;
import top.madianpeng.pojo.ReturnValue;
import top.madianpeng.service.RegionService;
import top.madianpeng.utils.POIUtils;
import top.madianpeng.utils.PinYin4jUtils;

@Controller
@RequestMapping("/region")
public class RegionController {
	@Autowired
	private RegionService regionService;

	/**
	 * 分区列表查询
	 * @param region
	 * @return
	 */
	@RequestMapping("/querylist")
	@ResponseBody
	public PageBean<BcRegion> queryRegion(BcRegion region) {
		PageBean<BcRegion> pageBean = regionService.queryRegion(region);
		return pageBean;
	}
	/**
	 *    添加区域
	 * @param region
	 * @return
	 */
	@RequestMapping("/addregion")
	@ResponseBody
	public ReturnValue addRegion(BcRegion region) {
		
		ReturnValue returnValue=regionService.addRegion(region);
		return returnValue;
	}
	/**
	 * 分区批量导入
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping("/import")
	@ResponseBody
	public ReturnValue importAdd(@RequestParam(value = "file", required = true) MultipartFile file) throws IOException {
		POIUtils poiUtils = new POIUtils();
		List<BcRegion> regionlist = new ArrayList<BcRegion>();
		List<String[]> importExcel = poiUtils.importExcel(file);
		for (String[] strings : importExcel) {
			BcRegion region = new BcRegion();
			region.setId(strings[0]);
			region.setProvince(strings[1]);
			region.setCity(strings[2]);
			region.setDistrict(strings[3]);
			region.setPostcode(strings[4]);
			String shortcode = strings[1]+strings[2]+strings[3];
			String[] headByString = PinYin4jUtils.getHeadByString(shortcode);
			shortcode=StringUtils.join(headByString, "");
			region.setShortcode(shortcode);
			region.setCitycode(PinYin4jUtils.hanziToPinyin(strings[2], ""));
			regionlist.add(region);
		}
		ReturnValue returnValue=regionService.importAdd(regionlist);
		return returnValue;
	}
	
}
