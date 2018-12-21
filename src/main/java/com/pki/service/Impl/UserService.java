package com.pki.service.Impl;


import com.pki.dao.UserDao;
import com.pki.entity.User;
import com.pki.service.IUserService;
import com.pki.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller("userService")
public class UserService implements IUserService {
	@Autowired
	private UserDao userDao;

	@Override
	public void register(User user) {
		userDao.Save(user);
	}

	@Override
	public User login(String name, String psd) {
		String hql="from User u where u.UName=:name and u.UPsd=:psd";
		Map<String, Object>map=new HashMap<String, Object>();
		map.put("name", name);
		map.put("psd", psd);
		return userDao.Get(hql,map);
	}

	@Override
	public User getUserById(int uId) {
		String hql="from User u where u.UId="+uId;
		return userDao.Get(hql);
	}


	@Override
	public List<User> getUserByList(Page<User> page) {
		String hql="from User";
		List<User>list=userDao.find(hql, page.getCurrentPage(), page.getCurrentPage());

		return list;
	}

	@Override
	public Long getUserByListLong() {
		String hql="count(*) from User";
		return userDao.count(hql);
	}

}
