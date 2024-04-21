package uk.ac.leedsbeckett.albertarkaa.libraryportal.dto.request.invoice;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uk.ac.leedsbeckett.albertarkaa.libraryportal.util.Type;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateInvoiceRequest {
    private double amount;
    private LocalDateTime dueDate= LocalDateTime.now();
    private Type type;
    private String StudentId;
}
