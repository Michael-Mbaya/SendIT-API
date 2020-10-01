package models;

import java.sql.Timestamp;
import java.util.Objects;

public class DeliveryDetails {


    private int id;
    private String item;
    private String quantity;
    private int price;
    private String destination;
    private Timestamp dispatch_time;
    private Timestamp delivery_time;

    //DeliveryDetails Object constructor
    public DeliveryDetails(String item, String quantity, int price, String destination, Timestamp dispatch_time, Timestamp delivery_time){
        this.item =item;
        this.quantity = quantity;
        this.price = price;
        this.destination = destination;
        this.dispatch_time = dispatch_time;
        this.delivery_time = delivery_time;


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

    public void setDispatch_time(Timestamp dispatch_time) {
        this.dispatch_time = dispatch_time;
    }

    public Timestamp getDispatch_time() {
        return dispatch_time;
    }

    public void setDelivery_time(Timestamp delivery_time) {
        this.delivery_time = delivery_time;
    }

    public Timestamp getDelivery_time() {
        return delivery_time;
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
                destination.equals(that.destination) &&
                dispatch_time.equals(that.dispatch_time) &&
                delivery_time.equals(that.delivery_time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, item, quantity, price, destination, dispatch_time, delivery_time);
    }
}
