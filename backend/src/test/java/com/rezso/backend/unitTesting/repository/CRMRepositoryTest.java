package com.rezso.backend.unitTesting.repository;

import com.rezso.backend.model.CRM;
import com.rezso.backend.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class CRMRepositoryTest {

    @Mock
    UserRepository userRepository;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("User Repository Test")
    public void checkRepository() throws Exception{
        Mockito.when(userRepository.findAll()).thenReturn(getCRMList());
        List<CRM> crms = userRepository.findAll();
        verify(userRepository, times(1)).findAll();
        Assertions.assertNotNull(crms);
        Assertions.assertEquals("nafis", crms.stream().findFirst().get().getName());
    }

    @Test
    @DisplayName("Fail Test of User Repository")
    public void failCheck(){
        Mockito.when(userRepository.findAll()).thenThrow(new RuntimeException("Fail Case"));
        try {
            List<CRM> crmList = userRepository.findAll();
        }
        catch (RuntimeException exception){
            Assertions.assertEquals("Fail Case", exception.getMessage());
        }
    }

    public List<CRM> getCRMList() {
        List<CRM> crmList = new ArrayList<>();
        crmList.add(new CRM(1, "nafis", "asd", "asd", "sgsd", "hjdasfg", "adf", "asd", "asd", "asd", "asd", "asd"));
        crmList.add(new CRM(2, "grim", "asd", "asd", "sgsd", "hjdasfg", "adf", "asd", "asd", "asd", "asd", "asd"));

        return crmList;
    }

}
