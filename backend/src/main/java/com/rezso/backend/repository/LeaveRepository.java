package com.rezso.backend.repository;

import com.rezso.backend.model.Employee;
import com.rezso.backend.model.Leave;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LeaveRepository extends JpaRepository<Leave, Integer> {

    List<Leave> findLeaveByRequestType(String requestType);

}
