package uk.ac.leedsbeckett.albertarkaa.libraryportal.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uk.ac.leedsbeckett.albertarkaa.libraryportal.util.Status;
import uk.ac.leedsbeckett.albertarkaa.libraryportal.util.Type;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "invoice")
public class InvoiceModel {

    private @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(unique = true)
    private String reference;
    private Double amount;
    private LocalDateTime dueDate;
    private Type type;
    private Status status;
    private String studentId;
    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();
    @Builder.Default
    private LocalDateTime updatedAt = LocalDateTime.now();
}
