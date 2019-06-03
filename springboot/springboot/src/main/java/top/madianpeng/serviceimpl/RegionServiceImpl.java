package top.madianpeng.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import top.madianpeng.mapper.BcRegionMapper;
import top.madianpeng.mapper.BcStaffMapper;
import top.madianpeng.pojo.BcRegion;
import top.madianpeng.pojo.BcStaff;
import top.madianpeng.pojo.PageBean;
import top.madianpeng.pojo.ReturnValue;
import top.madianpeng.service.RegionService;
import top.madianpeng.service.StaffService;
import top.madianpeng.utils.IDUtils;
import top.madianpeng.utils.NonUtil;
import top.madianpeng.utils.PinYin4jUtils;

@Service
@Transactional
public class RegionServiceImpl implements RegionService {
	@Autowired
	private BcRegionMapper mapper;

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public PageBean<BcRegion> queryRegion(BcRegion region) {
		PageBean<BcRegion> pageBean = new PageBean<BcRegion>();
		int count = 0;
		if (NonUtil.isNotNon(region.getProvince())) {
			region.setProvince("%" + region.getProvince() + "%");
		}
		if (NonUtil.isNotNon(region.getCity())) {
			region.setCity("%" + region.getCity() + "%");
		}
		if (NonUtil.isNotNon(region.getDistrict())) {
			region.setDistrict("%" + region.getDistrict() + "%");
		}
		List<BcRegion> regionlist = new ArrayList<BcRegion>();
		try {
			PageHelper.startPage(region.getPage(), region.getLimit());
			regionlist = mapper.queryRegion(region);
			count = mapper.queryCount();
		} catch (Exception e) {
			logger.error("分区查询异常：" + e.toString());
			pageBean.isFail("查询");
			return pageBean;
		}
		pageBean.isSuccess("查询");
		pageBean.setData(regionlist);
		if (NonUtil.isNotNon(region)) {
			count = regionlist.size();
		}
		pageBean.setCount(count);
		return pageBean;
	}

	@Override
	public ReturnValue importAdd(List<BcRegion> regionlist) {
		ReturnValue returnValue = new ReturnValue();
		try {
			mapper.importAdd(regionlist);
		} catch (Exception e) {
			logger.error("导入失败：" + e.toString());
			returnValue.isFail("导入");
			return returnValue;
		}
		returnValue.isSuccess("导入");
		return returnValue;
	}

	@Override
	public ReturnValue addRegion(BcRegion region) {
		ReturnValue returnValue = new ReturnValue();
		String shortcode = region.getProvince() + region.getCity() + region.getDistrict();
		String[] headByString = PinYin4jUtils.getHeadByString(shortcode);
		shortcode = StringUtils.join(headByString, "");
		region.setShortcode(shortcode);
		region.setCitycode(PinYin4jUtils.hanziToPinyin(region.getCity(), ""));
		region.setId(IDUtils.getId());
		try {
			mapper.insert(region);
		} catch (Exception e) {
			logger.error("添加失败：" + e.toString());
			returnValue.isFail("添加");
		}
		returnValue.isSuccess("添加");
		return returnValue;
	}

}
