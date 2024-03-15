import model.*;
import enums.*;

import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        User userCemal = new User("cemal","yılmaz" , "password@gmail.com",
                "3210", "05542312319", true );
        User userHaso = new User("hasan","yılar" , "password@gmail.com",
                "3211", "05542312318", true );
        User userMurtaza = new User("murtaza","yılmazlar" , "password@gmail.com",
                "3212", "05542312315", true );
        User userHatice = new User("hatice","yılanlaroğulları" , "password@gmail.com",
                "3213", "05542312331", true );


        Bank akBank=new Bank();
        akBank.setName("AkBank");

        Bank garanti=new Bank();
        garanti.setName("Garanti");

        Bank finansBank=new Bank();
        finansBank.setName("AkBank");

        ConsumerLoan consumerLoanAkbank= new ConsumerLoan();
        consumerLoanAkbank.setBank(akBank);
        consumerLoanAkbank.setInterestRate(2.31);

        ConsumerLoan consumerLoanGaranti= new ConsumerLoan();
        consumerLoanGaranti.setBank(garanti);
        consumerLoanGaranti.setInterestRate(2.61);

        ConsumerLoan consumerLoanFinansBank= new ConsumerLoan();
        consumerLoanFinansBank.setBank(finansBank);
        consumerLoanFinansBank.setInterestRate(2.91);

        HouseLoan houseLoan= new HouseLoan();

        List<Loan> loanList= new ArrayList<>();

    }
}
