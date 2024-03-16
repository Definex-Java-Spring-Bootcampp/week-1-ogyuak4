package model;

import enums.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;


public class Application {

    private Loan loan;
    private Product product;
    private User user;
    private LocalDateTime localDateTime;
    private ApplicationStatus applicationStatus;

    private Application() {
    }

    /*
    public Application(CreditCard creditCard, User user, LocalDateTime localDateTime) {
        this.creditCard = creditCard;
        this.user = user;
        this.localDateTime = localDateTime;
        this.applicationStatus = ApplicationStatus.INITIAL;
    }*/

    public Application(Product product, User user, LocalDateTime localDateTime) {
        this.product = product;
        this.user = user;
        this.localDateTime = localDateTime;
        this.applicationStatus = ApplicationStatus.INITIAL;
    }

    public Application(Loan loan, User user, LocalDateTime localDateTime) {
        this.loan = loan;
        this.user = user;
        this.localDateTime = localDateTime;
        this.applicationStatus = ApplicationStatus.INITIAL;
    }

    public Loan getLoan() {
        return loan;
    }

    public void setLoan(Loan loan) {
        this.loan = loan;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public ApplicationStatus getApplicationStatus() {
        return applicationStatus;
    }

    public void setApplicationStatus(ApplicationStatus applicationStatus) {
        this.applicationStatus = applicationStatus;
    }

    @Override
    public String toString() {
        return "Application{" +
                "loan=" + loan +
                ", user=" + user +
                ", localDateTime=" + localDateTime +
                ", applicationStatus=" + applicationStatus +
                '}';
    }
    // en fazla application yapmış olan kullanıcıyı bulan methodu yazalım:
    public static User findUserWithMostApplications(List<Application> applications) {
        // kullanıcı başına düşen application sayısını mapleme işlemi
        Map<User, Integer> userApplicationCount = new HashMap<>();

        // kullanıcı başına düşen applicationı saymak için:
        for (Application application : applications) {
            User user = application.getUser();
            userApplicationCount.put(user, userApplicationCount.getOrDefault(user, 0) + 1);
        }

        // max application sayısına sahip useri bulmak için:
        User userWithMostApplications = userApplicationCount.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);

        return userWithMostApplications;
    }

    // Son bir aylık yapılan başvuruları listeleyen method
    public static List<Application> listApplicationsInLastMonth(List<Application> applications) {
        List<Application> applicationsInLastMonth = new ArrayList<>();

        // mevcut tarih alınıyor
        LocalDateTime currentDate = LocalDateTime.now();

        // 1 ay öncenin tarihi hesaplanıyor
        LocalDateTime oneMonthAgo = currentDate.minusMonths(1);

        // son 1 ay içinde yapılıp yapılmadığı kontrol ediliyor
        for (Application application : applications) {
            if (application.getLocalDateTime().isAfter(oneMonthAgo)) {
                applicationsInLastMonth.add(application);
            }
        }

        return applicationsInLastMonth;
    }

    public static List<Application> listApplicationsByUserEmail(List<Application> applications, String email) {
        List<Application> userApplications = new ArrayList<>();

        // Iterate işlemi gerçekleştirilir
        for (Application application : applications) {
            // spesifik olarak belirtilen email adresi ile eşleşip eşleşmediğine bakılır
            if (application.getUser().getEmail().equals(email)) {
                // eşleşiyorsa applicationlar seçilir
                userApplications.add(application);
            }
        }

        return userApplications;
    }

}
