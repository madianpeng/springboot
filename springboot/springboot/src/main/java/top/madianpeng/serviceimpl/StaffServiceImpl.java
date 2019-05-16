package top.madianpeng.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.madianpeng.mapper.BcStaffMapper;
import top.madianpeng.pojo.BcStaff;
import top.madianpeng.pojo.PageBean;
import top.madianpeng.service.StaffService;
@Service
@Transactional
public class StaffServiceImpl implements StaffService {
	@Autowired
	private BcStaffMapper mapper;
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Override
	public PageBean<BcStaff> queryStaff(BcStaff staff) {
		staff.setName("%"+staff.getName()+"%");
		List<BcStaff> list = new ArrayList<BcStaff>();
		PageBean<BcStaff> bean = new PageBean<BcStaff>();
		try {
			list= mapper.queryStaff(staff);
		} catch (Exception e) {
			logger.error("取派员查询异常："+e.toString());
			bean.setCode(1);
			bean.setMsg("取派员查询异常");
			return bean;
		}
		
		bean.setCount(list.size());
		bean.setData(list);
		bean.setMsg("查询成功");
		return bean;
	}

}
