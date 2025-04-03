package com.berke.controller;

import com.berke.dto.AuthRequest;
import com.berke.dto.AuthResponse;
import com.berke.dto.DtoUser;
import com.berke.dto.RefreshTokenRequest;

public interface IRestAuthenticationController {
	public RootEntity<DtoUser> register(AuthRequest input);
	public RootEntity<AuthResponse> authenticate(AuthRequest input);
	public RootEntity<AuthResponse> refreshToken(RefreshTokenRequest input);
}
