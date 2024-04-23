package uk.ac.leedsbeckett.albertarkaa.libraryportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uk.ac.leedsbeckett.albertarkaa.libraryportal.model.InvoiceModel;
import uk.ac.leedsbeckett.albertarkaa.libraryportal.util.Status;

import java.util.List;

public interface InvoiceRepository extends JpaRepository<InvoiceModel, Integer> {
    InvoiceModel findByReference(String reference);

    List<InvoiceModel> findByStudentId(String studentId);

    long countByStudentIdAndStatus(String studentId, Status status);

}
