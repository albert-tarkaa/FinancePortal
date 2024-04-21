package uk.ac.leedsbeckett.albertarkaa.libraryportal.dto.response.account;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uk.ac.leedsbeckett.albertarkaa.libraryportal.model.AccountModel;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountResponse {
    private int id;
    private String studentId;
    private boolean hasOutstandingBalance;

    public AccountResponse(AccountModel newAccount) {
        this.id = newAccount.getId().intValue();
        this.studentId = newAccount.getStudentId();
        this.hasOutstandingBalance = newAccount.isHasOutstandingBalance();
    }
}
