package com.xpanxion.skeleton.dao;

import java.util.List;

import com.xpanxion.skeleton.dto.entity.UserEntity;


public interface UserDao {
	
	public List<UserEntity> getAllUsers();
	
	public UserEntity getUser(long userId);
	
	public UserEntity addUser(UserEntity user);
	
	public UserEntity updateUser(long userId, UserEntity user);
	
	public UserEntity deleteUser(long userId);

}
