package top.madianpeng.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import top.madianpeng.mapper.BcAreaMapper;
import top.madianpeng.pojo.BcArea;
import top.madianpeng.service.AreaService;
@Service
@Transactional
public class AreaServiceImpl implements AreaService {
	@Autowired
	private BcAreaMapper areaMapper;
	@Override
	public List<BcArea> queryAear(BcArea area) {
		List<BcArea> list = areaMapper.queryArea(area);
		return list;
	}

}
