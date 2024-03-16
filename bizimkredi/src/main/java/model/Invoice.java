package model;

import java.time.LocalDateTime;

public class Invoice {
    private Order order;
    private LocalDateTime createdAt;
    private double totalAmount;

    public Invoice(Order order, double totalAmount) {
        this.order = order;
        this.createdAt = LocalDateTime.now();
        this.totalAmount = totalAmount;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
}
