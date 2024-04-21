package uk.ac.leedsbeckett.albertarkaa.libraryportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uk.ac.leedsbeckett.albertarkaa.libraryportal.dto.request.invoice.CreateInvoiceRequest;
import uk.ac.leedsbeckett.albertarkaa.libraryportal.model.AccountModel;
import uk.ac.leedsbeckett.albertarkaa.libraryportal.model.InvoiceModel;

import java.util.List;
import java.util.Optional;

public interface InvoiceRepository extends JpaRepository<InvoiceModel, Integer> {
   InvoiceModel findByReference(String reference);

    List<InvoiceModel> findByStudentId(String studentId);
}
