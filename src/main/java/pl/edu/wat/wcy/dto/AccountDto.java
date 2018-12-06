package pl.edu.wat.wcy.dto;

import pl.edu.wat.wcy.model.person.account.AccountType;

public class AccountDto {
    private String loginStr;

    private String plainPassword;

    private String emailStr;

    private AccountType accountType;

    private long personId;

    private AccountDto() {}

    public AccountDto(String loginStr, String plainPassword, String emailStr, AccountType accountType, long personId) {
        this.loginStr = loginStr;
        this.plainPassword = plainPassword;
        this.emailStr = emailStr;
        this.accountType = accountType;
        this.personId = personId;
    }

    public String getLoginStr() {
        return loginStr;
    }

    public String getPlainPassword() {
        return plainPassword;
    }

    public String getEmailStr() {
        return emailStr;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public long getPersonId() {
        return personId;
    }
}
