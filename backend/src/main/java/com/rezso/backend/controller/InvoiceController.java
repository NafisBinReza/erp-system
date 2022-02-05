package com.rezso.backend.controller;

import com.rezso.backend.model.CRM;
import com.rezso.backend.model.Invoice;
import com.rezso.backend.repository.InvoiceRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/invoice")
@CrossOrigin(origins = "http://localhost:3000")
public class InvoiceController {

    private final InvoiceRepository invoiceRepository;

    public InvoiceController(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    @GetMapping("/all")
    public List<Invoice> all() {
        return invoiceRepository.findAll();
    }

    @GetMapping("/inv/{id}")
    public Optional<Invoice> getInvoice(@PathVariable Integer id){
        return invoiceRepository.findById(id);
    }

    @PostMapping("/inv")
    public Invoice newInvoice(@RequestBody Invoice newInvoice) {
        newInvoice.setTotal(newInvoice.getPrice() * newInvoice.getQuantity());
        return invoiceRepository.save(newInvoice);
    }

    @PutMapping("/inv")
    public Invoice putInvoice(@RequestBody Invoice newInvoice){
        Integer id = newInvoice.getId();
        return invoiceRepository.findById(id)
                .map(invoice -> {
                    invoice.setCustomer(newInvoice.getCustomer());
                    invoice.setDate(newInvoice.getDate());
                    invoice.setAmountDue(newInvoice.getAmountDue());
                    invoice.setDocument(newInvoice.getDocument());
                    invoice.setDueDate(newInvoice.getDueDate());
                    invoice.setNumber(newInvoice.getNumber());
                    invoice.setSalesperson(newInvoice.getSalesperson());
                    invoice.setStatus(newInvoice.getStatus());
                    invoice.setPrice(newInvoice.getPrice());
                    invoice.setQuantity(newInvoice.getQuantity());
                    invoice.setTotal(newInvoice.getPrice() * newInvoice.getQuantity());

                    return invoiceRepository.save(invoice);
                })
                .orElseGet(() -> {
                    newInvoice.setId(id);
                    return invoiceRepository.save(newInvoice);
                });
    }

    @DeleteMapping("/inv/{id}")
    public String deleteInvoice(@PathVariable Integer id){
        invoiceRepository.deleteById(id);
        return "Invoice Deleted";
    }


}

