package com.jobportal.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.jobportal.demo.exception.ResourceNotFoundException;
import com.jobportal.demo.model.Job;
import com.jobportal.demo.repositories.JobRepository;



@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:4200")
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
	   * @param job object
	   * @return Job
	   */
	  @PostMapping("/jobs")
	  public Job createJob(@Valid @RequestBody Job job) {
	      return jobRepository.save(job);

	  }
	  
	  
	  /** Returns job post by id
	   * @param jobId of job post
	   * @return Job
	   */
	  @GetMapping("/jobs/{id}")
	  public Job getJobById(@PathVariable(value = "id") Long jobId) {

	  	return jobRepository.findById(jobId).orElseThrow(()-> new ResourceNotFoundException("Job", "id", jobId));

	  }
	  
	  
	  /** Updated job post in the database 
	   * @param jobId of job post
	   * @param jobDetails object
	   * @return Job
	   */
	  @PutMapping("/jobs/{id}")
	  public Job updateJob(@PathVariable(value = "id") String jobId, @Valid @RequestBody Job jobDetails) {

	      Job job = jobRepository.findById(Long.parseLong(jobId))
	              .orElseThrow(() -> new ResourceNotFoundException("Job", "id", jobId));

	      Job updatedJob = jobRepository.save(jobDetails);
	      return updatedJob;
	  }
	  
	  
	  /** Delete job post in the database 
	   * @param jobId of job post
	   * @return Job
	   */
	  @DeleteMapping("/jobs/{id}")
	  public ResponseEntity<?> deleteJob(@PathVariable(value = "id") Long jobId) {
		  
	      Job job = jobRepository.findById(jobId)
	              .orElseThrow(() -> new ResourceNotFoundException("Job", "id", jobId));

	      jobRepository.delete(job);

	      return ResponseEntity.ok().build();
	  }

	/** Delete Search post in the database
	 * @return Job
	 */
	@GetMapping("/jobs/search/{searchKey}")
	public List<Job> searchJob(@PathVariable(value = "searchKey") String searchKey) {
		return  jobRepository.findJobs(searchKey);
	}


	 
	  
}
