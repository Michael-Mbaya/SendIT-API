package models;

import java.util.Objects;

public class DeliveryDetails {


    private int id;
    private String item;
    private String quantity;
    private int price;
    private String destination;

    //DeliveryDetails Object constructor
    public DeliveryDetails(String item, String quantity, int price, String destination){
        this.item =item;
        this.quantity = quantity;
        this.price = price;
        this.destination = destination;


    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getitem() {
        return item;
    }

    public void setitem(String item) {
        this.item = item;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeliveryDetails that = (DeliveryDetails) o;
        return id == that.id &&
                price == that.price &&
                item.equals(that.item) &&
                quantity.equals(that.quantity) &&
                destination.equals(that.destination);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, item, quantity, price, destination);
    }
}
