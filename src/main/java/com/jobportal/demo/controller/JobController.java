package com.jobportal.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.jobportal.demo.exception.ResourceNotFoundException;
import com.jobportal.demo.model.Job;
import com.jobportal.demo.repositories.JobRepository;



@RestController
@RequestMapping("/api/v1")
public class JobController {
	

	  @Autowired
	  JobRepository  jobRepository;
	  
	  
	  /** Returns All job posts in the database 
	   * @return Job
	   */
	  @GetMapping("/jobs")
	  public List<Job> getAllJobs() {
	      return jobRepository.findAll();
	  }
	  
	  
	  /** Create new job
	   * @param Job object
	   * @return Job
	   */
	  @PostMapping("/jobs")
	  public Job createJob(@Valid @RequestBody Job job) {
	      return jobRepository.save(job);
	  }
	  
	  
	  /** Returns job post by id
	   * @param id of job post
	   * @return Job
	   */
	  @GetMapping("/jobs/{id}")
	  public Job getJobById(@PathVariable(value = "id") Long jobId) {
		  return jobRepository.findById(jobId).orElseThrow(()-> new ResourceNotFoundException("Job", "id", jobId));
	  }
	  
	  
	  /** Updated job post in the database 
	   * @param id of job post
	   * @param Job object
	   * @return Job
	   */
	  @PutMapping("/jobs/{id}")
	  public Job updateJob(@PathVariable(value = "id") Long jobId, @Valid @RequestBody Job jobDetails) {

	      Job job = jobRepository.findById(jobId)
	              .orElseThrow(() -> new ResourceNotFoundException("Job", "id", jobId));

	      job.setBeginning(jobDetails.getBeginning());
	      job.setCompanyName(jobDetails.getCompanyName());
	      job.setContent(jobDetails.getContent());
	      job.setLocation(jobDetails.getLocation());
	      job.setOffer(jobDetails.getOffer());
	      job.setTitle(jobDetails.getTitle());
	      job.setProfile(jobDetails.getProfile());
	      job.setTasks(jobDetails.getTasks());

	      Job updatedJob = jobRepository.save(job);
	      return updatedJob;
	  }
	  
	  
	  /** Delete job post in the database 
	   * @param id of job post
	   * @return Job
	   */
	  @DeleteMapping("/jobs/{id}")
	  public ResponseEntity<?> deleteNote(@PathVariable(value = "id") Long jobId) {
		  
	      Job job = jobRepository.findById(jobId)
	              .orElseThrow(() -> new ResourceNotFoundException("Job", "id", jobId));

	      jobRepository.delete(job);

	      return ResponseEntity.ok().build();
	  }
	  
	  
	 
	  
}
