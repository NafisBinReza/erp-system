package com.rezso.backend.unitTesting.repository;

import com.rezso.backend.model.Invoice;
import com.rezso.backend.repository.InvoiceRepository;
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

import static org.apache.tomcat.util.http.FastHttpDateFormat.parseDate;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class InvoiceRepositoryTest {
    @Mock
    InvoiceRepository invoiceRepository;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Invoice Repository Test")
    public void checkRepository() throws Exception{
        Mockito.when(invoiceRepository.findAll()).thenReturn(getInvoiceList());
        List<Invoice> invoices = invoiceRepository.findAll();
        verify(invoiceRepository, times(1)).findAll();
        Assertions.assertNotNull(invoices);
        Assertions.assertEquals("nafis", invoices.stream().findFirst().get().getCustomer());
    }

    @Test
    @DisplayName("Fail Test of Invoice Repository")
    public void failCheck(){
        Mockito.when(invoiceRepository.findAll()).thenThrow(new RuntimeException("Fail Case"));
        try {
            List<Invoice> invoiceList = invoiceRepository.findAll();
        }
        catch (RuntimeException exception){
            Assertions.assertEquals("Fail Case", exception.getMessage());
        }
    }

    public List<Invoice> getInvoiceList() {
        List<Invoice> invoiceList = new ArrayList<>();
        invoiceList.add(new Invoice(1, "nafis", Date.from(Instant.now()), "asd", "asd", Date.from(Instant.now()), "asd", 1, 1, 1, 1, "asd"));

        return invoiceList;
    }
}
