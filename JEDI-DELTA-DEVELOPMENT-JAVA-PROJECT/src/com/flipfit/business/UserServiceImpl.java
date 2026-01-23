package com.flipfit.business;

public interface UserServiceImpl implements UserService{
	public void register(FlipFit user) {};
	public boolean login(String email, String password) {
		return true;
	}
}
