package uk.ac.leedsbeckett.albertarkaa.libraryportal.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "account")
public class AccountModel {

    private @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(unique = true)
    private String studentId;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)

    private List<InvoiceModel> invoiceList = new ArrayList<>();
    @Builder.Default
    private boolean hasOutstandingBalance = false;
    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();
}
