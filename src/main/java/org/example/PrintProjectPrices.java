package org.example;

public class PrintProjectPrices {
    private long id;
    private int price;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "PrintProjectPrices{" +
                "id=" + id +
                ", price=" + price +
                '}';
    }
}
