package com.helloworld.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name="profile_pic_helloworld")
public class ProfilePicture implements Serializable{

  @Id
  private String username;
  @Lob
  private byte[] image;
  
  public ProfilePicture() {
	// TODO Auto-generated constructor stub
}

public String getUsername() {
	return username;
}

public void setUsername(String username) {
	this.username = username;
}

public byte[] getImage() {
	return image;
}

public void setImage(byte[] image) {
	this.image = image;
}
  
}
