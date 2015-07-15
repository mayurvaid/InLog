package com.inlog.services;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.inlog.dao.IUserDao;
import com.inlog.dao.repositories.UserRepository;
import com.inlog.entities.InlogException;
import com.inlog.entities.User;

@Service
public class UserService implements IUserService {
	@Value("${sharedSecretKey}")
	private String SHARED_SECRET_KEY;

	private final UserRepository userRepository;
	private final IUserDao userDao;

	@Autowired
	public UserService(UserRepository userRepository, IUserDao userDao) {
		this.userRepository = userRepository;
		this.userDao = userDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.inlog.services.IUserService#saveUserDetails(com.inlog.entities.User)
	 */
	@Override
	public void saveUserDetails(User user) throws InlogException {
		if (userDao.getUserCountByUserName(user.getUsername()) > 0) {
			throw new InlogException(
					"User already exits please select a different user");
		}
		user.setAuthToken(getAuthToken(user.getUsername(), SHARED_SECRET_KEY));
		userRepository.save(user);
	}

	private String getAuthToken(String key, String SHARED_SECRET_KEY) {
		return Jwts.builder().setSubject(key)
				.signWith(SignatureAlgorithm.HS256, SHARED_SECRET_KEY)
				.compact();
	}

	@Override
	public boolean validateAuthToken(String username, String authToken) {
		try {
			return StringUtils.equals(
					Jwts.parser().setSigningKey(SHARED_SECRET_KEY)
							.parseClaimsJws(authToken).getBody().getSubject(),
					username);

		} catch (SignatureException ex) {
			return false;
		}
	}

	@Override
	public User getUserDetails(String username) {
		return userRepository.getUserByUserName(username);
	}

}
