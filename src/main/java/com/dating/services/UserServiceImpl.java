package com.dating.services;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dating.entities.ProcessingUser;
import com.dating.entities.User;
import com.dating.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	
	
	@Autowired
	private UserRepository userRepository;
	
	

	@Override
	public User saveUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public List<User> filterUserAccordingToPreference(ProcessingUser user) {
	    // Fetch all users from the repository
	    List<User> users = userRepository.findAll();

	    // Filter out users who have the same gender
	    List<User> filteredUsers = users.stream()
	            .filter(u -> !u.getGender().equals(user.getGender()))
	            .collect(Collectors.toList());

	    // Sort the users based on the matching rules: Gender, Age, Interests
	    filteredUsers.sort((user1, user2) -> {
	        // Gender Rule: Preference for opposite gender
	        if (!user1.getGender().equals(user.getGender()) && user2.getGender().equals(user.getGender())) {
	            return -1;
	        } else if (user1.getGender().equals(user.getGender()) && !user2.getGender().equals(user.getGender())) {
	            return 1;
	        }

	        // Age Rule: Preference for closest age
	        int ageDifference1 = Math.abs(user1.getAge() - user.getAge());
	        int ageDifference2 = Math.abs(user2.getAge() - user.getAge());
	        if (ageDifference1 != ageDifference2) {
	            return Integer.compare(ageDifference1, ageDifference2);
	        }

	        // Interest Rule: Preference for matching interests
	        // Ensure interests are not null and split the interests string into a list
	        long matchCount1 = user1.getInterests() != null
	            ? Arrays.stream(user1.getInterests().split(",")).filter(interest -> Arrays.asList(user.getInterests().split(",")).contains(interest)).count()
	            : 0;

	        long matchCount2 = user2.getInterests() != null
	            ? Arrays.stream(user2.getInterests().split(",")).filter(interest -> Arrays.asList(user.getInterests().split(",")).contains(interest)).count()
	            : 0;

	        // Descending order of match count
	        return Long.compare(matchCount2, matchCount1);
	    });

	    // If a specific number of results is requested, limit the returned list

	    int numberOfResults=users.size();
		if(user.getFilter_top_number().indexOf("all")==-1) {
			String sub=user.getFilter_top_number().substring(7);
			numberOfResults=Integer.parseInt(sub);
		}
		
	    
	    if (numberOfResults > 0 && filteredUsers.size() > numberOfResults) {
	        return filteredUsers.subList(0, numberOfResults);
	    }

	    return filteredUsers;  // Return all if no specific number is provided
	}




}
