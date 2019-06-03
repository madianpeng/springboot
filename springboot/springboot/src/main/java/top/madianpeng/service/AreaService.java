package top.madianpeng.service;

import java.util.List;

import top.madianpeng.pojo.BcArea;

/**
 * 地区查询
 * @author Can
 *
 */
public interface AreaService {
	
	List<BcArea> queryAear(BcArea area);
}
