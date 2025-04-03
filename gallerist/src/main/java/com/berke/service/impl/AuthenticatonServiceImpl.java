package com.berke.service.impl;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.berke.dto.AuthRequest;
import com.berke.dto.AuthResponse;
import com.berke.dto.DtoUser;
import com.berke.dto.RefreshTokenRequest;
import com.berke.entity.RefreshToken;
import com.berke.entity.User;
import com.berke.exception.BaseException;
import com.berke.exception.ErrorMessage;
import com.berke.exception.MessageType;
import com.berke.jwt.JWTService;
import com.berke.repository.RefreshTokenRepository;
import com.berke.repository.UserRepository;
import com.berke.service.IAuthenticationService;
@Service
public class AuthenticatonServiceImpl implements IAuthenticationService{
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private AuthenticationProvider authenticationProvider;
	@Autowired
	private JWTService jwtService;
	@Autowired
	private RefreshTokenRepository refreshTokenRepository;
	
	private RefreshToken createRefreshToken(User user) {
		RefreshToken refreshToken = new RefreshToken();
		refreshToken.setCreateTime(new Date());
		refreshToken.setExpiredDate(new Date(System.currentTimeMillis()+ 1000*60*60*4));
		refreshToken.setUser(user);
		refreshToken.setRefreshToken(UUID.randomUUID().toString());
		return refreshToken;
	}
	private User createUser(AuthRequest input) {
		User user = new User();
		user.setCreateTime(new Date());
		user.setUsername(input.getUsername());
		user.setPassword(passwordEncoder.encode(input.getPassword()));
		return user;
	}
	public boolean isRefreshTokenValid(Date expireDate) {
		return new Date().before(expireDate);
	}
	
	@Override
	public DtoUser register(AuthRequest input) {
		User savedUser = userRepository.save(createUser(input));
		DtoUser dtoUser = new DtoUser();
		BeanUtils.copyProperties(savedUser, dtoUser);
		return dtoUser;
	}
	
	@Override
	public AuthResponse authenticate(AuthRequest input) {
		try {
			UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(input.getUsername(), input.getPassword());
			authenticationProvider.authenticate(authenticationToken);
			Optional<User> optional = userRepository.findByUsername(input.getUsername());
			String accessToken = jwtService.generateToken(optional.get());
			RefreshToken savedRefreshToken = refreshTokenRepository.save(createRefreshToken(optional.get()));
			return new AuthResponse(accessToken, savedRefreshToken.getRefreshToken());
		} catch (Exception e) {
			throw new BaseException(new ErrorMessage(MessageType.USERNAME_OR_PASSWORD_INVALID, e.getMessage()));
		}
	}
	
	@Override
	public AuthResponse refreshToken(RefreshTokenRequest input) {
		Optional<RefreshToken> optRefreshToken = refreshTokenRepository.findByRefreshToken(input.getRefreshToken());
		if(optRefreshToken.isEmpty()) {
			throw new BaseException(new ErrorMessage(MessageType.REFRESH_TOKEN_NOT_FOUND, input.getRefreshToken()));
		}
		if(!isRefreshTokenValid(optRefreshToken.get().getExpiredDate())) {
			throw new BaseException(new ErrorMessage(MessageType.REFRESH_TOKEN_EXPIRED, input.getRefreshToken()));
		}
		User user = optRefreshToken.get().getUser();
		String accessToken = jwtService.generateToken(user);
		RefreshToken savedRefreshToken = refreshTokenRepository.save(createRefreshToken(user));
		return new AuthResponse(accessToken, savedRefreshToken.getRefreshToken());
	}

}
