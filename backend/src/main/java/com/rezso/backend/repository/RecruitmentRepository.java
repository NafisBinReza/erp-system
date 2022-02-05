package com.rezso.backend.repository;

import com.rezso.backend.model.Recruitment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecruitmentRepository extends JpaRepository<Recruitment, Integer> {
}
