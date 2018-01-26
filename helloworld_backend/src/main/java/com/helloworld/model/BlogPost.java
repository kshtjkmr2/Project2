package com.helloworld.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;
@Entity
@Table(name="blogpost_helloworld")
public class BlogPost implements Serializable{
@Id
@GeneratedValue(strategy=GenerationType.AUTO)
private int id;
@NotEmpty
private String blogTitle;

private String description;
@ManyToOne
private User postedBy;
private Date postedOn;
private boolean approved;
public BlogPost() {
	// TODO Auto-generated constructor stub
}

public boolean isApproved() {
	return approved;
}

public void setApproved(boolean approved) {
	this.approved = approved;
}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getBlogTitle() {
	return blogTitle;
}

public void setBlogTitle(String blogTitle) {
	this.blogTitle = blogTitle;
}

public String getDescription() {
	return description;
}

public void setDescription(String description) {
	this.description = description;
}

public User getPostedBy() {
	return postedBy;
}

public void setPostedBy(User postedBy) {
	this.postedBy = postedBy;
}

public Date getPostedOn() {
	return postedOn;
}

public void setPostedOn(Date postedOn) {
	this.postedOn = postedOn;
}

}
