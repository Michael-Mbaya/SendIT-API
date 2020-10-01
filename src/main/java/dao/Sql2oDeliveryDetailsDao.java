package dao;

import models.DeliveryDetails;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oDeliveryDetailsDao implements DeliveryDetailsDao {

    private final  Sql2o sql2o;
    public Sql2oDeliveryDetailsDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void add(DeliveryDetails deliveries){
        String sql = "INSERT INTO deliveries (item, quantity, price, destination) VALUES (:item,:quantity, :price, :destination)";
        try(Connection con = sql2o.open()){
            int id = (int) con.createQuery(sql, true)
                    .bind(deliveries)
                    .executeUpdate()
                    .getKey();
            deliveries.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public List<DeliveryDetails> getAll(){
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM deliveries")
                    .executeAndFetch(DeliveryDetails.class);
        }
    }

    @Override
    public DeliveryDetails findById(int id){
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM deliveries WHERE id=:id")
                    .addParameter("id", id)
                    .executeAndFetchFirst(DeliveryDetails.class);
        }
    }

    @Override
    public void update(int id, String item, String quantity, int price, String destination){
        String sql = "UPDATE deliveries SET (item, quantity, price, destination) = (:item, :quantity, :price, :destination) WHERE id=:id"; //CHECK!!!
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("item", item)
                    .addParameter("quantity", quantity)
                    .addParameter("price", price)
                    .addParameter("id", id)
                    .addParameter("destination", destination)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public DeliveryDetails deleteById(int id){
        String sql = "DELETE from deliveries WHERE id = :id"; //raw sql
        try(Connection con = sql2o.open()){
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        }catch (Sql2oException ex){
            System.out.println(ex);
        }
        return null;
    }

    @Override
    public void clearAll(){
        String sql = "DELETE FROM deliveries";
        try(Connection con = sql2o.open()){
            con.createQuery(sql).executeUpdate();
        }catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }


}