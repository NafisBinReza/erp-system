package com.rezso.backend.integrationTesting;

import com.rezso.backend.model.Employee;
import com.rezso.backend.model.Job;
import com.rezso.backend.model.Leave;
import com.rezso.backend.model.Recruitment;
import com.rezso.backend.repository.EmployeeRepository;
import com.rezso.backend.repository.JobRepository;
import com.rezso.backend.repository.LeaveRepository;
import com.rezso.backend.repository.RecruitmentRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.sql.Date;
import java.text.ParseException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RecruitmentControllerIntTest {
    @LocalServerPort
    int randomPort;

    @Autowired
    TestRestTemplate testRestTemplate;

    @MockBean
    RecruitmentRepository recruitmentRepository;

    @MockBean
    JobRepository jobRepository;

    @MockBean
    EmployeeRepository employeeRepository;

    @Test
    @DisplayName("Testing all() method")
    void testAllInt() throws Exception {
        final String baseUrl = "http://localhost:" + randomPort + "/recruitment/all";
        URI uri = new URI(baseUrl);

        Mockito.when(recruitmentRepository.findAll()).thenReturn(getRecruitmentList());

        List response = testRestTemplate.getForObject(uri, List.class);

        System.out.println(response.get(0));
        assertNotNull(response);
    }

    @Test
    @DisplayName("Testing getJobs() method")
    void testGetJobsInt() throws Exception {
        final String baseUrl = "http://localhost:" + randomPort + "/recruitment/jobs";
        URI uri = new URI(baseUrl);

        Mockito.when(recruitmentRepository.findAll()).thenReturn(getRecruitmentList());
        Mockito.when(jobRepository.findAll()).thenReturn(getJobList());
        Mockito.when(jobRepository.findJobByJobTitle(any())).thenReturn(new Job(1, "", "", 0, 0, 0, 0, ""));
        Mockito.when(employeeRepository.findEmployeeByJobTitle(any())).thenReturn(getEmployeeList());

        List response = testRestTemplate.getForObject(uri, List.class);

        System.out.println(response.get(0));
        assertNotNull(response);
    }

    @Test
    @DisplayName("Testing getRecruitment() method")
    void testGetRecruitmentInt() throws Exception {
        final String baseUrl = "http://localhost:" + randomPort + "/recruitment/rec/2";
        URI uri = new URI(baseUrl);

        Mockito.when(recruitmentRepository.findById(any())).thenReturn(Optional.of(new Recruitment(1, "nafis", Date.from(Instant.now()), "", "", "", "", "", "", "", "", "", "", "", "", "", "", "")));

        Recruitment response = testRestTemplate.getForObject(uri, Recruitment.class);

        assertNotNull(response);
        assertEquals("nafis", response.getName());
    }

    @Test
    @DisplayName("Testing newRecruitment() method")
    void testNewRecruitmentInt() throws Exception {
        final String baseUrl = "http://localhost:" + randomPort + "/recruitment/rec";
        URI uri = new URI(baseUrl);

        Recruitment recruitment = new Recruitment(1, "nafis", Date.from(Instant.now()), "", "", "", "", "", "", "", "", "", "", "", "", "", "", "");
        Mockito.when(recruitmentRepository.save(any())).thenReturn(new Recruitment(1, "nafis", Date.from(Instant.now()), "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""));

        Recruitment response = testRestTemplate.postForObject(uri, recruitment, Recruitment.class);

        assertNotNull(response);
        assertEquals("nafis", response.getName());
    }

    @Test
    @DisplayName("Testing putRecruitment() method")
    void testPutRecruitmentInt() throws Exception {
        final String baseUrl = "http://localhost:" + randomPort + "/recruitment/rec";
        URI uri = new URI(baseUrl);

        HttpEntity<Recruitment> httpEntity = new HttpEntity<>(new Recruitment(1, "nafis", Date.from(Instant.now()), "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""));
        Mockito.when(recruitmentRepository.save(any())).thenReturn(new Recruitment(1, "nafis", Date.from(Instant.now()), "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""));

        ResponseEntity<Recruitment> response = testRestTemplate.exchange(uri, HttpMethod.PUT, httpEntity, Recruitment.class);

        System.out.println(response.getBody());
        assertNotNull(response);
        assertEquals("nafis", response.getBody().getName());
    }

    @Test
    @DisplayName("Testing deleteRecruitment() method")
    void testDeleteRecruitmentInt() throws Exception {
        final String baseUrl = "http://localhost:" + randomPort + "/recruitment/rec/1";
        URI uri = new URI(baseUrl);

        Mockito.when(recruitmentRepository.findById(any())).thenReturn(Optional.of(new Recruitment(1, "nafis", Date.from(Instant.now()), "", "", "", "", "", "", "", "", "", "", "", "", "", "", "")));
        HttpEntity<Recruitment> httpEntity = new HttpEntity<>(new Recruitment(1, "nafis", Date.from(Instant.now()), "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""));

        ResponseEntity<String> response = testRestTemplate.exchange(uri, HttpMethod.DELETE, httpEntity, String.class);

        assertNotNull(response);
        assertEquals("Recruitment Deleted", response.getBody());
    }

    public List<Recruitment> getRecruitmentList() {
        List<Recruitment> recruitmentList = new ArrayList<>();
        recruitmentList.add(new Recruitment(1, "nafis", Date.from(Instant.now()), "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""));
        recruitmentList.add(new Recruitment(1, "grim", Date.from(Instant.now()), "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""));

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
