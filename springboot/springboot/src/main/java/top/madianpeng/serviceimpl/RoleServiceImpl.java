package top.madianpeng.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import top.madianpeng.mapper.URoleMapper;
import top.madianpeng.pojo.URole;
import top.madianpeng.service.RoleService;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {
	@Autowired
	private URoleMapper roleMapper;

	@Override
	public List<URole> findRoleByUid(String uid) {
		
		List<URole>  roleList = roleMapper.findRoleByUid(uid);
		
		return roleList;
	}
}
