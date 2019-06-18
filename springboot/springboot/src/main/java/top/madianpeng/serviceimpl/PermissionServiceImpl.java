package top.madianpeng.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import top.madianpeng.mapper.UPermissionMapper;
import top.madianpeng.mapper.URoleMapper;
import top.madianpeng.pojo.UPermission;
import top.madianpeng.pojo.URole;
import top.madianpeng.service.PermissionService;
import top.madianpeng.service.RoleService;

@Service
@Transactional
public class PermissionServiceImpl implements PermissionService {
	@Autowired
	private UPermissionMapper permissionMapper;

	@Override
	public List<UPermission> findPermission(List<URole> rlist) {
		List<UPermission> permissList=permissionMapper.findPermission(rlist);
		return permissList;
	}


}
