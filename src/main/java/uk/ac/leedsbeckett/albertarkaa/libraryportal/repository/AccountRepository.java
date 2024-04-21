package uk.ac.leedsbeckett.albertarkaa.libraryportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uk.ac.leedsbeckett.albertarkaa.libraryportal.model.AccountModel;
import uk.ac.leedsbeckett.albertarkaa.libraryportal.util.Account;

import java.util.List;

public interface AccountRepository extends JpaRepository<AccountModel, Integer>{
    AccountModel findByStudentId(String studentId);
}
