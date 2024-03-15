package model;

import enums.LoanType;

public class ConsumerLoan extends Loan{
    private LoanType loanType= LoanType.IHTIYAC_KREDISI;
    public LoanType getLoanType() {
        return loanType;
    }
}
