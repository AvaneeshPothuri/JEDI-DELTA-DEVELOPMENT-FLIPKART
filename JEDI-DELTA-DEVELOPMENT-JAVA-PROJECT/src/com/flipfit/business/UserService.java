package com.flipfit.business;

public interface UserService {
	void register(FlipFitUser user);
	boolean login(String email, String password);
}
