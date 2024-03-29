package model;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class User {

    private String name;
    private String surname;
    private LocalDate birthDate;
    private String email; //bir email ile bir kere kayıt olunabilir.
    private String password; //hash fonskiyonlarından biri ile hashlanecek.
    private String phoneNumber;
    private Boolean isActive;
    private List<Application> applicationList;

    // // Static set eklendi ki kaydolan emailleri kayıt altına alıp bakabilelim
    private static Set<String> registeredEmails = new HashSet<>();

    public User(String name, String surname, LocalDate birthDate, String email, String password, String phoneNumber, Boolean isActive) {
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.isActive = isActive;
        if (registeredEmails.contains(email)) {
            throw new IllegalArgumentException("Email address already registered.");
        }
        registeredEmails.add(email); // email register ediliyor
    }

    public User(String name, String surname, String email, String password, String phoneNumber, Boolean isActive) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.isActive = isActive;
        if (registeredEmails.contains(email)) {
            throw new IllegalArgumentException("Email address already registered.");
        }
        registeredEmails.add(email); // email register ediliyor
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public List<Application> getApplicationList() {
        return applicationList;
    }

    public void setApplicationList(List<Application> applicationList) {
        this.applicationList = applicationList;
    }
    public static Set<String> getRegisteredEmails() {
        return registeredEmails;
    }

    public static void setRegisteredEmails(Set<String> registeredEmails) {
        User.registeredEmails = registeredEmails;
    }

    private String encryptPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-512");
            byte[] encodedHash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder(2 * encodedHash.length);
            for (byte b : encodedHash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public BigDecimal calculateTotalLoanAmount() {
        BigDecimal totalLoanAmount = BigDecimal.ZERO;
        if (applicationList != null) {
            for (Application application : applicationList) {
                if (application.getLoan() != null) {
                    totalLoanAmount = totalLoanAmount.add(application.getLoan().getAmount());
                }
            }
        }
        return totalLoanAmount;
    }

    // Static method en yüksek loana sahip olan kullanıcının loan miktarını bulmak için eklendi
    public static User findUserWithHighestLoan(List<User> userList) {
        User userWithHighestLoan = userList.get(0);
        BigDecimal highestLoanAmount = BigDecimal.ZERO;
        for (User user : userList) {
            BigDecimal totalLoanAmount = user.calculateTotalLoanAmount();
            if (totalLoanAmount.compareTo(highestLoanAmount) > 0) {
                highestLoanAmount = totalLoanAmount;
                userWithHighestLoan = user;
            }
        }
        return userWithHighestLoan;
    }

}
