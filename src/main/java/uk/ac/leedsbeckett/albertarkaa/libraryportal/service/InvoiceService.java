package uk.ac.leedsbeckett.albertarkaa.libraryportal.service;

import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import uk.ac.leedsbeckett.albertarkaa.libraryportal.dto.request.invoice.CreateInvoiceRequest;
import uk.ac.leedsbeckett.albertarkaa.libraryportal.dto.response.ControllerResponse;
import uk.ac.leedsbeckett.albertarkaa.libraryportal.dto.response.invoice.CreateInvoiceResponse;
import uk.ac.leedsbeckett.albertarkaa.libraryportal.dto.response.invoice.Link;
import uk.ac.leedsbeckett.albertarkaa.libraryportal.dto.response.invoice.Links;
import uk.ac.leedsbeckett.albertarkaa.libraryportal.model.AccountModel;
import uk.ac.leedsbeckett.albertarkaa.libraryportal.model.InvoiceModel;
import uk.ac.leedsbeckett.albertarkaa.libraryportal.repository.AccountRepository;
import uk.ac.leedsbeckett.albertarkaa.libraryportal.repository.InvoiceRepository;
import uk.ac.leedsbeckett.albertarkaa.libraryportal.util.Status;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InvoiceService {

    private static final Logger logger = LoggerFactory.getLogger(InvoiceService.class);
    private final InvoiceRepository invoiceRepository;
    private final AccountRepository accountRepository;
    @Value("${AppURL}")
    private String AppURL;

    public ControllerResponse<CreateInvoiceResponse> createInvoice(CreateInvoiceRequest createInvoiceRequest) {
        try {

            if (createInvoiceRequest.getAmount() <= 0)
                return new ControllerResponse<>(false, "Amount must be greater than 0", null);
            if (createInvoiceRequest.getStudentId() == null || createInvoiceRequest.getStudentId().isEmpty() || createInvoiceRequest.getStudentId().isBlank())
                return new ControllerResponse<>(false, "StudentId is required", null);

            Faker faker = new Faker();
            InvoiceModel invoice = InvoiceModel.builder()
                    .reference(faker.regexify("[0-9A-Z]{8}"))
                    .amount(createInvoiceRequest.getAmount())
                    .dueDate(createInvoiceRequest.getDueDate())
                    .type(createInvoiceRequest.getType())
                    .status(Status.OUTSTANDING)
                    .studentId(createInvoiceRequest.getStudentId())
                    .build();

            invoiceRepository.save(invoice);

            AccountModel account = accountRepository.findByStudentId(invoice.getStudentId());

            account.setHasOutstandingBalance(true);
            accountRepository.save(account);

            CreateInvoiceResponse createInvoiceResponse = CreateInvoiceResponse.builder()
                    .reference(invoice.getReference())
                    .amount(invoice.getAmount())
                    .dueDate(invoice.getDueDate())
                    .type(String.valueOf(invoice.getType()))
                    .status(String.valueOf(invoice.getStatus()))
                    .studentId(invoice.getStudentId())
                    .links(
                            Links.builder()
                                    .cancel(Link.builder()
                                            .href(AppURL + "/api/invoice/cancel/" + invoice.getReference())
                                            .build())
                                    .pay(Link.builder()
                                            .href(AppURL + "/api/invoice/pay/" + invoice.getReference())
                                            .build())
                                    .build()
                    )
                    .build();

            return new ControllerResponse<>(true, null, createInvoiceResponse);

        } catch (Exception e) {
            logger.error("An error occurred", e);
            return new ControllerResponse<>(false, "An unexpected error occurred while processing your request. Please try again later.", null);
        }
    }

    public ControllerResponse<CreateInvoiceResponse> cancelInvoice(String reference) {
        try {
            InvoiceModel invoice = invoiceRepository.findByReference(reference);
            if (invoice == null)
                return new ControllerResponse<>(false, "Invoice not found", null);

            if (invoice.getStatus() == Status.PAID)
                return new ControllerResponse<>(false, "Invoice has already been paid", null);

            invoice.setStatus(Status.CANCELLED);
            invoice.setUpdatedAt(java.time.LocalDateTime.now());
            invoiceRepository.save(invoice);


            AccountModel account = accountRepository.findByStudentId(invoice.getStudentId());

            if (invoiceRepository.countByStudentIdAndStatus(invoice.getStudentId(), Status.OUTSTANDING) == 0){
                account.setHasOutstandingBalance(false);
                accountRepository.save(account);
            }

            CreateInvoiceResponse createInvoiceResponse = CreateInvoiceResponse.builder()
                    .reference(invoice.getReference())
                    .amount(invoice.getAmount())
                    .dueDate(invoice.getDueDate())
                    .type(String.valueOf(invoice.getType()))
                    .status(String.valueOf(invoice.getStatus()))
                    .studentId(invoice.getStudentId())
                    .build();

            return new ControllerResponse<>(true, null, createInvoiceResponse);

        } catch (Exception e) {
            logger.error("An error occurred", e);
            return new ControllerResponse<>(false, "An unexpected error occurred while processing your request. Please try again later.", null);
        }
    }

    public ControllerResponse<CreateInvoiceResponse> payInvoice(String reference) {
        try {
            InvoiceModel invoice = invoiceRepository.findByReference(reference);
            if (invoice == null)
                return new ControllerResponse<>(false, "Invoice not found", null);

            if (invoice.getStatus() == Status.PAID)
                return new ControllerResponse<>(false, "Invoice has already been paid", null);

            invoice.setStatus(Status.PAID);
            invoice.setUpdatedAt(java.time.LocalDateTime.now());
            invoiceRepository.save(invoice);


            AccountModel account = accountRepository.findByStudentId(invoice.getStudentId());


            if (invoiceRepository.countByStudentIdAndStatus(invoice.getStudentId(), Status.OUTSTANDING) == 0){
                account.setHasOutstandingBalance(false);
                accountRepository.save(account);
            }

            CreateInvoiceResponse createInvoiceResponse = CreateInvoiceResponse.builder()
                    .reference(invoice.getReference())
                    .amount(invoice.getAmount())
                    .dueDate(invoice.getDueDate())
                    .type(String.valueOf(invoice.getType()))
                    .status(String.valueOf(invoice.getStatus()))
                    .studentId(invoice.getStudentId())
                    .build();

            return new ControllerResponse<>(true, null, createInvoiceResponse);

        } catch (Exception e) {
            logger.error("An error occurred", e);
            return new ControllerResponse<>(false, "An unexpected error occurred while processing your request. Please try again later.", null);
        }
    }

    public ControllerResponse<CreateInvoiceResponse> getInvoice(String reference) {
        try {
            InvoiceModel invoice = invoiceRepository.findByReference(reference);
            if (invoice == null)
                return new ControllerResponse<>(false, "Invoice not found", null);

            CreateInvoiceResponse createInvoiceResponse = CreateInvoiceResponse.builder()
                    .reference(invoice.getReference())
                    .amount(invoice.getAmount())
                    .dueDate(invoice.getDueDate())
                    .type(String.valueOf(invoice.getType()))
                    .status(String.valueOf(invoice.getStatus()))
                    .studentId(invoice.getStudentId())
                    .links(
                            invoice.getStatus() == Status.OUTSTANDING ?
                                    Links.builder()
                                            .cancel(Link.builder()
                                                    .href(AppURL + "/api/invoice/cancel/" + invoice.getReference())
                                                    .build())
                                            .pay(Link.builder()
                                                    .href(AppURL + "/api/invoice/pay/" + invoice.getReference())
                                                    .build())
                                            .build() : null
                    )
                    .build();

            return new ControllerResponse<>(true, null, createInvoiceResponse);

        } catch (Exception e) {
            logger.error("An error occurred", e);
            return new ControllerResponse<>(false, "An unexpected error occurred while processing your request. Please try again later.", null);
        }
    }

    public ControllerResponse<List<CreateInvoiceResponse>> getInvoices() {
        try {
            List<InvoiceModel> invoices = invoiceRepository.findAll();
            if (invoices.isEmpty())
                return new ControllerResponse<>(false, "No invoices found", null);

            List<CreateInvoiceResponse> createInvoiceResponses = new java.util.ArrayList<>();
            for (InvoiceModel invoice : invoices) {
                CreateInvoiceResponse createInvoiceResponse = CreateInvoiceResponse.builder()
                        .reference(invoice.getReference())
                        .amount(invoice.getAmount())
                        .dueDate(invoice.getDueDate())
                        .type(String.valueOf(invoice.getType()))
                        .status(String.valueOf(invoice.getStatus()))
                        .studentId(invoice.getStudentId())
                        .links(
                                invoice.getStatus() == Status.OUTSTANDING ?
                                        Links.builder()
                                                .cancel(Link.builder()
                                                        .href(AppURL + "/api/invoice/cancel/" + invoice.getReference())
                                                        .build())
                                                .pay(Link.builder()
                                                        .href(AppURL + "/api/invoice/pay/" + invoice.getReference())
                                                        .build())
                                                .build() : null
                        )
                        .build();
                createInvoiceResponses.add(createInvoiceResponse);
            }

            return new ControllerResponse<>(true, null, createInvoiceResponses);

        } catch (Exception e) {
            logger.error("An error occurred", e);
            return new ControllerResponse<>(false, "An unexpected error occurred while processing your request. Please try again later.", null);
        }
    }

    public ControllerResponse<List<CreateInvoiceResponse>> getInvoicesByStudentId(String studentId) {
        try {
            List<InvoiceModel> invoices = invoiceRepository.findByStudentId(studentId);
            if (invoices.isEmpty())
                return new ControllerResponse<>(false, "No invoices found", null);

            List<CreateInvoiceResponse> createInvoiceResponses = new java.util.ArrayList<>();
            for (InvoiceModel invoice : invoices) {
                CreateInvoiceResponse createInvoiceResponse = CreateInvoiceResponse.builder()
                        .reference(invoice.getReference())
                        .amount(invoice.getAmount())
                        .dueDate(invoice.getDueDate())
                        .type(String.valueOf(invoice.getType()))
                        .status(String.valueOf(invoice.getStatus()))
                        .studentId(invoice.getStudentId())
                        .links(
                                invoice.getStatus() == Status.OUTSTANDING ?
                                        Links.builder()
                                                .cancel(Link.builder()
                                                        .href(AppURL + "/api/invoice/cancel/" + invoice.getReference())
                                                        .build())
                                                .pay(Link.builder()
                                                        .href(AppURL + "/api/invoice/pay/" + invoice.getReference())
                                                        .build())
                                                .build() : null
                        )
                        .build();
                createInvoiceResponses.add(createInvoiceResponse);
            }

            return new ControllerResponse<>(true, null, createInvoiceResponses);

        } catch (Exception e) {
            logger.error("An error occurred", e);
            return new ControllerResponse<>(false, "An unexpected error occurred while processing your request. Please try again later.", null);
        }
    }
}
