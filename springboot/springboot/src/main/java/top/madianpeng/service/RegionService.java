package top.madianpeng.service;

import java.util.List;

import top.madianpeng.pojo.BcRegion;
import top.madianpeng.pojo.PageBean;
import top.madianpeng.pojo.ReturnValue;

public interface RegionService {

	/**
	 * 分区列表查询
	 * @param region
	 * @return
	 */
	PageBean<BcRegion> queryRegion(BcRegion region);
	
	/**
	 * 批量导入
	 * @param regionlist
	 * @return
	 */
	ReturnValue importAdd(List<BcRegion> regionlist);
	/**
	 *   添加分区
	 * @param region
	 * @return
	 */
	ReturnValue addRegion(BcRegion region);

}
