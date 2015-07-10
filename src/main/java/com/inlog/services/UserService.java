package com.inlog.services;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.inlog.dao.repositories.UserRepository;
import com.inlog.entities.User;

@Service
public class UserService implements IUserService {
	@Value("${sharedSecretKey}")
	private String SHARED_SECRET_KEY;

	private final UserRepository userRepository;

	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.inlog.services.IUserService#saveUserDetails(com.inlog.entities.User)
	 */
	@Override
	public void saveUserDetails(User user) {
		System.out.println(SHARED_SECRET_KEY);
		user.setAuthToken(getAuthToken(user.getUsername(), SHARED_SECRET_KEY));
		userRepository.save(user);
	}

	private String getAuthToken(String key, String SHARED_SECRET_KEY) {
		return Jwts.builder().setSubject(key)
				.signWith(SignatureAlgorithm.HS256, SHARED_SECRET_KEY)
				.compact();
	}

	@Override
	public boolean validateAuthToken(String username , String authToken) {
		try {
			return StringUtils.equals(
					Jwts.parser().setSigningKey(SHARED_SECRET_KEY)
							.parseClaimsJws(authToken).getBody()
							.getSubject(),username);

		} catch (SignatureException ex) {
			return false;
		}
	}

}
