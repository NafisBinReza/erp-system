package com.rezso.backend.controller;

import com.rezso.backend.model.Employee;
import com.rezso.backend.model.Job;
import com.rezso.backend.model.Recruitment;
import com.rezso.backend.repository.EmployeeRepository;
import com.rezso.backend.repository.JobRepository;
import com.rezso.backend.repository.RecruitmentRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/recruitment")
@CrossOrigin(origins = "http://localhost:3000")
public class RecruitmentController {

    private final RecruitmentRepository recruitmentRepository;
    private final JobRepository jobRepository;
    private final EmployeeRepository employeeRepository;

    public RecruitmentController(RecruitmentRepository recruitmentRepository, JobRepository jobRepository, EmployeeRepository employeeRepository) {
        this.recruitmentRepository = recruitmentRepository;
        this.jobRepository = jobRepository;
        this.employeeRepository = employeeRepository;
    }

    @GetMapping("/all")
    public List<Recruitment> all() {
        return recruitmentRepository.findAll();
    }

    @GetMapping("/jobs")
    public List<Job> allJobs() {
        jobRepository.findJobByJobTitle("Chief Executive Officer").setCurrentEmployees((int) employeeRepository.findEmployeeByJobTitle("Chief Executive Officer").stream().count());
        jobRepository.findJobByJobTitle("Consultant").setCurrentEmployees((int) employeeRepository.findEmployeeByJobTitle("Consultant").stream().count());
        jobRepository.findJobByJobTitle("Chief Technical Officer").setCurrentEmployees((int) employeeRepository.findEmployeeByJobTitle("Chief Technical Officer").stream().count());
        jobRepository.findJobByJobTitle("Experienced Developer").setCurrentEmployees((int) employeeRepository.findEmployeeByJobTitle("Experienced Developer").stream().count());
        jobRepository.findJobByJobTitle("Human Resource Manager").setCurrentEmployees((int) employeeRepository.findEmployeeByJobTitle("Human Resource Manager").stream().count());
        jobRepository.findJobByJobTitle("Marketing and Community Manager").setCurrentEmployees((int) employeeRepository.findEmployeeByJobTitle("Marketing and Community Manager").stream().count());
        jobRepository.findJobByJobTitle("Trainee").setCurrentEmployees((int) employeeRepository.findEmployeeByJobTitle("Trainee").stream().count());
        return jobRepository.findAll();
    }

    @GetMapping("/rec/{id}")
    public Optional<Recruitment> getRecruitment(@PathVariable Integer id){
        return recruitmentRepository.findById(id);
    }

    @PostMapping("/rec")
    public Recruitment newRecruitment(@RequestBody Recruitment newRecruitment) {
        if(newRecruitment.getJobTitle().equals("Consultant")) {
            jobRepository.findJobByJobTitle("Consultant").setForecastedEmployees(jobRepository.findJobByJobTitle("Consultant").getForecastedEmployees() + 1);
            jobRepository.findJobByJobTitle("Consultant").setStatus("Recruitment in Progress");
        }
        else if(newRecruitment.getJobTitle().equals("Chief Executive Officer")){
            jobRepository.findJobByJobTitle("Chief Executive Officer").setForecastedEmployees(jobRepository.findJobByJobTitle("Chief Executive Officer").getForecastedEmployees() + 1);
            jobRepository.findJobByJobTitle("Chief Executive Officer").setStatus("Recruitment in Progress");
        }
        else if(newRecruitment.getJobTitle().equals("Chief Technical Officer")){
            jobRepository.findJobByJobTitle("Chief Technical Officer").setForecastedEmployees(jobRepository.findJobByJobTitle("Chief Technical Officer").getForecastedEmployees() + 1);
            jobRepository.findJobByJobTitle("Chief Technical Officer").setStatus("Recruitment in Progress");
        }
        else if(newRecruitment.getJobTitle().equals("Experienced Developer")){
            jobRepository.findJobByJobTitle("Experienced Developer").setForecastedEmployees(jobRepository.findJobByJobTitle("Experienced Developer").getForecastedEmployees() + 1);
            jobRepository.findJobByJobTitle("Experienced Developer").setStatus("Recruitment in Progress");
        }
        else if(newRecruitment.getJobTitle().equals("Human Resource Manager")){
            jobRepository.findJobByJobTitle("Human Resource Manager").setForecastedEmployees(jobRepository.findJobByJobTitle("Human Resource Manager").getForecastedEmployees() + 1);
            jobRepository.findJobByJobTitle("Human Resource Manager").setStatus("Recruitment in Progress");
        }
        else if(newRecruitment.getJobTitle().equals("Marketing and Community Manager")){
            jobRepository.findJobByJobTitle("Marketing and Community Manager").setForecastedEmployees(jobRepository.findJobByJobTitle("Marketing and Community Manager").getForecastedEmployees() + 1);
            jobRepository.findJobByJobTitle("Marketing and Community Manager").setStatus("Recruitment in Progress");
        }
        else if(newRecruitment.getJobTitle().equals("Trainee")){
            jobRepository.findJobByJobTitle("Trainee").setForecastedEmployees(jobRepository.findJobByJobTitle("Trainee").getForecastedEmployees() + 1);
            jobRepository.findJobByJobTitle("Trainee").setStatus("Recruitment in Progress");
        }
        else{}
        return recruitmentRepository.save(newRecruitment);
    }

    @PutMapping("/rec")
    public Recruitment putRecruitment(@RequestBody Recruitment newRecruitment){
        Integer id = newRecruitment.getId();
        return recruitmentRepository.findById(id)
                .map(recruitment -> {
                    recruitment.setAppreciation(newRecruitment.getAppreciation());
                    recruitment.setEmail(newRecruitment.getEmail());
                    recruitment.setCreationDate(newRecruitment.getCreationDate());
                    recruitment.setMedium(newRecruitment.getMedium());
                    recruitment.setJobTitle(newRecruitment.getJobTitle());
                    recruitment.setName(newRecruitment.getName());
                    recruitment.setPhone(newRecruitment.getPhone());
                    recruitment.setMobile(newRecruitment.getMobile());
                    recruitment.setSource(newRecruitment.getSource());
                    recruitment.setResponsible(newRecruitment.getResponsible());
                    recruitment.setStage(newRecruitment.getStage());
                    recruitment.setSubject(newRecruitment.getSubject());
                    recruitment.setDepartment(newRecruitment.getDepartment());
                    recruitment.setDegree(newRecruitment.getDegree());
                    recruitment.setExpectedSalary(newRecruitment.getExpectedSalary());
                    recruitment.setProposedSalary(newRecruitment.getProposedSalary());
                    recruitment.setAvailability(newRecruitment.getAvailability());

                    if(newRecruitment.getStage().equals("Contract Signed")) {
                        if (newRecruitment.getJobTitle().equals("Consultant")) {
                            jobRepository.findJobByJobTitle("Consultant").setHiredEmployees(jobRepository.findJobByJobTitle("Consultant").getHiredEmployees() + 1);
                            jobRepository.findJobByJobTitle("Consultant").setForecastedEmployees(jobRepository.findJobByJobTitle("Consultant").getForecastedEmployees() - 1);
                        } else if (newRecruitment.getJobTitle().equals("Chief Executive Officer")) {
                            jobRepository.findJobByJobTitle("Chief Executive Officer").setHiredEmployees(jobRepository.findJobByJobTitle("Chief Executive Officer").getHiredEmployees() + 1);
                            jobRepository.findJobByJobTitle("Chief Executive Officer").setForecastedEmployees(jobRepository.findJobByJobTitle("Chief Executive Officer").getForecastedEmployees() - 1);
                        } else if (newRecruitment.getJobTitle().equals("Chief Technical Officer")) {
                            jobRepository.findJobByJobTitle("Chief Technical Officer").setHiredEmployees(jobRepository.findJobByJobTitle("Chief Technical Officer").getHiredEmployees() + 1);
                            jobRepository.findJobByJobTitle("Chief Technical Officer").setForecastedEmployees(jobRepository.findJobByJobTitle("Chief Technical Officer").getForecastedEmployees() - 1);
                        } else if (newRecruitment.getJobTitle().equals("Experienced Developer")) {
                            jobRepository.findJobByJobTitle("Experienced Developer").setHiredEmployees(jobRepository.findJobByJobTitle("Experienced Developer").getHiredEmployees() + 1);
                            jobRepository.findJobByJobTitle("Experienced Developer").setForecastedEmployees(jobRepository.findJobByJobTitle("Experienced Developer").getForecastedEmployees() - 1);
                        } else if (newRecruitment.getJobTitle().equals("Human Resource Manager")) {
                            jobRepository.findJobByJobTitle("Human Resource Manager").setHiredEmployees(jobRepository.findJobByJobTitle("Human Resource Manager").getHiredEmployees() + 1);
                            jobRepository.findJobByJobTitle("Human Resource Manager").setForecastedEmployees(jobRepository.findJobByJobTitle("Human Resource Manager").getForecastedEmployees() - 1);
                        } else if (newRecruitment.getJobTitle().equals("Marketing and Community Manager")) {
                            jobRepository.findJobByJobTitle("Marketing and Community Manager").setForecastedEmployees(jobRepository.findJobByJobTitle("Marketing and Community Manager").getForecastedEmployees() - 1);
                            jobRepository.findJobByJobTitle("Marketing and Community Manager").setHiredEmployees(jobRepository.findJobByJobTitle("Marketing and Community Manager").getHiredEmployees() + 1);
                        } else if (newRecruitment.getJobTitle().equals("Trainee")) {
                            jobRepository.findJobByJobTitle("Trainee").setForecastedEmployees(jobRepository.findJobByJobTitle("Trainee").getForecastedEmployees() - 1);
                            jobRepository.findJobByJobTitle("Trainee").setHiredEmployees(jobRepository.findJobByJobTitle("Trainee").getHiredEmployees() + 1);
                        } else {
                        }
                        employeeRepository.save(new Employee(0, newRecruitment.getName(), null, null, null, null, null, null, null, newRecruitment.getDepartment(),newRecruitment.getJobTitle(), null, null, null, null, null, null, null, null, null, null));
                    }
                    return recruitmentRepository.save(recruitment);
                })
                .orElseGet(() -> {
                    newRecruitment.setId(id);
                    return recruitmentRepository.save(newRecruitment);
                });
    }

    @DeleteMapping("/rec/{id}")
    public String deleteRecruitment(@PathVariable Integer id){

        Optional<Recruitment> newRecruitment = recruitmentRepository.findById(id);
        if(newRecruitment.get().getJobTitle().equals("Consultant")) {
            jobRepository.findJobByJobTitle("Consultant").setForecastedEmployees(jobRepository.findJobByJobTitle("Consultant").getForecastedEmployees() - 1);
            jobRepository.findJobByJobTitle("Consultant").setStatus("Recruitment in Progress");
        }
        else if(newRecruitment.get().getJobTitle().equals("Chief Executive Officer")){
            jobRepository.findJobByJobTitle("Chief Executive Officer").setForecastedEmployees(jobRepository.findJobByJobTitle("Chief Executive Officer").getForecastedEmployees() - 1);
            jobRepository.findJobByJobTitle("Chief Executive Officer").setStatus("Recruitment in Progress");
        }
        else if(newRecruitment.get().getJobTitle().equals("Chief Technical Officer")){
            jobRepository.findJobByJobTitle("Chief Technical Officer").setForecastedEmployees(jobRepository.findJobByJobTitle("Chief Technical Officer").getForecastedEmployees() - 1);
            jobRepository.findJobByJobTitle("Chief Technical Officer").setStatus("Recruitment in Progress");
        }
        else if(newRecruitment.get().getJobTitle().equals("Experienced Developer")){
            jobRepository.findJobByJobTitle("Experienced Developer").setForecastedEmployees(jobRepository.findJobByJobTitle("Experienced Developer").getForecastedEmployees() - 1);
            jobRepository.findJobByJobTitle("Experienced Developer").setStatus("Recruitment in Progress");
        }
        else if(newRecruitment.get().getJobTitle().equals("Human Resource Manager")){
            jobRepository.findJobByJobTitle("Human Resource Manager").setForecastedEmployees(jobRepository.findJobByJobTitle("Human Resource Manager").getForecastedEmployees() - 1);
            jobRepository.findJobByJobTitle("Human Resource Manager").setStatus("Recruitment in Progress");
        }
        else if(newRecruitment.get().getJobTitle().equals("Marketing and Community Manager")){
            jobRepository.findJobByJobTitle("Marketing and Community Manager").setForecastedEmployees(jobRepository.findJobByJobTitle("Marketing and Community Manager").getForecastedEmployees() - 1);
            jobRepository.findJobByJobTitle("Marketing and Community Manager").setStatus("Recruitment in Progress");
        }
        else if(newRecruitment.get().getJobTitle().equals("Trainee")){
            jobRepository.findJobByJobTitle("Trainee").setForecastedEmployees(jobRepository.findJobByJobTitle("Trainee").getForecastedEmployees() - 1);
            jobRepository.findJobByJobTitle("Trainee").setStatus("Recruitment in Progress");
        }
        else{}

        recruitmentRepository.deleteById(id);
        return "Recruitment Deleted";
    }

}

