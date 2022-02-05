package com.rezso.backend.integrationTesting;

import com.rezso.backend.model.Employee;
import com.rezso.backend.repository.EmployeeRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmployeeControllerIntTest {

    @LocalServerPort
    int randomPort;

    @Autowired
    TestRestTemplate testRestTemplate;

    @MockBean
    EmployeeRepository employeeRepository;

    @Test
    @DisplayName("Testing all() method")
    void testAllInt() throws Exception {
        final String baseUrl = "http://localhost:" + randomPort + "/employees/all";
        URI uri = new URI(baseUrl);

        Mockito.when(employeeRepository.findAll()).thenReturn(getEmployeeList());

        List response = testRestTemplate.getForObject(uri, List.class);

        System.out.println(response.get(0));
        assertNotNull(response);
    }

    @Test
    @DisplayName("Testing getEmployee() method")
    void testGetEmployeeInt() throws Exception {
        final String baseUrl = "http://localhost:" + randomPort + "/employees/emp/2";
        URI uri = new URI(baseUrl);

        Mockito.when(employeeRepository.findById(any())).thenReturn(getEmployeeList().stream().findFirst());

        Employee response = testRestTemplate.getForObject(uri, Employee.class);

        assertNotNull(response);
        assertEquals("nafis", response.getUsername());
    }

    @Test
    @DisplayName("Testing newEmployee() method")
    void testNewEmployeeInt() throws Exception {
        final String baseUrl = "http://localhost:" + randomPort + "/employees/emp";
        URI uri = new URI(baseUrl);

        Employee employee = new Employee(1, "nafis", "asd", "asd", "sgsd", "hjdasfg", "adf", "asd", "asd", "asd", "asd", "asd", "asd", "asd", "asd", "asd", "asd", "asd", "asd", "asd", "ads");
        Mockito.when(employeeRepository.save(any())).thenReturn(new Employee(1, "nafis", "asd", "asd", "sgsd", "hjdasfg", "adf", "asd", "asd", "asd", "asd", "asd", "asd", "asd", "asd", "asd", "asd", "asd", "asd", "asd", "ads"));

        Employee response = testRestTemplate.postForObject(uri, employee, Employee.class);

        assertNotNull(response);
        assertEquals("nafis", response.getUsername());
    }

    @Test
    @DisplayName("Testing putEmployee() method")
    void testPutEmployeeInt() throws Exception {
        final String baseUrl = "http://localhost:" + randomPort + "/employees/emp";
        URI uri = new URI(baseUrl);

        HttpEntity<Employee> httpEntity = new HttpEntity<>(new Employee(1, "rezso", "asd", "asd", "sgsd", "hjdasfg", "adf", "asd", "asd", "asd", "asd", "asd", "asd", "asd", "asd", "asd", "asd", "asd", "asd", "asd", "ads"));
        Mockito.when(employeeRepository.save(any())).thenReturn(new Employee(1, "nafis", "asd", "asd", "sgsd", "hjdasfg", "adf", "asd", "asd", "asd", "asd", "asd", "asd", "asd", "asd", "asd", "asd", "asd", "asd", "asd", "ads"));

        ResponseEntity<Employee> response = testRestTemplate.exchange(uri, HttpMethod.PUT, httpEntity, Employee.class);

        System.out.println(response.getBody());
        assertNotNull(response);
        assertEquals("nafis", response.getBody().getUsername());
    }

    @Test
    @DisplayName("Testing deleteEmployee() method")
    void testDeleteEmployeeInt() throws Exception {
        final String baseUrl = "http://localhost:" + randomPort + "/employees/emp/1";
        URI uri = new URI(baseUrl);

        HttpEntity<Employee> httpEntity = new HttpEntity<>(new Employee(1, "rezso", "asd", "asd", "sgsd", "hjdasfg", "adf", "asd", "asd", "asd", "asd", "asd", "asd", "asd", "asd", "asd", "asd", "asd", "asd", "asd", "ads"));

        ResponseEntity<String> response = testRestTemplate.exchange(uri, HttpMethod.DELETE, httpEntity, String.class);

        assertNotNull(response);
        assertEquals("Employee Deleted", response.getBody());
    }

    public List<Employee> getEmployeeList() {
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee(1, "rezso", "asd", "asd", "sgsd", "hjdasfg", "adf", "asd", "asd", "asd", "asd", "asd", "asd", "asd", "asd", "asd", "asd", "asd", "asd", "asd", "ads"));
        employeeList.add(new Employee(1, "grim", "asd", "asd", "sgsd", "hjdasfg", "adf", "asd", "asd", "asd", "asd", "asd", "asd", "asd", "asd", "asd", "asd", "asd", "asd", "asd", "ads"));

        return employeeList;
    }
}
