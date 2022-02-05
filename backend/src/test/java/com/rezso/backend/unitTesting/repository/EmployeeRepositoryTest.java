package com.rezso.backend.unitTesting.repository;

import com.rezso.backend.model.Employee;
import com.rezso.backend.repository.EmployeeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class EmployeeRepositoryTest {
    @Mock
    EmployeeRepository employeeRepository;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Employee Repository Test")
    public void checkRepository() throws Exception{
        Mockito.when(employeeRepository.findAll()).thenReturn(getEmployeeList());
        List<Employee> employees = employeeRepository.findAll();
        verify(employeeRepository, times(1)).findAll();
        Assertions.assertNotNull(employees);
        Assertions.assertEquals("nafis", employees.stream().findFirst().get().getUsername());
    }

    @Test
    @DisplayName("Fail Test of Employee Repository")
    public void failCheck(){
        Mockito.when(employeeRepository.findAll()).thenThrow(new RuntimeException("Fail Case"));
        try {
            List<Employee> employees = employeeRepository.findAll();
        }
        catch (RuntimeException exception){
            Assertions.assertEquals("Fail Case", exception.getMessage());
        }
    }

    public List<Employee> getEmployeeList() {
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee(1, "nafis", "asd", "asd", "sgsd", "hjdasfg", "adf", "asd", "asd", "asd", "asd", "asd", "asd", "asd", "asd", "asd", "asd", "asd", "asd", "asd", "ads"));
        employeeList.add(new Employee(1, "grim", "asd", "asd", "sgsd", "hjdasfg", "adf", "asd", "asd", "asd", "asd", "asd", "asd", "asd", "asd", "asd", "asd", "asd", "asd", "asd", "ads"));

        return employeeList;
    }
}
