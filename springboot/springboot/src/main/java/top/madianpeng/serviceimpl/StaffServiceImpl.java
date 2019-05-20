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
import top.madianpeng.service.StaffService;
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

}
