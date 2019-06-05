package top.madianpeng.service;

import top.madianpeng.pojo.BcDecidedzone;
import top.madianpeng.pojo.PageBean;

public interface DecideZoneService {
	/**
	 * 分页查询
	 * @param decidedzone
	 * @return
	 */
	PageBean<BcDecidedzone> queryDecidedzone(BcDecidedzone decidedzone);

}
