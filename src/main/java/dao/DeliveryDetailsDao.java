package dao;

import models.DeliveryDetails;

import java.sql.Timestamp;
import java.util.List;

public interface DeliveryDetailsDao {

    //create
    void add(DeliveryDetails deliveries);

    //read
    List<DeliveryDetails> getAll();
    DeliveryDetails findById(int id);

    //update
    void update(int id, String item, String quantity, int price, String destination, Timestamp dispatch_time, Timestamp delivery_time);

    //delete
    DeliveryDetails deleteById(int id);
    void clearAll();


}