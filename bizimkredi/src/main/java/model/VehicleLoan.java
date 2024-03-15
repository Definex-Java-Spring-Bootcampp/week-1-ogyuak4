package model;

import enums.*;
import java.math.BigDecimal;
public class VehicleLoan extends Loan {
    private LoanType loanType = LoanType.ARAC_KREDISI;
    private VehicleStatuType vechileStatuType;

    public VehicleLoan() {

    }

    public VehicleLoan(BigDecimal amount, Integer installment, Double interestRate) {
        super(amount, installment, interestRate);
    }

    public LoanType getLoanType() {
        return loanType;
    }

    @Override
    void calculate(BigDecimal amount, int installment) {
        //istediği kadar ödeme yapabilir.
    }
}
