package com.jobportal.demo.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.jobportal.demo.model.Job;

public interface JobRepository extends JpaRepository<Job, Long> {

}
