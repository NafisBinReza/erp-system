package com.rezso.backend.unitTesting.controller;

import com.rezso.backend.controller.EmployeeController;
import com.rezso.backend.model.Employee;
import com.rezso.backend.repository.EmployeeRepository;
import com.rezso.backend.repository.JobRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

public class EmployeeControllerTest {
    @Mock
    EmployeeRepository employeeRepository;

    @Mock
    JobRepository jobRepository;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Testing all() method")
    public void testAll(){
        Mockito.when(employeeRepository.findAll()).thenReturn(getEmployeeList());
        EmployeeController employeeController = new EmployeeController(employeeRepository, jobRepository);
        List<Employee> employeeList = employeeController.all();
        assertNotNull(employeeList);
        assertEquals(getEmployeeList(), employeeList);
        assertTrue(employeeList.containsAll(getEmployeeList()));
    }

    @Test
    @DisplayName("Testing getEmployee() method")
    public void testGetEmployee(){
        Mockito.when(employeeRepository.findById(1)).thenReturn(getEmployeeList().stream().findFirst());
        EmployeeController employeeController = new EmployeeController(employeeRepository, jobRepository);
        Optional<Employee> employee = employeeController.getEmployee(1);
        assertNotNull(employee);
        assertEquals(getEmployeeList().stream().findFirst(), employee);
        assertTrue(employee.equals(getEmployeeList().stream().findFirst()));
    }

    @Test
    @DisplayName("Testing newEmployee() method")
    public void testNewEmployee(){
        Mockito.when(employeeRepository.save(any())).thenReturn(new Employee(1, "nafis", "asd", "asd", "sgsd", "hjdasfg", "adf", "asd", "asd", "asd", "asd", "asd", "asd", "asd", "asd", "asd", "asd", "asd", "asd", "asd", "ads"));
        EmployeeController employeeController = new EmployeeController(employeeRepository, jobRepository);
        Employee testEmployee = new Employee(1, "nafis", "asd", "asd", "sgsd", "hjdasfg", "adf", "asd", "asd", "asd", "asd", "asd", "asd", "asd", "asd", "asd", "asd", "asd", "asd", "asd", "ads");
        Employee employee = employeeController.newEmployee(testEmployee);
        assertNotNull(employee);
        assertEquals(new Employee(1, "nafis", "asd", "asd", "sgsd", "hjdasfg", "adf", "asd", "asd", "asd", "asd", "asd", "asd", "asd", "asd", "asd", "asd", "asd", "asd", "asd", "ads"), employee);
        assertTrue(employee.equals(new Employee(1, "nafis", "asd", "asd", "sgsd", "hjdasfg", "adf", "asd", "asd", "asd", "asd", "asd", "asd", "asd", "asd", "asd", "asd", "asd", "asd", "asd", "ads")));
    }

    @Test
    @DisplayName("Testing putEmployee() method")
    public void testPutEmployee(){
        Mockito.when(employeeRepository.save(any())).thenReturn(new Employee(1, "nafis", "asd", "asd", "sgsd", "hjdasfg", "adf", "asd", "asd", "asd", "asd", "asd", "asd", "asd", "asd", "asd", "asd", "asd", "asd", "asd", "ads"));
        EmployeeController employeeController = new EmployeeController(employeeRepository, jobRepository);
        Employee testEmployee = new Employee(1, "nafis", "asd", "asd", "sgsd", "hjdasfg", "adf", "asd", "asd", "asd", "asd", "asd", "asd", "asd", "asd", "asd", "asd", "asd", "asd", "asd", "ads");
        Employee employee = employeeController.putEmployee(testEmployee);
        assertNotNull(employee);
        assertEquals(new Employee(1, "nafis", "asd", "asd", "sgsd", "hjdasfg", "adf", "asd", "asd", "asd", "asd", "asd", "asd", "asd", "asd", "asd", "asd", "asd", "asd", "asd", "ads"), employee);
    }

    @Test
    @DisplayName("Testing deleteEmployee() method")
    public void testDeleteEmployee(){
        EmployeeController employeeController = new EmployeeController(employeeRepository, jobRepository);
        String res = employeeController.deleteEmployee(1);
        assertNotNull(res);
        assertEquals("Employee Deleted", res);
    }


    public List<Employee> getEmployeeList() {
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee(1, "nafis", "asd", "asd", "sgsd", "hjdasfg", "adf", "asd", "asd", "asd", "asd", "asd", "asd", "asd", "asd", "asd", "asd", "asd", "asd", "asd", "ads"));
        employeeList.add(new Employee(1, "grim", "asd", "asd", "sgsd", "hjdasfg", "adf", "asd", "asd", "asd", "asd", "asd", "asd", "asd", "asd", "asd", "asd", "asd", "asd", "asd", "ads"));

        return employeeList;
    }
}
