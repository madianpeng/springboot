package top.madianpeng.service;

import top.madianpeng.pojo.BcStaff;
import top.madianpeng.pojo.PageBean;

public interface StaffService {
	/**
	 * 分页查询取派员
	 * @param staff
	 * @return
	 */
	PageBean<BcStaff> queryStaff(BcStaff staff);
}
