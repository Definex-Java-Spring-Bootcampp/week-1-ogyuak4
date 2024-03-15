package model;

import enums.LoanType;

public class HouseLoan extends Loan{
    private LoanType loanType=LoanType.KONUT_KREDISI;

    public LoanType getLoanType() {
        return loanType;
    }



}
