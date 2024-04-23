package uk.ac.leedsbeckett.albertarkaa.libraryportal.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uk.ac.leedsbeckett.albertarkaa.libraryportal.dto.request.invoice.CreateInvoiceRequest;
import uk.ac.leedsbeckett.albertarkaa.libraryportal.dto.response.ControllerResponse;
import uk.ac.leedsbeckett.albertarkaa.libraryportal.dto.response.invoice.CreateInvoiceResponse;
import uk.ac.leedsbeckett.albertarkaa.libraryportal.service.InvoiceService;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/invoice")
@RequiredArgsConstructor
public class InvoiceController {

    private final InvoiceService invoiceService;

    @PostMapping("/create")
    public ResponseEntity<ControllerResponse<uk.ac.leedsbeckett.albertarkaa.libraryportal.dto.response.invoice.CreateInvoiceResponse>> createInvoice(
            @RequestBody CreateInvoiceRequest createInvoiceRequest) {
        ControllerResponse<uk.ac.leedsbeckett.albertarkaa.libraryportal.dto.response.invoice.CreateInvoiceResponse> response = invoiceService.createInvoice(createInvoiceRequest);

        if (response.isSuccess()) return ResponseEntity.ok(response);
        else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);

    }

    @PutMapping("/pay/{reference}")
    public ResponseEntity<ControllerResponse<uk.ac.leedsbeckett.albertarkaa.libraryportal.dto.response.invoice.CreateInvoiceResponse>> payInvoice(
            @PathVariable String reference) {
        ControllerResponse<CreateInvoiceResponse> response = invoiceService.payInvoice(reference);

        if (response.isSuccess()) return ResponseEntity.ok(response);
        else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @PutMapping("/cancel/{reference}")
    public ResponseEntity<ControllerResponse<uk.ac.leedsbeckett.albertarkaa.libraryportal.dto.response.invoice.CreateInvoiceResponse>> cancelInvoice(
            @PathVariable String reference) {
        ControllerResponse<CreateInvoiceResponse> response = invoiceService.cancelInvoice(reference);

        if (response.isSuccess()) return ResponseEntity.ok(response);
        else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @GetMapping("/get/{reference}")
    public ResponseEntity<ControllerResponse<uk.ac.leedsbeckett.albertarkaa.libraryportal.dto.response.invoice.CreateInvoiceResponse>> getInvoice(
            @PathVariable String reference) {
        ControllerResponse<CreateInvoiceResponse> response = invoiceService.getInvoice(reference);

        if (response.isSuccess()) return ResponseEntity.ok(response);
        else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @GetMapping("/get/student/{studentId}")
    public ResponseEntity<ControllerResponse<List<uk.ac.leedsbeckett.albertarkaa.libraryportal.dto.response.invoice.CreateInvoiceResponse>>> getInvoicesByStudentId(
            @PathVariable String studentId) {
        ControllerResponse<List<CreateInvoiceResponse>> response = invoiceService.getInvoicesByStudentId(studentId);

        if (response.isSuccess()) return ResponseEntity.ok(response);
        else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @GetMapping("/get/all")
    public ResponseEntity<ControllerResponse<List<uk.ac.leedsbeckett.albertarkaa.libraryportal.dto.response.invoice.CreateInvoiceResponse>>> getAllInvoices() {
        ControllerResponse<List<CreateInvoiceResponse>> response = invoiceService.getInvoices();

        if (response.isSuccess()) return ResponseEntity.ok(response);
        else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

}
