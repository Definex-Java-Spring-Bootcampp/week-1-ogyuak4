import model.*;
import enums.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.math.BigDecimal;
import java.time.LocalDate;

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
        List<Loan> akBankLoadList= new ArrayList<>();
        akBankLoadList.add(new ConsumerLoan(new BigDecimal(10000),10,3.51));
        akBankLoadList.add(new HouseLoan(new BigDecimal(10000),100,3.31));
        akBankLoadList.add(new VehicleLoan(new BigDecimal(10000),150,3.41));

        Bank akBank=new Bank();
        akBank.setName("AkBank");
        akBank.setLoanList(akBankLoadList);

        System.out.println(akBank);

        Bank garanti=new Bank();
        garanti.setName("Garanti");

        Bank finansBank=new Bank();
        finansBank.setName("FinansBank");

        List<Loan> loanList=new ArrayList<>();

        ConsumerLoan consumerLoanAkBank= new ConsumerLoan();
        consumerLoanAkBank.setBank(akBank);
        consumerLoanAkBank.setInterestRate(2.31);
        loanList.add(consumerLoanAkBank);

        ConsumerLoan consumerLoanGaranti= new ConsumerLoan();
        consumerLoanGaranti.setBank(garanti);
        consumerLoanGaranti.setInterestRate(2.61);
        loanList.add(consumerLoanGaranti);

        ConsumerLoan consumerLoanFinans= new ConsumerLoan();
        consumerLoanFinans.setBank(finansBank);
        consumerLoanFinans.setInterestRate(1.23);
        loanList.add(consumerLoanFinans);

        HouseLoan houseLoan= new HouseLoan();
        houseLoan.setBank(akBank);
        houseLoan.setInterestRate(2.91);
        loanList.add(houseLoan);

        loanList.forEach(System.out::println);

        Application application1=new Application(consumerLoanAkBank, userCemal, LocalDateTime.now());
        Application application2=new Application(consumerLoanFinans, userHaso, LocalDateTime.now());
        Application application3=new Application(consumerLoanGaranti, userHatice, LocalDateTime.now());
        Application application4=new Application(consumerLoanAkBank, userMurtaza, LocalDateTime.now());

        List<Application> applicationsList = List.of(application1,application2,application3,application4);

        applicationsList.forEach(System.out::println);

        Product creditCard1 = new CreditCard(new BigDecimal(432),new ArrayList<>());
        Product creditCard2 = new CreditCard(new BigDecimal(333), Collections.EMPTY_LIST);

        CreditCard creditCard3=new CreditCard(new BigDecimal(99),Collections.EMPTY_LIST);

        //((CreditCard)creditCard1).getFee();


        Application application5=new Application(creditCard1,userCemal,LocalDateTime.now());
        Application application6=new Application(creditCard2,userHaso,LocalDateTime.now());
        Application application7=new Application(creditCard3,userMurtaza,LocalDateTime.now());
        Application application8=new Application(houseLoan,userCemal,LocalDateTime.now());

        List<Application> allApplicationList= new ArrayList<>(applicationsList);

        allApplicationList.add(application5);
        allApplicationList.add(application6);
        allApplicationList.add(application7);
        allApplicationList.add(application8);

        allApplicationList.forEach(System.out::println);

    }
}
