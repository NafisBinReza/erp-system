package com.rezso.backend.unitTesting.repository;

import com.rezso.backend.model.Leave;
import com.rezso.backend.repository.LeaveRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class LeaveRepositoryTest {

    @Mock
    LeaveRepository leaveRepository;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Leave Repository Test")
    public void checkRepository() throws Exception{
        Mockito.when(leaveRepository.findAll()).thenReturn(getLeaveList());
        List<Leave> leaves = leaveRepository.findAll();
        verify(leaveRepository, times(1)).findAll();
        Assertions.assertNotNull(leaves);
        Assertions.assertEquals("nafis", leaves.stream().findFirst().get().getEmployee());
    }

    @Test
    @DisplayName("Fail Test of Leave Repository")
    public void failCheck(){
        Mockito.when(leaveRepository.findAll()).thenThrow(new RuntimeException("Fail Case"));
        try {
            List<Leave> leaveList = leaveRepository.findAll();
        }
        catch (RuntimeException exception){
            Assertions.assertEquals("Fail Case", exception.getMessage());
        }
    }

    public List<Leave> getLeaveList() {
        List<Leave> leaveList = new ArrayList<>();
        leaveList.add(new Leave(1, 13, "nafis", "asd", "asd", 2, Date.from(Instant.now()), Date.from(Instant.now()), "asd", "asd", "asd", "asd"));
        leaveList.add(new Leave(2, 14, "grim", "asd", "asd", 2, Date.from(Instant.now()), Date.from(Instant.now()), "asd", "asd", "asd", "asd"));

        return leaveList;
    }

}
