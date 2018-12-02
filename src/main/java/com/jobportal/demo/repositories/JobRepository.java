package com.jobportal.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.jobportal.demo.model.Job;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

@Repository
@CrossOrigin(origins = "http://localhost:4200")
public interface JobRepository extends JpaRepository<Job, Long>  {

    @Query("SELECT j FROM Job j WHERE title LIKE %?1% OR content LIKE %?1% OR tasks LIKE %?1% OR profile LIKE %?1% OR location LIKE %?1%")
    List<Job> findJobs(String key);

}
