package model;

import enums.LoanType;

public class VehicleLoan extends Loan {
    private LoanType loanType= LoanType.ARAC_KREDISI;

    public LoanType getLoanType() {
        return loanType;
    }
}
