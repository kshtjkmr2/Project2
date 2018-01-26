package com.helloworld.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name="timeline_helloworld")
public class TimelinePost implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;		
	private String description;
	@Transient
	private MultipartFile image;
	private Date PostedOn;
}
