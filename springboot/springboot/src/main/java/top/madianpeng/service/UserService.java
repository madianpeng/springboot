package top.madianpeng.service;

import top.madianpeng.pojo.TbUser;

public interface UserService {
	/**
	 * 根据用户查询用户
	 * @param tbUser
	 * @return
	 */
	public TbUser findUserByName(TbUser tbUser);
}
