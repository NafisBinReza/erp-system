package com.rezso.backend.unitTesting.controller;

import com.rezso.backend.controller.CRMController;
import com.rezso.backend.controller.RecruitmentController;
import com.rezso.backend.model.CRM;
import com.rezso.backend.model.Employee;
import com.rezso.backend.model.Job;
import com.rezso.backend.model.Recruitment;
import com.rezso.backend.repository.EmployeeRepository;
import com.rezso.backend.repository.JobRepository;
import com.rezso.backend.repository.RecruitmentRepository;
import com.rezso.backend.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

public class RecruitmentControllerTest {

    @Mock
    RecruitmentRepository recruitmentRepository;

    @Mock
    JobRepository jobRepository;

    @Mock
    EmployeeRepository employeeRepository;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Testing all() method")
    public void testAll() throws ParseException {
        Mockito.when(recruitmentRepository.findAll()).thenReturn(getRecruitmentList());
        RecruitmentController recruitmentController = new RecruitmentController(recruitmentRepository, jobRepository, employeeRepository);
        List<Recruitment> recruitmentList = recruitmentController.all();
        assertNotNull(recruitmentList);
        assertEquals(getRecruitmentList(), recruitmentList);
        assertTrue(recruitmentList.containsAll(getRecruitmentList()));
    }

    @Test
    @DisplayName("Testing getRecruitment() method")
    public void testGetRecruitment() throws ParseException {
        Mockito.when(recruitmentRepository.findById(any())).thenReturn(getRecruitmentList().stream().findFirst());
        RecruitmentController recruitmentController = new RecruitmentController(recruitmentRepository, jobRepository, employeeRepository);
        Optional<Recruitment> recruitment = recruitmentController.getRecruitment(1);
        assertNotNull(recruitment);
        assertEquals(getRecruitmentList().stream().findFirst(), recruitment);
        assertTrue(recruitment.equals(getRecruitmentList().stream().findFirst()));
    }

    @Test
    @DisplayName("Testing allJobs() method")
    public void testAllJobs() throws ParseException {
        Mockito.when(jobRepository.findAll()).thenReturn(getJobList());
        Mockito.when(jobRepository.findJobByJobTitle(any())).thenReturn(new Job(1, "", "", 0, 0, 0, 0, ""));
        Mockito.when(employeeRepository.findEmployeeByJobTitle(any())).thenReturn(getEmployeeList());
        RecruitmentController recruitmentController = new RecruitmentController(recruitmentRepository, jobRepository, employeeRepository);
        List<Job> jobList = recruitmentController.allJobs();
        assertNotNull(jobList);
        assertEquals(getJobList(), jobList);
        assertTrue(jobList.containsAll(getJobList()));
    }

    @Test
    @DisplayName("Testing newRecruitment() method")
    public void testNewRecruitment() throws ParseException {
        Mockito.when(recruitmentRepository.save(any())).thenReturn(new Recruitment(1, "nafis", new SimpleDateFormat( "yyyyMMdd" ).parse( "20100520" ), "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""));
        RecruitmentController recruitmentController = new RecruitmentController(recruitmentRepository, jobRepository, employeeRepository);
        Recruitment testRecruitment = new Recruitment(1, "nafis", new SimpleDateFormat( "yyyyMMdd" ).parse( "20100520" ), "", "", "", "", "", "", "", "", "", "", "", "", "", "", "");
        Recruitment recruitment = recruitmentController.newRecruitment(testRecruitment);
        assertNotNull(recruitment);
        assertEquals(new Recruitment(1, "nafis", new SimpleDateFormat( "yyyyMMdd" ).parse( "20100520" ), "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""), recruitment);
    }

    @Test
    @DisplayName("Testing putRecruitment() method")
    public void putRecruitment() throws ParseException {
        Mockito.when(recruitmentRepository.save(any())).thenReturn(new Recruitment(1, "nafis", new SimpleDateFormat( "yyyyMMdd" ).parse( "20100520" ), "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""));
        RecruitmentController recruitmentController = new RecruitmentController(recruitmentRepository, jobRepository, employeeRepository);
        Recruitment testRecruitment = new Recruitment(1, "nafis", new SimpleDateFormat( "yyyyMMdd" ).parse( "20100520" ), "", "", "", "", "", "", "", "", "", "", "", "", "", "", "");
        Recruitment recruitment = recruitmentController.putRecruitment(testRecruitment);
        assertNotNull(recruitment);
        assertEquals(new Recruitment(1, "nafis", new SimpleDateFormat( "yyyyMMdd" ).parse( "20100520" ), "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""), recruitment);
        assertTrue(recruitment.equals(new Recruitment(1, "nafis", new SimpleDateFormat( "yyyyMMdd" ).parse( "20100520" ), "", "", "", "", "", "", "", "", "", "", "", "", "", "", "")));
    }

    @Test
    @DisplayName("Testing deleteRecruitment() method")
    public void deleteRecruitment(){
        RecruitmentController recruitmentController = new RecruitmentController(recruitmentRepository, jobRepository, employeeRepository);
        String response = recruitmentController.deleteRecruitment(1);
        assertNotNull(response);
        assertEquals("Recruitment Deleted", response);
    }

    public List<Recruitment> getRecruitmentList() throws ParseException {
        List<Recruitment> recruitmentList = new ArrayList<>();
        recruitmentList.add(new Recruitment(1, "nafis", new SimpleDateFormat( "yyyyMMdd" ).parse( "20100520" ), "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""));
        recruitmentList.add(new Recruitment(1, "nafis", new SimpleDateFormat( "yyyyMMdd" ).parse( "20100520" ), "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""));

        return recruitmentList;
    }

    public List<Job> getJobList() throws ParseException {
        List<Job> jobList = new ArrayList<>();
        jobList.add(new Job(1, "", "", 0, 0, 0, 0, ""));
        jobList.add(new Job(1, "", "", 0, 0, 0, 0, ""));

        return jobList;
    }

    public List<Employee> getEmployeeList() {
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee(1, "nafis", "asd", "asd", "sgsd", "hjdasfg", "adf", "asd", "asd", "asd", "asd", "asd", "asd", "asd", "asd", "asd", "asd", "asd", "asd", "asd", "ads"));
        employeeList.add(new Employee(1, "grim", "asd", "asd", "sgsd", "hjdasfg", "adf", "asd", "asd", "asd", "asd", "asd", "asd", "asd", "asd", "asd", "asd", "asd", "asd", "asd", "ads"));

        return employeeList;
    }
}
