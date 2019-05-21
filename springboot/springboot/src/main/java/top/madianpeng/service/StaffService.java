package top.madianpeng.service;

import java.util.List;

import top.madianpeng.pojo.BcStaff;
import top.madianpeng.pojo.PageBean;
import top.madianpeng.pojo.ReturnValue;

public interface StaffService {
	/**
	 * 分页查询取派员
	 * @param staff
	 * @return
	 */
	PageBean<BcStaff> queryStaff(BcStaff staff);
	
	/**
	 * 添加取派员
	 * @param staff
	 * @return
	 */
	ReturnValue addStaff(BcStaff staff);
	/**
	 * 根据id查询
	 * @param staff
	 * @return
	 */
	BcStaff queryByID(BcStaff staff);
	
	/**
	 * 修改取派员
	 * @param bcStaff
	 * @return
	 */
	ReturnValue modifyStaff(BcStaff bcStaff);
	
	/**
	 * 删除取派员
	 * @param idsList
	 * @return
	 */
	ReturnValue delStaff(List<String> idsList);
}
