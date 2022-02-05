package com.rezso.backend.integrationTesting;

import com.rezso.backend.model.Employee;
import com.rezso.backend.model.Leave;
import com.rezso.backend.repository.EmployeeRepository;
import com.rezso.backend.repository.LeaveRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
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
import java.sql.Date;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LeaveControllerIntTest {
    @LocalServerPort
    int randomPort;

    @Autowired
    TestRestTemplate testRestTemplate;

    @MockBean
    LeaveRepository leaveRepository;

    @Test
    @DisplayName("Testing all() method")
    void testAllInt() throws Exception {
        final String baseUrl = "http://localhost:" + randomPort + "/leave/all";
        URI uri = new URI(baseUrl);

        Mockito.when(leaveRepository.findAll()).thenReturn(getLeaveList());

        List response = testRestTemplate.getForObject(uri, List.class);

        System.out.println(response.get(0));
        assertNotNull(response);
    }

    @Test
    @DisplayName("Testing getLeave() method")
    void testGetLeaveInt() throws Exception {
        final String baseUrl = "http://localhost:" + randomPort + "/leave/lv/2";
        URI uri = new URI(baseUrl);

        Mockito.when(leaveRepository.findById(any())).thenReturn(Optional.of(new Leave(1, 1, "nafis", "", "", 3, Date.from(Instant.now()), Date.from(Instant.now()), "", "", "", "")));

        Leave response = testRestTemplate.getForObject(uri, Leave.class);

        assertNotNull(response);
        assertEquals("nafis", response.getEmployee());
    }

    @Test
    @DisplayName("Testing stat() method")
    void testStatInt() throws Exception {
        final String baseUrl = "http://localhost:" + randomPort + "/leave/stat";
        URI uri = new URI(baseUrl);

        Map response = testRestTemplate.getForObject(uri, Map.class);

        assertNotNull(response);
        assertEquals(20, response.get("legalLeaves"));
    }

    @Test
    @DisplayName("Testing newLeave() method")
    void testNewLeaveInt() throws Exception {
        final String baseUrl = "http://localhost:" + randomPort + "/leave/lv";
        URI uri = new URI(baseUrl);

        Leave leave = new Leave(1, 1, "nafis", "", "", 3, Date.from(Instant.now()), Date.from(Instant.now()), "legal", "", "", "");
        Mockito.when(leaveRepository.save(any())).thenReturn(new Leave(1, 1, "nafis", "", "", 3, Date.from(Instant.now()), Date.from(Instant.now()), "legal", "", "", ""));

        Leave response = testRestTemplate.postForObject(uri, leave, Leave.class);

        assertNotNull(response);
        assertEquals("nafis", response.getEmployee());
    }

    @Test
    @DisplayName("Testing putLeave() method")
    void testPutLeaveInt() throws Exception {
        final String baseUrl = "http://localhost:" + randomPort + "/leave/lv";
        URI uri = new URI(baseUrl);

        HttpEntity<Leave> httpEntity = new HttpEntity<>(new Leave(1, 1, "nafis", "", "", 3, Date.from(Instant.now()), Date.from(Instant.now()), "", "", "", ""));
        Mockito.when(leaveRepository.save(any())).thenReturn(new Leave(1, 1, "nafis", "", "", 3, Date.from(Instant.now()), Date.from(Instant.now()), "", "", "", ""));

        ResponseEntity<Leave> response = testRestTemplate.exchange(uri, HttpMethod.PUT, httpEntity, Leave.class);

        System.out.println(response.getBody());
        assertNotNull(response);
        assertEquals("nafis", response.getBody().getEmployee());
    }

    @Test
    @DisplayName("Testing deleteLeave() method")
    void testDeleteLeaveInt() throws Exception {
        final String baseUrl = "http://localhost:" + randomPort + "/leave/lv/1";
        URI uri = new URI(baseUrl);

        Mockito.when(leaveRepository.findById(any())).thenReturn(Optional.of(new Leave(1, 1, "nafis", "", "", 3, Date.from(Instant.now()), Date.from(Instant.now()), "", "", "", "")));
        HttpEntity<Leave> httpEntity = new HttpEntity<>(new Leave(1, 1, "nafis", "", "", 3, Date.from(Instant.now()), Date.from(Instant.now()), "", "", "", ""));

        ResponseEntity<String> response = testRestTemplate.exchange(uri, HttpMethod.DELETE, httpEntity, String.class);

        assertNotNull(response);
        assertEquals("Leave Entry Deleted", response.getBody());
    }

    public List<Leave> getLeaveList() {
        List<Leave> leaveList = new ArrayList<>();
        leaveList.add(new Leave(1, 1, "nafis", "", "", 3, Date.from(Instant.now()), Date.from(Instant.now()), "", "", "", ""));
        leaveList.add(new Leave(1, 1, "grim", "", "", 3, Date.from(Instant.now()), Date.from(Instant.now()), "", "", "", ""));

        return leaveList;
    }
}
