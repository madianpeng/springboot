package top.madianpeng.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;

import top.madianpeng.mapper.BcSubareaMapper;
import top.madianpeng.pojo.Area;
import top.madianpeng.pojo.BcSubarea;
import top.madianpeng.pojo.PageBean;
import top.madianpeng.pojo.ReturnValue;
import top.madianpeng.service.SubareaService;
import top.madianpeng.utils.IDUtils;
import top.madianpeng.utils.NonUtil;

@Service
@Transactional
public class SubareaServiceImpl implements SubareaService {
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private BcSubareaMapper mapper;

	@Override
	public PageBean<BcSubarea> querySubarea(BcSubarea subarea) {
		if (NonUtil.isNotNon(subarea.getProvince())) {
			subarea.setProvince("%" + subarea.getProvince() + "%");
		}
		if (NonUtil.isNotNon(subarea.getCity())) {
			subarea.setCity("%" + subarea.getCity() + "%");
		}
		if (NonUtil.isNotNon(subarea.getDistrict())) {
			subarea.setDistrict("%" + subarea.getDistrict() + "%");
		}
		if (NonUtil.isNotNon(subarea.getAddresskey())) {
			subarea.setAddresskey("%" + subarea.getAddresskey() + "%");
		}
		if (NonUtil.isNotNon(subarea.getPosition())) {
			subarea.setPosition("%" + subarea.getPosition() + "%");
		}
		PageBean<BcSubarea> pageBean = new PageBean<BcSubarea>();
		List<BcSubarea> list = new ArrayList<BcSubarea>();
		List<BcSubarea> countlist = new ArrayList<BcSubarea>();
		try {
			countlist = mapper.querySubarea(subarea);
			PageHelper.startPage(subarea.getPage(), subarea.getLimit());
			list = mapper.querySubarea(subarea);
		} catch (Exception e) {
			pageBean.isFail("查询");
			return pageBean;
		}
		pageBean.setData(list);
		pageBean.setCount(countlist.size());
		pageBean.isSuccess("查询");
		return pageBean;
	}

	@Override
	public ReturnValue addSubarea(BcSubarea subarea) {
		ReturnValue returnValue = new ReturnValue();
		subarea.setId(IDUtils.getId());
		subarea.setDecidedzoneId("0");
		try {
			mapper.insert(subarea);
		} catch (Exception e) {
			logger.error("添加失败：" + e.toString());
			returnValue.isFail("添加");
			return returnValue;
		}
		returnValue.isSuccess("添加");
		return returnValue;
	}

	@Override
	public BcSubarea findById(String id) {
		BcSubarea subarea = new BcSubarea();
		try {
			subarea = mapper.selectByPrimaryKey(id);
		} catch (Exception e) {
			logger.error("查询异常：" + e.toString());
		}

		return subarea;
	}

	@Override
	public ReturnValue update(BcSubarea subarea) {
		ReturnValue returnValue = new ReturnValue();
		try {
			mapper.updateByPrimaryKey(subarea);
		} catch (Exception e) {
			logger.error("修改失败：" + e.toString());
			returnValue.isFail("修改");
			return returnValue;
		}
		returnValue.isSuccess("修改");
		return returnValue;
	}

	@Override
	public ReturnValue delSubarea(List<String> id) {
		ReturnValue returnValue = new ReturnValue();
		try {
			mapper.delSubarea(id);
		} catch (Exception e) {
			logger.error("删除失败：" + e.toString());
			returnValue.isFail("删除");
			return returnValue;
		}
		returnValue.isSuccess("删除");
		return returnValue;
	}

	@Override
	public List<Area> queryCity() {
		List<Area> queryCity = mapper.queryCity();
		return queryCity;
	}

	@Override
	public List<BcSubarea> queryForDecide() {
		BcSubarea subarea = new BcSubarea();
		subarea.setDecidedzoneId("0");
		List<BcSubarea> querySubarea = mapper.querySubarea(subarea);
		for (BcSubarea bcSubarea : querySubarea) {
			bcSubarea.setAddresskey(bcSubarea.getProvince() + " " + bcSubarea.getCity() + " " + bcSubarea.getDistrict()
					+ " " + bcSubarea.getAddresskey());
		}
		return querySubarea;
	}

}
