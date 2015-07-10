package com.inlog.services;

import com.inlog.entities.User;

public interface IUserService {

	public abstract void saveUserDetails(User user);
	public abstract boolean validateAuthToken(String username , String authToken);

}