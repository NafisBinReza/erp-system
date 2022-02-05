package com.rezso.backend.service;

import com.rezso.backend.model.Leave;
import com.rezso.backend.repository.LeaveRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public
class LoadAllocationLeaves {

    private static final Logger log = LoggerFactory.getLogger(LoadAllocationLeaves.class);

    @Bean
    public CommandLineRunner initDatabase(LeaveRepository lRepository) {
        if(!lRepository.findLeaveByRequestType("Allocation Request").isEmpty()){
            return null;
        }
        else {
            return args -> {
                log.info("Preloading " + lRepository.save(new Leave(100000001, 100001, "nafis", "Allocation Request", "Legal Leaves for nafis", 20, null, null, "legal", "Approved", "ANY", "This is everybody's right")));
                log.info("Preloading " + lRepository.save(new Leave(100000002, 100002, "nafis", "Allocation Request", "Compensatory leaves for nafis", 7, null, null, "compensatory", "Approved", "ANY", "This is everybody's right")));
                log.info("Preloading " + lRepository.save(new Leave(100000003, 100003, "nafis", "Allocation Request", "Unpaid leaves for nafis", 7, null, null, "unpaid", "Approved", "ANY", "This is everybody's right")));
            };
        }
    }
}


//package com.rezso.backend.service;
//
//import com.rezso.backend.model.Leave;
//import com.rezso.backend.repository.LeaveRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.PostConstruct;
//
//@Component
//public class LoadAllocationLeaves {
//
//    @Autowired
//    static LeaveRepository leaveRepository;
//
//    @PostConstruct
//    public static void loadLeaves() throws Exception{
//        Leave l1 = new Leave(100000001, 100001, "nafis", "Allocation Request", "Legal Leaves for nafis", 20, null, null, "legal", "Approved", "ANY", "This is everybody's right");
//        Leave l2 = new Leave(100000002, 100002, "nafis", "Allocation Request", "Compensatory leaves for nafis", 7, null, null, "compensatory", "Approved", "ANY", "This is everybody's right");
//        Leave l3 = new Leave(100000003, 100003, "nafis", "Allocation Request", "Unpaid leaves for nafis", 7, null, null, "unpaid", "Approved", "ANY", "This is everybody's right");
//
//        leaveRepository.save(l1);
//        leaveRepository.save(l2);
//        leaveRepository.save(l3);
//    }
//
//}
