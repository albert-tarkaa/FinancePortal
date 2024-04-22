package uk.ac.leedsbeckett.albertarkaa.libraryportal.dto.response.invoice;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateInvoiceResponse {
    private String reference;
    private double amount;
    private LocalDateTime dueDate;
    private String type;
    private String status;
    private String studentId;
    private Links links;
}