package top.madianpeng.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.transaction.TransactionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import top.madianpeng.mapper.BcDecidedzoneMapper;
import top.madianpeng.mapper.BcSubareaMapper;
import top.madianpeng.pojo.BcDecidedzone;
import top.madianpeng.pojo.BcSubarea;
import top.madianpeng.pojo.PageBean;
import top.madianpeng.pojo.ReturnValue;
import top.madianpeng.service.DecideZoneService;
import top.madianpeng.utils.IDUtils;
import top.madianpeng.utils.NonUtil;
@Service
@Transactional
public class DecideZoneServiceImpl implements DecideZoneService {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private BcDecidedzoneMapper mapper;	
	@Autowired
	private BcSubareaMapper subareaMapper;	
	@Override
	public PageBean<BcDecidedzone> queryDecidedzone(BcDecidedzone decidedzone) {
		if (NonUtil.isNotNon(decidedzone.getName())) {
			decidedzone.setName("%"+decidedzone.getName()+"%");
		}
		if (NonUtil.isNotNon(decidedzone.getStaffname())) {
			decidedzone.setStaffname("%"+decidedzone.getStaffname()+"%");
		}
		if (NonUtil.isNotNon(decidedzone.getStation())) {
			decidedzone.setStation("%"+decidedzone.getStation()+"%");
		}
		PageBean<BcDecidedzone> pageBean = new PageBean<BcDecidedzone>();
		List<BcDecidedzone> list = new ArrayList<BcDecidedzone>();
		int count = 0;
		try {
			PageHelper.startPage(decidedzone.getPage(),decidedzone.getLimit());
			list = mapper.queryDecidedzone(decidedzone);
			PageInfo<BcDecidedzone> info = new PageInfo<BcDecidedzone>(list);
			count = (int) info.getTotal();
		} catch (Exception e) {
			logger.error("查询异常："+e.toString());
		}
		pageBean.setCount(count);
		pageBean.setData(list);
		return pageBean;
	}
	@Override
	public ReturnValue addDecidedzone(BcDecidedzone decidedzone) {
		ReturnValue returnValue = new ReturnValue();
		String id = IDUtils.getId();
		decidedzone.setId(id);
		String[] split = decidedzone.getSubareaid().split(",");
		List<BcSubarea>  list = new ArrayList<BcSubarea>();
		for (String subareaid : split) {
			BcSubarea bcSubarea = new BcSubarea();
			bcSubarea.setId(subareaid);
			bcSubarea.setDecidedzoneId(id);
			list.add(bcSubarea);
		}
		try {
			mapper.insert(decidedzone);
			subareaMapper.bindDecidezone(list);
		} catch (Exception e) {
			logger.error("新增异常："+e.getMessage());
			throw new RuntimeException(e.getMessage());		
		}
		returnValue.isSuccess("新增");
		return returnValue;
	}
	@Override
	public BcDecidedzone findById(String id) {
		BcDecidedzone decidedzone = mapper.selectByPrimaryKey(id);
		return decidedzone;
	}
	@Override
	public String[] findSubarea(String id) {
		BcSubarea subarea = new BcSubarea();
		subarea.setDecidedzoneId(id);
		List<BcSubarea> list = subareaMapper.querySubarea(subarea);
		String[] ids = new String[list.size()];
		for (int i = 0; i < list.size(); i++) {
			ids[i]=list.get(i).getId();
		}
		return ids;
	}
	@Override
	public ReturnValue modifyDecidezone(BcDecidedzone decidedzone) {
		ReturnValue returnValue = new ReturnValue();
		String[] split = decidedzone.getSubareaid().split(",");
		String id = decidedzone.getId();
		String[] ids = new String[1];
		ids[0] =id;
		List<BcSubarea>  list = new ArrayList<BcSubarea>();
		for (String subareaid : split) {
			BcSubarea bcSubarea = new BcSubarea();
			bcSubarea.setId(subareaid);
			bcSubarea.setDecidedzoneId(id);
			list.add(bcSubarea);
		}
		try {
			mapper.updateByPrimaryKey(decidedzone);
			subareaMapper.deleteDecidezone(ids);
			subareaMapper.bindDecidezone(list);
		} catch (Exception e) {
			logger.error("修改异常: "+e.getMessage());
			throw new TransactionException(e);
		}
		returnValue.isSuccess("修改");
		return returnValue;
	}
	@Override
	public ReturnValue delDecidezone(String[] ids) {
		ReturnValue returnValue = new ReturnValue();
		try {
			subareaMapper.deleteDecidezone(ids);
			mapper.delDecidezone(ids);
		} catch (Exception e) {
			logger.error("删除异常: "+e.getMessage());
			throw new TransactionException(e);
		}
		returnValue.isSuccess("删除");
		return returnValue;
	}

}
