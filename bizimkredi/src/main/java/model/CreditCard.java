package model;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import model.*;

public class CreditCard implements Product{

    private BigDecimal fee;
    private List<Campaign> campaignList;
    private Bank bank;

    public CreditCard(BigDecimal fee, List<Campaign> campaignList) {
        this.fee = fee;
        this.campaignList = campaignList;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    public List<Campaign> getCampaignList() {
        return campaignList;
    }

    public void setCampaignList(List<Campaign> campaignList) {
        this.campaignList = campaignList;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    @Override
    public String toString() {
        return "CreditCard{" +
                "fee=" + fee +
                ", campaignList=" + campaignList +
                ", bank=" + bank +
                '}';
    }

    // Kredi kartı tekliflerini kampanya sayısına göre çoktan aza doğru listeleyen bir metod
    public static List<CreditCard> listCreditCardOffersByCampaignCount(List<CreditCard> creditCards) {
        // Sort the credit cards based on the number of campaigns
        return creditCards.stream()
                .sorted(Comparator.comparingInt(card -> ((CreditCard) card).getCampaignList().size()).reversed())
                .collect(Collectors.toList());
    }


}
