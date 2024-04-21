package uk.ac.leedsbeckett.albertarkaa.libraryportal.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import uk.ac.leedsbeckett.albertarkaa.libraryportal.dto.response.ControllerResponse;
import uk.ac.leedsbeckett.albertarkaa.libraryportal.dto.response.account.AccountResponse;
import uk.ac.leedsbeckett.albertarkaa.libraryportal.model.AccountModel;
import uk.ac.leedsbeckett.albertarkaa.libraryportal.repository.AccountRepository;

@Service
@RequiredArgsConstructor
public class AccountService {

    private static final Logger logger = LoggerFactory.getLogger(AccountService.class);
    private final AccountRepository accountRepository;

    public ControllerResponse<AccountResponse> createAccount(String studentId) {
        try {
            if (studentId == null || studentId.isEmpty())
                return new ControllerResponse<>(false, "Student ID is required", null);

            AccountModel accountModel = accountRepository.findByStudentId(studentId);
            if (accountModel != null)
                return new ControllerResponse<>(false, "Account already exists", null);


            AccountModel newAccount = AccountModel.builder()
                    .studentId(studentId)
                    .build();

            accountRepository.save(newAccount);
            return new ControllerResponse<>(true, null, new AccountResponse(newAccount));
        } catch (Exception e) {
            logger.error("An error occurred", e);
            return new ControllerResponse<>(false, "An unexpected error occurred while processing your request. Please try again later.", null);
        }
    }

    public ControllerResponse<AccountResponse> getAccountByStudentId(String studentId) {
        try {

            if (studentId == null || studentId.isEmpty())
                return new ControllerResponse<>(false, "Student ID is required", null);

            AccountModel accountModel = accountRepository.findByStudentId(studentId);
            if (accountModel == null) {
                return new ControllerResponse<>(false, "Account not found", null);
            }
            return new ControllerResponse<>(true, null, new AccountResponse(accountModel));
        } catch (Exception e) {
            logger.error("An error occurred", e);
            return new ControllerResponse<>(false, "An unexpected error occurred while processing your request. Please try again later.", null);
        }
    }
}