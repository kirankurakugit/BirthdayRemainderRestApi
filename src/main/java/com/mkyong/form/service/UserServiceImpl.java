package com.zensar.form.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zensar.form.dao.UserDao;
import com.zensar.form.model.User;

@Service("userService")
public class UserServiceImpl implements UserService {

	UserDao userDao;

	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}


	@Override
	public void saveOrUpdate(User user) {

		if (findById(user.getId())==null) {
			userDao.save(user);
		} else {
			userDao.update(user);
		}

	}

	
}
