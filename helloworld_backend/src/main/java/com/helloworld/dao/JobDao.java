package com.helloworld.dao;

import java.util.List;
import com.helloworld.model.Job;

public interface JobDao {

	void saveJob(Job job);

	List<Job> getAllJobs();

	Job getJobById(int id);
	
	Job editJobById(int id);

}
