package com.rezso.backend.repository;

import com.rezso.backend.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Integer> {
    Job findJobByJobTitle(String jobTitle);
}
