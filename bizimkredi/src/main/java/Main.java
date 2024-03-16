import model.*;
import enums.*;

import javax.naming.spi.ObjectFactory;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.math.BigDecimal;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        User userCem = new User("cem","yılmaz" , "cemdrman@gmail.com",
                "3210", "05542312319", true );
        User userHaso = new User("hasan","yılar" , "password2@gmail.com",
                "3211", "05542312318", true );
        User userMurtaza = new User("murtaza","yılmazlar" , "password3@gmail.com",
                "3212", "05542312315", true );
        User userHatice = new User("hatice","yılanlaroğulları" , "password4@gmail.com",
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

        Application application1=new Application(consumerLoanAkBank, userCem, LocalDateTime.now());
        Application application2=new Application(consumerLoanFinans, userHaso, LocalDateTime.now());
        Application application3=new Application(consumerLoanGaranti, userHatice, LocalDateTime.now());
        Application application4=new Application(consumerLoanAkBank, userMurtaza, LocalDateTime.now());

        List<Application> applicationsList = List.of(application1,application2,application3,application4);

        applicationsList.forEach(System.out::println);

        Product creditCard1 = new CreditCard(new BigDecimal(432),new ArrayList<>());
        Product creditCard2 = new CreditCard(new BigDecimal(333), Collections.EMPTY_LIST);

        CreditCard creditCard3=new CreditCard(new BigDecimal(99),Collections.EMPTY_LIST);

        //((CreditCard)creditCard1).getFee();


        Application application5=new Application(creditCard1,userCem,LocalDateTime.now());
        Application application6=new Application(creditCard2,userHaso,LocalDateTime.now());
        Application application7=new Application(creditCard3,userMurtaza,LocalDateTime.now());
        Application application8=new Application(houseLoan,userCem,LocalDateTime.now());

        List<Application> allApplicationList= new ArrayList<>(applicationsList);

        allApplicationList.add(application5);
        allApplicationList.add(application6);
        allApplicationList.add(application7);
        allApplicationList.add(application8);

        allApplicationList.forEach(System.out::println);

        User userWithMostApplications = Application.findUserWithMostApplications(allApplicationList);

        // En çok başvuru yapan kullanıcıyı bulan methodun sonucunu yazalım:
        if (userWithMostApplications != null) {
            System.out.println("User with the most applications: " + userWithMostApplications.getName());
        } else {
            System.out.println("No applications found.");
        }

        List<Application> applicationsInLastMonth = Application.listApplicationsInLastMonth(allApplicationList);

        // Son bir aylık yapılan başvuruları print ediyoruz
        System.out.println("Applications made in the last month:");
        for (Application application : applicationsInLastMonth) {
            System.out.println(application);
        }



        List<Product> products = new ArrayList<>();
        products.add(creditCard1);
        products.add(creditCard2);
        products.add(creditCard3);

        List<CreditCard> creditCards = new ArrayList<>();
        for (Product product : products) {
            if (product instanceof CreditCard) {
                creditCards.add((CreditCard) product);
            }
        }

        // kredi kartlarını kampanya sayılarına göre sort etmek:
        List<CreditCard> sortedCreditCards = CreditCard.listCreditCardOffersByCampaignCount(creditCards);

        // ve print etmek:
        System.out.println("Credit card offers sorted by the number of campaigns:");
        for (CreditCard creditCard : sortedCreditCards) {
            System.out.println(creditCard);
        }

        List<Application> userApplications = Application.listApplicationsByUserEmail(allApplicationList, "cemdrman@gmail.com");

        // cemdrman gmaili ile eşleşen applicationlar konsola yazdırılır
        System.out.println("Applications of user with email cemdrman@gmail.com:");
        for (Application application : userApplications) {
            System.out.println(application);
        }


        Customer customer1 = new Customer("Cem", "alice@example.com",24);
        Customer customer2 = new Customer("Bob Johnson", "bob@example.com",18);
        Customer customer3 = new Customer("Eve Brown", "eve@example.com",76);


        ShopProduct product1 = new ShopProduct("Smartphone", "Electronics", 599.99, 15);
        ShopProduct product2 = new ShopProduct("Tablet", "Electronics", 399.99, 20);
        ShopProduct product3 = new ShopProduct("Watch", "Fashion", 1989.99, 30);


        Order order1 = new Order(customer1);
        order1.addProduct(product1);
        order1.addProduct(product2);

        Order order2 = new Order(customer2);
        order2.addProduct(product2);
        order2.addProduct(product3);

        Order order3 = new Order(customer3);
        order3.addProduct(product1);
        order3.addProduct(product3);

        // order başına toplam ücret hesaplanıyor
        double totalAmount1 = product1.getPrice() + product2.getPrice();
        double totalAmount2 = product2.getPrice() + product3.getPrice();
        double totalAmount3 = product1.getPrice() + product3.getPrice();

        // orderlar için fatura oluşturuluyor
        Invoice invoice1 = new Invoice(order1, totalAmount1);
        Invoice invoice2 = new Invoice(order2, totalAmount2);
        Invoice invoice3 = new Invoice(order3, totalAmount3);

        // Toplam müşteri sayısı
        int totalCustomers = Customer.getTotalCustomers();
        System.out.println("Total number of customers: " + totalCustomers);

        List<Order> orders = new ArrayList<>();
        orders.add(order1);
        orders.add(order2);
        orders.add(order3);

        //  "Cem" tarafından satın alınan ürün sayısı
        int totalProductsByCem = 0;
        for (Order order : orders) {
            if (order.getCustomer().getName().equals("Cem")) {
                totalProductsByCem += order.getTotalProducts();
            }
        }

        System.out.println("Total number of products purchased by customers named Cem: " + totalProductsByCem);

        // İsmi Cem olup yaşı 30’dan küçük 25’ten büyük müşterilerin toplam alışveriş tutarını
        double totalShoppingAmount = 0;
        for (Order order : orders) {
            Customer customer = order.getCustomer();
            if (customer.getName().equals("Cem") && customer.getAge() > 25 && customer.getAge() < 30) {
                totalShoppingAmount += order.getTotalShoppingAmount();
            }
        }

        System.out.println("Total shopping amount of customers named Cem, younger than 30 and older than 25: $" + totalShoppingAmount);

        // 1500 TL üzerindeki faturalar listeleniyor
        List<Invoice> invoicesOver1500 = new ArrayList<>();

        // 1500 TL üzerindeki faturalar kontrol ediliyor
        if (invoice1.getTotalAmount() > 1500) {
            invoicesOver1500.add(invoice1);
        }
        if (invoice2.getTotalAmount() > 1500) {
            invoicesOver1500.add(invoice2);
        }

        //  1500 TL üzerindeki faturalar
        System.out.println("Invoices over 1500 TL in the system:");
        for (Invoice invoice : invoicesOver1500) {
            System.out.println(invoice);
        }


    }
}
