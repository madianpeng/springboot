package top.madianpeng.service;

import java.util.List;

import top.madianpeng.pojo.URole;

public interface RoleService {
	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	public  List<URole> findRoleByUid(String uid);
}
