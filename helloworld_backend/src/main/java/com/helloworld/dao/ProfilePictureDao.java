package com.helloworld.dao;

import com.helloworld.model.ProfilePicture;

public interface ProfilePictureDao {

	void saveProfilePicture(ProfilePicture profilePicture);
	ProfilePicture getProfilePicture(String username);
}
