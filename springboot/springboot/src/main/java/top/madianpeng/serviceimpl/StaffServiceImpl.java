package top.madianpeng.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import top.madianpeng.mapper.BcStaffMapper;
import top.madianpeng.pojo.BcStaff;
import top.madianpeng.pojo.PageBean;
import top.madianpeng.pojo.ReturnValue;
import top.madianpeng.service.StaffService;
import top.madianpeng.utils.IDUtils;
import top.madianpeng.utils.NonUtil;
@Service
@Transactional
public class StaffServiceImpl implements StaffService {
	@Autowired
	private BcStaffMapper mapper;
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Override
	public PageBean<BcStaff> queryStaff(BcStaff staff) {
		
		if (NonUtil.isNotNon(staff.getName())) {
			staff.setName("%"+staff.getName()+"%");
		}
		
		List<BcStaff> list = new ArrayList<BcStaff>();
		PageBean<BcStaff> bean = new PageBean<BcStaff>();
		int count = 0;
		try {
			PageHelper.startPage(staff.getPage(), staff.getLimit());
			list= mapper.queryStaff(staff);
			count = mapper.queryCount();
		} catch (Exception e) {
			logger.error("取派员查询异常："+e.toString());
			bean.setCode(1);
			bean.setMsg("取派员查询异常");
			return bean;
		}
		
		bean.setCount(count);
		bean.setData(list);
		bean.setMsg("查询成功");
		return bean;
	}

	@Override
	public ReturnValue addStaff(BcStaff staff) {
		ReturnValue returnValue = new ReturnValue();
		String id = IDUtils.getId();
		staff.setId(id);
		try {
			mapper.insert(staff);
		} catch (Exception e) {
			logger.error("添加取派员异常"+e.toString());
			returnValue.isFail("添加");
			return returnValue;
		}
		returnValue.isSuccess("添加");
		return returnValue;
	}

	@Override
	public BcStaff queryByID(BcStaff staff) {
		BcStaff bcStaff = mapper.selectByPrimaryKey(staff.getId());
		return bcStaff;
	}

	@Override
	public ReturnValue modifyStaff(BcStaff bcStaff) {
		ReturnValue returnValue = new ReturnValue();
		try {
			mapper.updateByPrimaryKey(bcStaff);
		} catch (Exception e) {
			logger.error("修改取派员异常"+e.toString());
			returnValue.isFail("修改");
			return returnValue;
		}
		returnValue.isSuccess("修改");
		return returnValue;
	}

	@Override
	public ReturnValue delStaff(List<String> idsList) {
		ReturnValue returnValue = new ReturnValue();
		try {
			int i = mapper.delStaff(idsList);
			logger.info("删除"+i+"条");
		} catch (Exception e) {
			logger.error("删除取派员异常: "+e.toString());
			returnValue.isFail("删除");
			return returnValue;
		}
		returnValue.isSuccess("删除");
		return returnValue;
	}

}
