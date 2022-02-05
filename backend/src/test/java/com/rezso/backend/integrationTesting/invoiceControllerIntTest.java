package com.rezso.backend.integrationTesting;


import com.rezso.backend.model.Employee;
import com.rezso.backend.model.Invoice;
import com.rezso.backend.repository.EmployeeRepository;
import com.rezso.backend.repository.InvoiceRepository;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class invoiceControllerIntTest {
    @LocalServerPort
    int randomPort;

    @Autowired
    TestRestTemplate testRestTemplate;

    @MockBean
    InvoiceRepository invoiceRepository;

    @Test
    @DisplayName("Testing all() method")
    void testAllInt() throws Exception {
        final String baseUrl = "http://localhost:" + randomPort + "/invoice/all";
        URI uri = new URI(baseUrl);

        Mockito.when(invoiceRepository.findAll()).thenReturn(getInvoiceList());

        List response = testRestTemplate.getForObject(uri, List.class);

        System.out.println(response.get(0));
        assertNotNull(response);
    }

    @Test
    @DisplayName("Testing getInvoice() method")
    void testGetInvoiceInt() throws Exception {
        final String baseUrl = "http://localhost:" + randomPort + "/invoice/inv/2";
        URI uri = new URI(baseUrl);

        Mockito.when(invoiceRepository.findById(any())).thenReturn(getInvoiceList().stream().findFirst());

        Invoice response = testRestTemplate.getForObject(uri, Invoice.class);

        assertNotNull(response);
        assertEquals("nafis", response.getCustomer());
    }

    @Test
    @DisplayName("Testing newInvoice() method")
    void testNewInvoiceInt() throws Exception {
        final String baseUrl = "http://localhost:" + randomPort + "/invoice/inv";
        URI uri = new URI(baseUrl);

        Invoice invoice = new Invoice(1, "grim", new SimpleDateFormat( "yyyyMMdd" ).parse( "20100520" ), "", "", new SimpleDateFormat( "yyyyMMdd" ).parse( "20100520" ), "", 0, 0, 0, 0, "");
        Mockito.when(invoiceRepository.save(any())).thenReturn(new Invoice(1, "grim", new SimpleDateFormat( "yyyyMMdd" ).parse( "20100520" ), "", "", new SimpleDateFormat( "yyyyMMdd" ).parse( "20100520" ), "", 0, 0, 0, 0, ""));

        Invoice response = testRestTemplate.postForObject(uri, invoice, Invoice.class);

        assertNotNull(response);
        assertEquals("grim", response.getCustomer());
    }

    @Test
    @DisplayName("Testing putInvoice() method")
    void testPutInvoiceInt() throws Exception {
        final String baseUrl = "http://localhost:" + randomPort + "/invoice/inv";
        URI uri = new URI(baseUrl);

        HttpEntity<Invoice> httpEntity = new HttpEntity<>(new Invoice(1, "grim", new SimpleDateFormat( "yyyyMMdd" ).parse( "20100520" ), "", "", new SimpleDateFormat( "yyyyMMdd" ).parse( "20100520" ), "", 0, 0, 0, 0, ""));
        Mockito.when(invoiceRepository.save(any())).thenReturn(new Invoice(1, "grim", new SimpleDateFormat( "yyyyMMdd" ).parse( "20100520" ), "", "", new SimpleDateFormat( "yyyyMMdd" ).parse( "20100520" ), "", 0, 0, 0, 0, ""));

        ResponseEntity<Invoice> response = testRestTemplate.exchange(uri, HttpMethod.PUT, httpEntity, Invoice.class);

        System.out.println(response.getBody());
        assertNotNull(response);
        assertEquals("grim", response.getBody().getCustomer());
    }

    @Test
    @DisplayName("Testing deleteInvoice() method")
    void testDeleteInvoiceInt() throws Exception {
        final String baseUrl = "http://localhost:" + randomPort + "/invoice/inv/1";
        URI uri = new URI(baseUrl);

        HttpEntity<Invoice> httpEntity = new HttpEntity<>(new Invoice(1, "grim", new SimpleDateFormat( "yyyyMMdd" ).parse( "20100520" ), "", "", new SimpleDateFormat( "yyyyMMdd" ).parse( "20100520" ), "", 0, 0, 0, 0, ""));

        ResponseEntity<String> response = testRestTemplate.exchange(uri, HttpMethod.DELETE, httpEntity, String.class);

        assertNotNull(response);
        assertEquals("Invoice Deleted", response.getBody());
    }

    public List<Invoice> getInvoiceList() throws ParseException {
        List<Invoice> invoiceList = new ArrayList<>();
        invoiceList.add(new Invoice(1, "nafis", new SimpleDateFormat( "yyyyMMdd" ).parse( "20100520" ), "", "", new SimpleDateFormat( "yyyyMMdd" ).parse( "20100520" ), "", 0, 0, 0, 0, ""));
        invoiceList.add(new Invoice(1, "grim", new SimpleDateFormat( "yyyyMMdd" ).parse( "20100520" ), "", "", new SimpleDateFormat( "yyyyMMdd" ).parse( "20100520" ), "", 0, 0, 0, 0, ""));

        return invoiceList;
    }

}
