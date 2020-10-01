package models;
import org.junit.Test;
import static org.junit.Assert.*;


public class DeliveryDetailsTest {

    @Test
    public void DeliveryDetails_instanceOfDeliveryDetails(){
        DeliveryDetails newDeliveryDetails = new DeliveryDetails("Cuban Link","2", 200, "Kinoo");
        assertTrue(newDeliveryDetails instanceof DeliveryDetails);
    }

}