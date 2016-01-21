package org.andy.shop.service.impl;

import java.util.List;

import org.andy.shop.dao.UserInfoMapper;
import org.andy.shop.model.UserInfo;
import org.andy.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 创建时间：2015-1-27 下午5:22:59
 * 
 * @author andy
 * @version 2.2
 */
@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserInfoMapper userInfoMapper;

	public UserInfo getUserById(int id) {
		return userInfoMapper.selectByPrimaryKey(id);
	}

	public List<UserInfo> getUsers() {
		return userInfoMapper.selectAll();
	}

	public int insert(UserInfo userInfo) {
		
		int result = userInfoMapper.insert(userInfo);
		return result;
	}

}
