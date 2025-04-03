package com.berke.service;

import com.berke.dto.AuthRequest;
import com.berke.dto.AuthResponse;
import com.berke.dto.DtoUser;
import com.berke.dto.RefreshTokenRequest;

public interface IAuthenticationService {
	public DtoUser register(AuthRequest input);
	public AuthResponse authenticate(AuthRequest input);
	public AuthResponse refreshToken(RefreshTokenRequest input);
}
