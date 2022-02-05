package com.rezso.backend.controller;

import com.rezso.backend.model.Employee;
import com.rezso.backend.model.Recruitment;
import com.rezso.backend.repository.EmployeeRepository;
import com.rezso.backend.repository.JobRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employees")
@CrossOrigin(origins = "http://localhost:3000")
public class EmployeeController {

    private final EmployeeRepository employeeRepository;
    private final JobRepository jobRepository;

    public EmployeeController(EmployeeRepository employeeRepository, JobRepository jobRepository) {
        this.employeeRepository = employeeRepository;
        this.jobRepository = jobRepository;
    }

    @GetMapping("/all")
    public List<Employee> all() {
        return employeeRepository.findAll();
    }

    @GetMapping("/emp/{id}")
    public Optional<Employee> getEmployee(@PathVariable Integer id){
        return employeeRepository.findById(id);
    }

    @PostMapping("/emp")
    public Employee newEmployee(@RequestBody Employee newEmployee) {
        return employeeRepository.save(newEmployee);
    }

    @PutMapping("/emp")
    public Employee putEmployee(@RequestBody Employee newEmployee){
        Integer id = newEmployee.getId();
        return employeeRepository.findById(id)
                .map(employee -> {
                    employee.setUsername(newEmployee.getUsername());
                    employee.setRoles(newEmployee.getRoles());
                    employee.setPassword(newEmployee.getPassword());
                    employee.setCoach(newEmployee.getCoach());
                    employee.setBankAccountNo(newEmployee.getBankAccountNo());
                    employee.setMaritalStatus(newEmployee.getMaritalStatus());
                    employee.setDepartment(newEmployee.getDepartment());
                    employee.setGender(newEmployee.getGender());
                    employee.setDateOfBirth(newEmployee.getDateOfBirth());
                    employee.setHomeAddress(newEmployee.getHomeAddress());
                    employee.setIdentificationNo(newEmployee.getIdentificationNo());
                    employee.setPassport(newEmployee.getPassport());
                    employee.setJobTitle(newEmployee.getJobTitle());
                    employee.setManager(newEmployee.getManager());
                    employee.setNationality(newEmployee.getNationality());
                    employee.setWorkEmail(newEmployee.getWorkEmail());
                    employee.setWorkingAddress(newEmployee.getWorkingAddress());
                    employee.setWorkLocation(newEmployee.getWorkLocation());
                    employee.setWorkMobile(newEmployee.getWorkMobile());
                    employee.setWorkPhone(newEmployee.getWorkPhone());

                    return employeeRepository.save(employee);
                })
                .orElseGet(() -> {
                    newEmployee.setId(id);
                    return employeeRepository.save(newEmployee);
                });
    }

    @DeleteMapping("/emp/{id}")
    public String deleteEmployee(@PathVariable Integer id){

        employeeRepository.deleteById(id);
        return "Employee Deleted";
    }


}
