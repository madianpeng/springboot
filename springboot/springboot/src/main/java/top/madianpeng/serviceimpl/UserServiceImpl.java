package top.madianpeng.serviceimpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import top.madianpeng.mapper.TbUserMapper;
import top.madianpeng.pojo.TbUser;
import top.madianpeng.pojo.TbUserExample;
import top.madianpeng.service.UserService;
import top.madianpeng.utils.NonUtil;
@Service
@Transactional
public class UserServiceImpl implements UserService {
	@Autowired
	private TbUserMapper tbUserMapper;
	//获取logger
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public TbUser findUserByCode(TbUser tbUser) {
		TbUserExample example = new TbUserExample();
		example.createCriteria().andUsercodeEqualTo(tbUser.getUsercode());
		List<TbUser> list = tbUserMapper.selectByExample(example);
		if (NonUtil.isNon(list)) {
			return null;
		}
		return list.get(0);
	}

	@Override
	public boolean modifyInfo(TbUser tbUser) {
		int i = tbUserMapper.modifyInfo(tbUser);
		logger.info(String.valueOf(i));
		return true;
	}

}
