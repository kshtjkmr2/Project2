package com.helloworld.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="job_helloworld")
public class Job implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
private int id;
private String jobTitle;
private String jobDescription;
private String skillRequired;
private String yearOfExp;
private String salary;
private String companyName;
private String location;
private Date postedOn;
	
public Job() {
	// TODO Auto-generated constructor stub
}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getJobTitle() {
	return jobTitle;
}

public void setJobTitle(String jobTitle) {
	this.jobTitle = jobTitle;
}

public String getJobDescription() {
	return jobDescription;
}

public void setJobDescription(String jobDescription) {
	this.jobDescription = jobDescription;
}

public String getSkillRequired() {
	return skillRequired;
}

public void setSkillRequired(String skillRequired) {
	this.skillRequired = skillRequired;
}

public String getYearOfExp() {
	return yearOfExp;
}

public void setYearOfExp(String yearOfExp) {
	this.yearOfExp = yearOfExp;
}

public String getSalary() {
	return salary;
}

public void setSalary(String salary) {
	this.salary = salary;
}

public String getCompanyName() {
	return companyName;
}

public void setCompanyName(String companyName) {
	this.companyName = companyName;
}

public String getLocation() {
	return location;
}

public void setLocation(String location) {
	this.location = location;
}

public Date getPostedOn() {
	return postedOn;
}

public void setPostedOn(Date postedOn) {
	this.postedOn = postedOn;
}

}
