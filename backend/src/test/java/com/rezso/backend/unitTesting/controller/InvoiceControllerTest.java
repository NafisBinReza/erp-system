package com.rezso.backend.unitTesting.controller;

import com.rezso.backend.controller.InvoiceController;
import com.rezso.backend.controller.LeaveController;
import com.rezso.backend.model.Invoice;
import com.rezso.backend.model.Leave;
import com.rezso.backend.repository.InvoiceRepository;
import com.rezso.backend.repository.LeaveRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

public class InvoiceControllerTest {
    @Mock
    InvoiceRepository invoiceRepository;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Testing all() method")
    public void testAll() throws ParseException {
        Mockito.when(invoiceRepository.findAll()).thenReturn(getInvoiceList());
        InvoiceController invoiceController = new InvoiceController(invoiceRepository);
        List<Invoice> invoiceList = invoiceController.all();
        assertNotNull(invoiceList);
        assertEquals(getInvoiceList(), invoiceList);
        assertTrue(invoiceList.containsAll(getInvoiceList()));
    }

    @Test
    @DisplayName("Testing getInvoice() method")
    public void testGetInvoice() throws ParseException {
        Mockito.when(invoiceRepository.findById(any())).thenReturn(Optional.of(new Invoice(1, "nafis", new SimpleDateFormat( "yyyyMMdd" ).parse( "20100520" ), "", "", new SimpleDateFormat( "yyyyMMdd" ).parse( "20100520" ), "", 0, 0, 0, 0, "")));
        InvoiceController invoiceController = new InvoiceController(invoiceRepository);
        Optional<Invoice> invoice = invoiceController.getInvoice(1);
        assertNotNull(invoice);
        assertEquals(Optional.of(new Invoice(1, "nafis", new SimpleDateFormat( "yyyyMMdd" ).parse( "20100520" ), "", "", new SimpleDateFormat( "yyyyMMdd" ).parse( "20100520" ), "", 0, 0, 0, 0, "")), invoice);
        assertTrue(invoice.equals(getInvoiceList().stream().findFirst()));
    }

    @Test
    @DisplayName("Testing newInvoice() method")
    public void testNewInvoice() throws ParseException {
        Mockito.when(invoiceRepository.save(any())).thenReturn(new Invoice(1, "nafis", new SimpleDateFormat( "yyyyMMdd" ).parse( "20100520" ), "", "", new SimpleDateFormat( "yyyyMMdd" ).parse( "20100520" ), "", 0, 0, 0, 0, ""));
        InvoiceController invoiceController = new InvoiceController(invoiceRepository);
        Invoice testInvoice = new Invoice(1, "nafis", new SimpleDateFormat( "yyyyMMdd" ).parse( "20100520" ), "", "", new SimpleDateFormat( "yyyyMMdd" ).parse( "20100520" ), "", 0, 0, 0, 0, "");
        Invoice invoice = invoiceController.newInvoice(testInvoice);
        assertNotNull(invoice);
        assertEquals(new Invoice(1, "nafis", new SimpleDateFormat( "yyyyMMdd" ).parse( "20100520" ), "", "", new SimpleDateFormat( "yyyyMMdd" ).parse( "20100520" ), "", 0, 0, 0, 0, ""), invoice);
    }

    @Test
    @DisplayName("Testing putInvoice() method")
    public void testPutInvoice() throws ParseException {
        Mockito.when(invoiceRepository.save(any())).thenReturn(new Invoice(1, "nafis", new SimpleDateFormat( "yyyyMMdd" ).parse( "20100520" ), "", "", new SimpleDateFormat( "yyyyMMdd" ).parse( "20100520" ), "", 0, 0, 0, 0, ""));
        InvoiceController invoiceController = new InvoiceController(invoiceRepository);
        Invoice testInvoice = new Invoice(1, "nafis", new SimpleDateFormat( "yyyyMMdd" ).parse( "20100520" ), "", "", new SimpleDateFormat( "yyyyMMdd" ).parse( "20100520" ), "", 0, 0, 0, 0, "");
        Invoice invoice = invoiceController.putInvoice(testInvoice);
        assertNotNull(invoice);
        assertEquals(new Invoice(1, "nafis", new SimpleDateFormat( "yyyyMMdd" ).parse( "20100520" ), "", "", new SimpleDateFormat( "yyyyMMdd" ).parse( "20100520" ), "", 0, 0, 0, 0, ""), invoice);
    }

    @Test
    @DisplayName("Testing deleteInvoice() method")
    public void testDeleteInvoice() throws ParseException {
        InvoiceController invoiceController = new InvoiceController(invoiceRepository);
        Mockito.when(invoiceRepository.findById(any())).thenReturn(Optional.of(new Invoice(1, "nafis", new SimpleDateFormat( "yyyyMMdd" ).parse( "20100520" ), "", "", new SimpleDateFormat( "yyyyMMdd" ).parse( "20100520" ), "", 0, 0, 0, 0, "")));
        String response = invoiceController.deleteInvoice(0);
        assertNotNull(response);
        assertEquals("Invoice Deleted", response);
    }

    public List<Invoice> getInvoiceList() throws ParseException {
        List<Invoice> invoiceList = new ArrayList<>();
        invoiceList.add(new Invoice(1, "nafis", new SimpleDateFormat( "yyyyMMdd" ).parse( "20100520" ), "", "", new SimpleDateFormat( "yyyyMMdd" ).parse( "20100520" ), "", 0, 0, 0, 0, ""));
        invoiceList.add(new Invoice(1, "grim", new SimpleDateFormat( "yyyyMMdd" ).parse( "20100520" ), "", "", new SimpleDateFormat( "yyyyMMdd" ).parse( "20100520" ), "", 0, 0, 0, 0, ""));

        return invoiceList;
    }

}
