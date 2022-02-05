package com.rezso.backend.controller;

import com.rezso.backend.model.Leave;
import com.rezso.backend.repository.LeaveRepository;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.TimeUnit;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/leave")
public class LeaveController {

    private static int totalLegalLeave = 20;
    private static int totalCompensatoryLeaves = 7;
    private static int totalUnpaidLeaves = 7;

    private final LeaveRepository leaveRepository;

    public LeaveController(LeaveRepository leaveRepository) {
        this.leaveRepository = leaveRepository;
    }

    @GetMapping("/all")
    public List<Leave> all(){
        return leaveRepository.findAll();
    }

    @GetMapping("/lv/{id}")
    public Optional<Leave> getLeave(@PathVariable Integer id){
        return leaveRepository.findById(id);
    }

    @GetMapping("/stat")
    public Map<String, Integer> stat(){
        Map<String, Integer> myList = new HashMap<>();
        myList.put("legalLeaves", totalLegalLeave);
        myList.put("compensatoryLeaves", totalCompensatoryLeaves);
        myList.put("unpaidLeaves", totalUnpaidLeaves);
        return myList;
    }

    @PostMapping("/lv")
    public Leave newLeave(@RequestBody Leave newLeave) {
        long diff = newLeave.getEndDate().getTime() - newLeave.getStartDate().getTime();
        newLeave.setNumberOfDays(Math.toIntExact(TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS)));

        if(newLeave.getLeaveType().equals("legal")){
            totalLegalLeave = totalLegalLeave - newLeave.getNumberOfDays();
        }
        else if(newLeave.getLeaveType().equals("compensatory")){
            totalCompensatoryLeaves = totalCompensatoryLeaves - newLeave.getNumberOfDays();
        }
        else if(newLeave.getLeaveType().equals("unpaid")){
            totalUnpaidLeaves = totalUnpaidLeaves - newLeave.getNumberOfDays();
        }
        else{

        }

        newLeave.setNumberOfDays(newLeave.getNumberOfDays() * -1);

        return leaveRepository.save(newLeave);
    }

    @PutMapping("/lv")
    public Leave putLeave(@RequestBody Leave newLeave){
        int id = newLeave.getId();

        return leaveRepository.findById(id).map(leave -> {

        if(newLeave.getLeaveType().equals("legal")){
            totalLegalLeave = totalLegalLeave + (leave.getNumberOfDays() * -1);
        }
        else if(newLeave.getLeaveType().equals("compensatory")){
            totalCompensatoryLeaves = totalCompensatoryLeaves + (leave.getNumberOfDays() * -1);
        }
        else if(newLeave.getLeaveType().equals("unpaid")){
            totalUnpaidLeaves = totalUnpaidLeaves + (leave.getNumberOfDays() * -1);
        }
        else{

        }
                    leave.setRequestType(newLeave.getRequestType());
                    leave.setDescription(newLeave.getDescription());
                    leave.setEndDate(newLeave.getEndDate());
                    leave.setStartDate(newLeave.getStartDate());
                    leave.setLeaveType(newLeave.getLeaveType());
                    leave.setStatus(newLeave.getStatus());
                    leave.setDepartment(newLeave.getDepartment());
                    leave.setComment(newLeave.getComment());

                    long diff = leave.getEndDate().getTime() - leave.getStartDate().getTime();
                    leave.setNumberOfDays(Math.toIntExact(TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS)));

                    if(newLeave.getLeaveType().equals("legal")){
                        totalLegalLeave = totalLegalLeave - leave.getNumberOfDays();
                    }
                    else if(newLeave.getLeaveType().equals("compensatory")){
                        totalCompensatoryLeaves = totalCompensatoryLeaves - leave.getNumberOfDays();
                    }
                    else if(newLeave.getLeaveType().equals("unpaid")){
                        totalUnpaidLeaves = totalUnpaidLeaves - leave.getNumberOfDays();
                    }
                    else{

                    }

                    leave.setNumberOfDays(leave.getNumberOfDays() * -1);

                    return leaveRepository.save(leave);
                })
                .orElseGet(() -> {
                    newLeave.setId(id);
                    return leaveRepository.save(newLeave);
                });
    }

    @DeleteMapping("/lv/{id}")
    public String deleteLeave(@PathVariable Integer id){
        Leave target = leaveRepository.findById(id).get();
        if(target.getLeaveType().equals("legal")){
            totalLegalLeave = totalLegalLeave + (target.getNumberOfDays() * -1);
        }
        else if(target.getLeaveType().equals("compensatory")){
            totalCompensatoryLeaves = totalCompensatoryLeaves + (target.getNumberOfDays() * -1);
        }
        else if(target.getLeaveType().equals("unpaid")){
            totalUnpaidLeaves = totalUnpaidLeaves + (target.getNumberOfDays() * -1);
        }
        else{

        }
        leaveRepository.deleteById(id);
        return "Leave Entry Deleted";
    }


//    private Leave createUser(@RequestBody Leave newLeave) {
//        long diff = newLeave.getEndDate().getTime() - newLeave.getStartDate().getTime();
//        newLeave.setNumberOfDays(Math.toIntExact(TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS)));
//
//        if(newLeave.getLeaveType().equals("legal")){
//            newLeave.setLegalLeaves(newLeave.getLegalLeaves() - newLeave.getNumberOfDays());
//        }
//        else if(newLeave.getLeaveType().equals("compensatory")){
//            newLeave.setCompensatoryLeaves(newLeave.getCompensatoryLeaves() - newLeave.getNumberOfDays());
//        }
//        else if(newLeave.getLeaveType().equals("unpaid")){
//            newLeave.setUnpaidLeaves(newLeave.getUnpaidLeaves() - newLeave.getNumberOfDays());
//        }
//        else{
//
//        }
//
//        Leave l1 = new Leave(100000001, newLeave.getEmployeeId(), newLeave.getEmployee(), "Allocation Request", "Legal Leaves for "+newLeave.getEmployee(), 20, null, null, "legal", "Approved", "ANY", "This is everybody's right", 40, 0, 0);
//        Leave l2 = new Leave(100000002, newLeave.getEmployeeId(), newLeave.getEmployee(), "Allocation Request", "Compensatory leaves for "+newLeave.getEmployee(), 7, null, null, "compensatory", "Approved", "ANY", "This is everybody's right", 0, 14, 0);
//        Leave l3 = new Leave(100000003, newLeave.getEmployeeId(), newLeave.getEmployee(), "Allocation Request", "Unpaid leaves for "+newLeave.getEmployee(), 7, null, null, "unpaid", "Approved", "ANY", "This is everybody's right", 0, 0, 14);
//
//        leaveRepository.save(l1);
//        leaveRepository.save(l2);
//        leaveRepository.save(l3);
//
//        return leaveRepository.save(newLeave);
//    }
}
