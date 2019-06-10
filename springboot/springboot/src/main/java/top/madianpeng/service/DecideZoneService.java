package top.madianpeng.service;

import top.madianpeng.pojo.BcDecidedzone;
import top.madianpeng.pojo.PageBean;
import top.madianpeng.pojo.ReturnValue;

public interface DecideZoneService {
	/**
	 * 分页查询
	 * @param decidedzone
	 * @return
	 */
	PageBean<BcDecidedzone> queryDecidedzone(BcDecidedzone decidedzone);
	/**
	 * 新增定区
	 * @param decidedzone
	 * @return
	 */
	ReturnValue addDecidedzone(BcDecidedzone decidedzone);
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 */
	BcDecidedzone findById(String id);
	/**
	 * 查询关联分区id
	 * @param id
	 * @return
	 */
	String[] findSubarea(String id);

}
