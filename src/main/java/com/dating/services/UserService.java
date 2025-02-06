package com.dating.services;

import java.util.List;

import com.dating.entities.ProcessingUser;
import com.dating.entities.User;

public interface UserService {
	
	public User saveUser(User user);
	
	List<User> filterUserAccordingToPreference(ProcessingUser user);
	
	

}
