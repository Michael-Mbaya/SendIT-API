package dao;

import models.DeliveryDetails;

import java.util.List;

public interface DeliveryDetailsDao {

    //create
    void add(DeliveryDetails deliveries);

    //read
    List<DeliveryDetails> getAll();
    DeliveryDetails findById(int id);

    //update
    void update(int id, String item, String quantity, int price, String destination);

    //delete
    DeliveryDetails deleteById(int id);
    void clearAll();


}