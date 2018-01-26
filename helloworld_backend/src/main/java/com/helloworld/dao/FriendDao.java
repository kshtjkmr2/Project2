package com.helloworld.dao;

import java.util.List;

import com.helloworld.model.Friend;
import com.helloworld.model.User;

public interface FriendDao {

	List<User> getlistOfSuggestedUser(String username);

	void sendFriedRequest(String username, String toId);
	
	List<Friend> listOfPeendingRequest(String loogedInUser);
	
	void updateRequest(Friend pendingRequest);
	
	List<Friend> getFriendsList(String username);

}
