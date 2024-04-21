package uk.ac.leedsbeckett.albertarkaa.libraryportal.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uk.ac.leedsbeckett.albertarkaa.libraryportal.dto.request.account.CreateAccountRequest;
import uk.ac.leedsbeckett.albertarkaa.libraryportal.dto.response.ControllerResponse;
import uk.ac.leedsbeckett.albertarkaa.libraryportal.dto.response.account.AccountResponse;
import uk.ac.leedsbeckett.albertarkaa.libraryportal.service.AccountService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping("/accounts")
    public ResponseEntity<ControllerResponse<AccountResponse>> createAccount(
            @RequestBody CreateAccountRequest createAccountRequest) {
        ControllerResponse<AccountResponse> response = accountService.createAccount(createAccountRequest.getStudentId());

        if (response.isSuccess()) return ResponseEntity.ok(response);
        else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);

    }

    @GetMapping("/accounts/{studentId}")
    public ResponseEntity<ControllerResponse<AccountResponse>> getAccountByStudentId(@PathVariable String studentId) {
        ControllerResponse<AccountResponse> response = accountService.getAccountByStudentId(studentId);

        if (response.isSuccess()) return ResponseEntity.ok(response);
        else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

}
