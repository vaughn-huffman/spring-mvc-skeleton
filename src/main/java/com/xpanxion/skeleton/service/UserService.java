package com.xpanxion.skeleton.service;

import java.util.List;

import com.xpanxion.skeleton.dao.UserDao;
import com.xpanxion.skeleton.dto.beans.UserBean;



public interface UserService {
	
	public List<UserBean> getUsers();
	
	public UserBean getUser(long userId);
	
	public UserBean addUser(UserBean user);
	
	public UserBean updateUser(long userId, UserBean user);
	
	public UserBean updateUser(long userId, String userPassword);
	
	public UserBean deleteUser(long userId);
	
	 public void setUserDao(UserDao dao);
	 
}
