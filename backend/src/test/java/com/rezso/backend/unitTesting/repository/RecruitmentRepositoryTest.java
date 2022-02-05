package com.rezso.backend.unitTesting.repository;

import com.rezso.backend.model.Recruitment;
import com.rezso.backend.repository.RecruitmentRepository;
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

public class RecruitmentRepositoryTest {

    @Mock
    RecruitmentRepository recruitmentRepository;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Recruitment Repository Test")
    public void checkRepository() throws Exception{
        Mockito.when(recruitmentRepository.findAll()).thenReturn(getRecruitmentList());
        List<Recruitment> recruitments = recruitmentRepository.findAll();
        verify(recruitmentRepository, times(1)).findAll();
        Assertions.assertNotNull(recruitments);
        Assertions.assertEquals("nafis", recruitments.stream().findFirst().get().getName());
    }

    @Test
    @DisplayName("Fail Test of Recruitment Repository")
    public void failCheck(){
        Mockito.when(recruitmentRepository.findAll()).thenThrow(new RuntimeException("Fail Case"));
        try {
            List<Recruitment> recruitmentList = recruitmentRepository.findAll();
        }
        catch (RuntimeException exception){
            Assertions.assertEquals("Fail Case", exception.getMessage());
        }
    }

    public List<Recruitment> getRecruitmentList() {
        List<Recruitment> recruitmentList = new ArrayList<>();
        recruitmentList.add(new Recruitment(1, "nafis", Date.from(Instant.now()), "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""));
        recruitmentList.add(new Recruitment(2, "grim", Date.from(Instant.now()), "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""));

        return recruitmentList;
    }

}
