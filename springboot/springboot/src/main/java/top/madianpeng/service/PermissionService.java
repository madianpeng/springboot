package top.madianpeng.service;

import java.util.List;

import top.madianpeng.pojo.UPermission;
import top.madianpeng.pojo.URole;

public interface PermissionService {
	
	/**
	 * 查询权限
	 * @param rlist
	 * @return
	 */
	List<UPermission> findPermission(List<URole> rlist);

}
