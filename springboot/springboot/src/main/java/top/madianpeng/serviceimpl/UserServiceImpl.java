package top.madianpeng.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.madianpeng.mapper.TbUserMapper;
import top.madianpeng.pojo.TbUser;
import top.madianpeng.pojo.TbUserExample;
import top.madianpeng.service.UserService;
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private TbUserMapper tbUserMapper;

	@Override
	public TbUser findUserByName(TbUser tbUser) {
		TbUserExample example = new TbUserExample();
		example.createCriteria().andUsernameEqualTo(tbUser.getUsername());
		List<TbUser> list = tbUserMapper.selectByExample(example);
		return list.get(0);
	}

}
