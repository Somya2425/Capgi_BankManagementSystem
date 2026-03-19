package com.example.Capgi_BankManagementSystem.dto.request;

<<<<<<< HEAD
import com.example.Capgi_BankManagementSystem.enums.AccountType;
=======
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
>>>>>>> rollback/test-version
import lombok.Data;

@Data
public class AccountRequestDto {
<<<<<<< HEAD
    private AccountType type;
    private double balance;
}
=======

    @NotBlank(message = "Account type is required (SAVINGS/CURRENT)")
    private String type;

    @NotNull(message = "Initial balance is required")
    private Double balance;
}
>>>>>>> rollback/test-version
