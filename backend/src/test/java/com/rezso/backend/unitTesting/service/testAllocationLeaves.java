package com.rezso.backend.unitTesting.service;

import com.rezso.backend.model.Leave;
import com.rezso.backend.repository.LeaveRepository;
import com.rezso.backend.service.LoadAllocationLeaves;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.CommandLineRunner;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

public class testAllocationLeaves {

    @Mock
    LeaveRepository leaveRepository;

    @BeforeEach
    void init(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Test")
    void testLoadAllocationLeaves(){
        Mockito.when(leaveRepository.findLeaveByRequestType(any())).thenReturn(Collections.singletonList(new Leave(100000001, 100001, "nafis", "Allocation Request", "Legal Leaves for nafis", 20, null, null, "legal", "Approved", "ANY", "This is everybody's right")));
        LoadAllocationLeaves loadAllocationLeaves = new LoadAllocationLeaves();
        CommandLineRunner lr = loadAllocationLeaves.initDatabase(leaveRepository);
        assertNull(lr);
    }

    @Test
    @DisplayName("Test")
    void testLoadAllocationLeaveReverse(){
        Mockito.when(leaveRepository.findLeaveByRequestType(any())).thenReturn(new ArrayList<>());
        LoadAllocationLeaves loadAllocationLeaves = new LoadAllocationLeaves();
        CommandLineRunner lr = loadAllocationLeaves.initDatabase(leaveRepository);
        assertNotNull(lr);
    }

}
