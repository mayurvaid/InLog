package com.inlog.services;

import com.inlog.entities.InlogException;
import com.inlog.entities.User;

/**
 * 
 * @author Mayur
 *
 */
public interface IUserService {
	/**
	 * Saves the user details into db and uses jjwt mechanism to get the token
	 * which is associated to each user and every call to any api need to have 
	 * that auth token along with the username which is used to authenticate the
	 * auth token
	 * @param user
	 * @throws InlogException
	 */
	public abstract void saveUserDetails(User user) throws InlogException;
	
	/**
	 * This method validates the auth token using jjwt which uses HMAC256 algorithm
	 * to convert the given input to 256 bytes token
	 * @param username
	 * @param authToken
	 * @return
	 */
	public abstract boolean validateAuthToken(String username , String authToken);
	
	/**
	 * This method gets the userdetails based on the username from db
	 * @param username
	 * @return
	 */
	public User getUserDetails(String username);
	
	public abstract void updateUserDetails(User user) throws InlogException;

}