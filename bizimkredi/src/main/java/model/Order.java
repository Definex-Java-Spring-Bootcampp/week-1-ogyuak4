package model;
import java.util.ArrayList;
import java.util.List;
public class Order {
    private Customer customer;
    private List<ShopProduct> products;

    public Order(Customer customer) {
        this.customer = customer;
        this.products = new ArrayList<>();
    }

    public void addProduct(ShopProduct product) {
        products.add(product);
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<ShopProduct> getProducts() {
        return products;
    }

    public void setProducts(List<ShopProduct> products) {
        this.products = products;
    }
    public int getTotalProducts() {
        return products.size();
    }


    // order başına toplam ücreti hesapla:
    public double getTotalShoppingAmount() {
        double totalAmount = 0;
        for (ShopProduct product : products) {
            totalAmount += product.getPrice();
        }
        return totalAmount;
    }




}
