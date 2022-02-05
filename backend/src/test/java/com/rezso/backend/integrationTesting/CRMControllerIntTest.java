package com.rezso.backend.integrationTesting;


import com.rezso.backend.model.CRM;
import com.rezso.backend.model.Invoice;
import com.rezso.backend.repository.InvoiceRepository;
import com.rezso.backend.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CRMControllerIntTest {

    @LocalServerPort
    int randomPort;

    @Autowired
    TestRestTemplate testRestTemplate;

    @MockBean
    UserRepository userRepository;

    @Test
    @DisplayName("Testing all() method")
    void testAllInt() throws Exception {
        final String baseUrl = "http://localhost:" + randomPort + "/crm/all";
        URI uri = new URI(baseUrl);

        Mockito.when(userRepository.findAll()).thenReturn(getCRMList());

        List response = testRestTemplate.getForObject(uri, List.class);

        System.out.println(response.get(0));
        assertNotNull(response);
    }

    @Test
    @DisplayName("Testing getUser() method")
    void testGetUserInt() throws Exception {
        final String baseUrl = "http://localhost:" + randomPort + "/crm/customer/2";
        URI uri = new URI(baseUrl);

        Mockito.when(userRepository.findById(any())).thenReturn(getCRMList().stream().findFirst());

        CRM response = testRestTemplate.getForObject(uri, CRM.class);

        assertNotNull(response);
        assertEquals("nafis", response.getName());
    }

    @Test
    @DisplayName("Testing newUser() method")
    void testNewUserInt() throws Exception {
        final String baseUrl = "http://localhost:" + randomPort + "/crm/customer";
        URI uri = new URI(baseUrl);

        CRM crm = new CRM(1, "nafis", "asd", "asd", "sgsd", "hjdasfg", "adf", "asd", "asd", "asd", "asd", "asd");
        Mockito.when(userRepository.save(any())).thenReturn(new CRM(1, "nafis", "asd", "asd", "sgsd", "hjdasfg", "adf", "asd", "asd", "asd", "asd", "asd"));

        CRM response = testRestTemplate.postForObject(uri, crm, CRM.class);

        assertNotNull(response);
        assertEquals("nafis", response.getName());
    }

    @Test
    @DisplayName("Testing putUser() method")
    void testPutUserInt() throws Exception {
        final String baseUrl = "http://localhost:" + randomPort + "/crm/customer";
        URI uri = new URI(baseUrl);

        HttpEntity<CRM> httpEntity = new HttpEntity<>(new CRM(1, "nafis", "asd", "asd", "sgsd", "hjdasfg", "adf", "asd", "asd", "asd", "asd", "asd"));
        Mockito.when(userRepository.save(any())).thenReturn(new CRM(1, "nafis", "asd", "asd", "sgsd", "hjdasfg", "adf", "asd", "asd", "asd", "asd", "asd"));

        ResponseEntity<CRM> response = testRestTemplate.exchange(uri, HttpMethod.PUT, httpEntity, CRM.class);

        System.out.println(response.getBody());
        assertNotNull(response);
        assertEquals("nafis", response.getBody().getName());
    }

    @Test
    @DisplayName("Testing deleteUser() method")
    void testDeleteUserInt() throws Exception {
        final String baseUrl = "http://localhost:" + randomPort + "/crm/customer/1";
        URI uri = new URI(baseUrl);

        HttpEntity<CRM> httpEntity = new HttpEntity<>(new CRM(1, "nafis", "asd", "asd", "sgsd", "hjdasfg", "adf", "asd", "asd", "asd", "asd", "asd"));

        ResponseEntity<String> response = testRestTemplate.exchange(uri, HttpMethod.DELETE, httpEntity, String.class);

        assertNotNull(response);
        assertEquals("User Deleted", response.getBody());
    }

public List<CRM> getCRMList() {
    List<CRM> crmList = new ArrayList<>();
    crmList.add(new CRM(1, "nafis", "asd", "asd", "sgsd", "hjdasfg", "adf", "asd", "asd", "asd", "asd", "asd"));
    crmList.add(new CRM(2, "grim", "asd", "asd", "sgsd", "hjdasfg", "adf", "asd", "asd", "asd", "asd", "asd"));

    return crmList;
}
}
