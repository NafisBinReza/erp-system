package com.rezso.backend.unitTesting.controller;

import com.rezso.backend.controller.EmployeeController;
import com.rezso.backend.controller.LeaveController;
import com.rezso.backend.controller.RecruitmentController;
import com.rezso.backend.model.Employee;
import com.rezso.backend.model.Leave;
import com.rezso.backend.repository.EmployeeRepository;
import com.rezso.backend.repository.LeaveRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

public class LeaveControllerTest {

    @Mock
    LeaveRepository leaveRepository;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Testing all() method")
    public void testAll() throws ParseException {
        Mockito.when(leaveRepository.findAll()).thenReturn(getLeaveList());
        LeaveController leaveController = new LeaveController(leaveRepository);
        List<Leave> leaveList = leaveController.all();
        assertNotNull(leaveList);
        assertEquals(getLeaveList(), leaveList);
        assertTrue(leaveList.containsAll(getLeaveList()));
    }

    @Test
    @DisplayName("Testing getLeave() method")
    public void testGetLeave() throws ParseException {
        Mockito.when(leaveRepository.findById(any())).thenReturn(Optional.of(new Leave(1, 1, "nafis", "", "", 3, new SimpleDateFormat( "yyyyMMdd" ).parse( "20100520" ), new SimpleDateFormat( "yyyyMMdd" ).parse( "20100520" ), "", "", "", "")));
        LeaveController leaveController = new LeaveController(leaveRepository);
        Optional<Leave> leave = leaveController.getLeave(1);
        assertNotNull(leave);
        assertEquals(Optional.of(new Leave(1, 1, "nafis", "", "", 3, new SimpleDateFormat( "yyyyMMdd" ).parse( "20100520" ), new SimpleDateFormat( "yyyyMMdd" ).parse( "20100520" ), "", "", "", "")), leave);
        assertTrue(leave.equals(getLeaveList().stream().findFirst()));
    }

    @Test
    @DisplayName("Testing stat() method")
    public void testStat() throws ParseException {
        LeaveController leaveController = new LeaveController(leaveRepository);
        Map<String, Integer> leave = leaveController.stat();
        assertNotNull(leave);
        assertEquals(20, leave.get("legalLeaves"));
    }

    @Test
    @DisplayName("Testing newLeave() method")
    public void testNewLeave() throws ParseException {
        Mockito.when(leaveRepository.save(any())).thenReturn(new Leave(1, 1, "nafis", "", "", 3, new SimpleDateFormat( "yyyyMMdd" ).parse( "20100520" ), new SimpleDateFormat( "yyyyMMdd" ).parse( "20100520" ), "", "", "", ""));
        LeaveController leaveController = new LeaveController(leaveRepository);
        Leave testLeave = new Leave(1, 1, "nafis", "", "", 3, new SimpleDateFormat( "yyyyMMdd" ).parse( "20100520" ), new SimpleDateFormat( "yyyyMMdd" ).parse( "20100520" ), "", "", "", "");
        Leave leave = leaveController.newLeave(testLeave);
        assertNotNull(leave);
        assertEquals(new Leave(1, 1, "nafis", "", "", 3, new SimpleDateFormat( "yyyyMMdd" ).parse( "20100520" ), new SimpleDateFormat( "yyyyMMdd" ).parse( "20100520" ), "", "", "", ""), leave);
    }

    @Test
    @DisplayName("Testing putLeave() method")
    public void testPutLeave() throws ParseException {
        Mockito.when(leaveRepository.save(any())).thenReturn(new Leave(1, 1, "grim", "", "", 3, new SimpleDateFormat( "yyyyMMdd" ).parse( "20100520" ), new SimpleDateFormat( "yyyyMMdd" ).parse( "20100520" ), "", "", "", ""));
        LeaveController leaveController = new LeaveController(leaveRepository);
        Leave testLeave = new Leave(1, 1, "nafis", "", "", 3, new SimpleDateFormat( "yyyyMMdd" ).parse( "20100520" ), new SimpleDateFormat( "yyyyMMdd" ).parse( "20100520" ), "", "", "", "");
        Leave leave = leaveController.putLeave(testLeave);
        assertNotNull(leave);
        assertEquals(new Leave(1, 1, "grim", "", "", 3, new SimpleDateFormat( "yyyyMMdd" ).parse( "20100520" ), new SimpleDateFormat( "yyyyMMdd" ).parse( "20100520" ), "", "", "", ""), leave);
    }

    @Test
    @DisplayName("Testing deleteLeave() method")
    public void testDeleteLeave() throws ParseException {
        LeaveController leaveController = new LeaveController(leaveRepository);
        Mockito.when(leaveRepository.findById(any())).thenReturn(Optional.of(new Leave(1, 1, "nafis", "", "", 3, new SimpleDateFormat("yyyyMMdd").parse("20100520"), new SimpleDateFormat("yyyyMMdd").parse("20100520"), "", "", "", "")));
        String response = leaveController.deleteLeave(0);
        assertNotNull(response);
        assertEquals("Leave Entry Deleted", response);
    }

    public List<Leave> getLeaveList() throws ParseException {
        List<Leave> leaveList = new ArrayList<>();
        leaveList.add(new Leave(1, 1, "nafis", "", "", 3, new SimpleDateFormat( "yyyyMMdd" ).parse( "20100520" ), new SimpleDateFormat( "yyyyMMdd" ).parse( "20100520" ), "", "", "", ""));
        leaveList.add(new Leave(1, 1, "grim", "", "", 3, new SimpleDateFormat( "yyyyMMdd" ).parse( "20100520" ), new SimpleDateFormat( "yyyyMMdd" ).parse( "20100520" ), "", "", "", ""));

        return leaveList;
    }
}
