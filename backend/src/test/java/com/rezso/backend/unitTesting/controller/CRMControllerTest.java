package com.rezso.backend.unitTesting.controller;

import com.rezso.backend.controller.CRMController;
import com.rezso.backend.model.CRM;
import com.rezso.backend.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CRMControllerTest {

    @Mock
    UserRepository userRepository;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Testing all() method")
    public void testAll(){
        Mockito.when(userRepository.findAll()).thenReturn(getCRMList());
        CRMController crmController = new CRMController(userRepository);
        List<CRM> crmList = crmController.all();
        assertNotNull(crmList);
        assertEquals(getCRMList(), crmList);
        assertTrue(crmList.containsAll(getCRMList()));
    }

    @Test
    @DisplayName("Testing getUser() method")
    public void testGetuser(){
        Mockito.when(userRepository.findById(1)).thenReturn(getCRMList().stream().findFirst());
        CRMController crmController = new CRMController(userRepository);
        Optional<CRM> crm = crmController.getUser(1);
        assertNotNull(crm);
        assertEquals(getCRMList().stream().findFirst(), crm);
        assertTrue(crm.equals(getCRMList().stream().findFirst()));
    }

    @Test
    @DisplayName("Testing newUser() method")
    public void testNewuser(){
        Mockito.when(userRepository.save(any())).thenReturn(new CRM(1, "nafis", "asd", "asd", "sgsd", "hjdasfg", "adf", "asd", "asd", "asd", "asd", "asd"));
        CRMController crmController = new CRMController(userRepository);
        CRM testCrm = new CRM(1, "nafis", "asd", "asd", "sgsd", "hjdasfg", "adf", "asd", "asd", "asd", "asd", "asd");
        CRM crm = crmController.newUser(testCrm);
        assertNotNull(crm);
        assertEquals(new CRM(1, "nafis", "asd", "asd", "sgsd", "hjdasfg", "adf", "asd", "asd", "asd", "asd", "asd"), crm);
        assertTrue(crm.equals(new CRM(1, "nafis", "asd", "asd", "sgsd", "hjdasfg", "adf", "asd", "asd", "asd", "asd", "asd")));
    }

    @Test
    @DisplayName("Testing putUser() method")
    public void putUser(){
        Mockito.when(userRepository.save(any())).thenReturn(new CRM(1, "nafis", "asd", "asd", "sgsd", "hjdasfg", "adf", "asd", "asd", "asd", "asd", "asd"));
        CRMController crmController = new CRMController(userRepository);
        CRM testCrm = new CRM(1, "nafis", "asd", "asd", "sgsd", "hjdasfg", "adf", "asd", "asd", "asd", "asd", "asd");
        CRM crm = crmController.putUser(testCrm);
        assertNotNull(crm);
        assertEquals(new CRM(1, "nafis", "asd", "asd", "sgsd", "hjdasfg", "adf", "asd", "asd", "asd", "asd", "asd"), crm);
        assertTrue(crm.equals(new CRM(1, "nafis", "asd", "asd", "sgsd", "hjdasfg", "adf", "asd", "asd", "asd", "asd", "asd")));
    }

    @Test
    @DisplayName("Testing deleteUser() method")
    public void deleteUser(){
        CRMController crmController = new CRMController(userRepository);
        String crm = crmController.deleteUser(1);
        assertNotNull(crm);
        assertEquals("User Deleted", crm);
    }



    public List<CRM> getCRMList() {
        List<CRM> crmList = new ArrayList<>();
        crmList.add(new CRM(1, "nafis", "asd", "asd", "sgsd", "hjdasfg", "adf", "asd", "asd", "asd", "asd", "asd"));
        crmList.add(new CRM(2, "grim", "asd", "asd", "sgsd", "hjdasfg", "adf", "asd", "asd", "asd", "asd", "asd"));

        return crmList;
    }
}
