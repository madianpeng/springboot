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

import top.madianpeng.mapper.BcDecidedzoneMapper;
import top.madianpeng.pojo.BcDecidedzone;
import top.madianpeng.pojo.PageBean;
import top.madianpeng.service.DecideZoneService;
import top.madianpeng.utils.NonUtil;
@Service
@Transactional
public class DecideZoneServiceImpl implements DecideZoneService {
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private BcDecidedzoneMapper mapper;	
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

}
