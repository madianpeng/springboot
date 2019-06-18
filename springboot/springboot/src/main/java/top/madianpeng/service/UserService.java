package top.madianpeng.service;

import top.madianpeng.pojo.TbUser;
import top.madianpeng.pojo.UUser;

public interface UserService {
	/**
	 * 根据用户查询用户
	 * @param tbUser
	 * @return
	 */
	public TbUser findUserByCode(TbUser tbUser);
	
	/**
	 * 修改用户信息
	 * @param tbUser
	 * @return
	 */
	public boolean modifyInfo(TbUser tbUser);
	
	public UUser selectAllByName(String name);
}
