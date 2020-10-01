//package dao;
//import models.DeliveryDetails;
//import org.junit.After;
//import org.junit.AfterClass;
//import org.junit.BeforeClass;
//import org.junit.Test;
//import org.sql2o.Connection;
//import org.sql2o.Sql2o;
//
//import static org.junit.Assert.assertEquals;
//
//
//public class Sql2oDeliveryDetailsDaoTest {
//
//    private static Connection conn;
//    private static Sql2oDeliveryDetailsDao deliveriesDao;
//
//    @BeforeClass
//    public static void setUp() throws Exception {
////        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
////        Sql2o sql2o = new Sql2o(connectionString, "", "");
//        //for postgresql
//        String connectionString = "jdbc:postgresql://localhost:5432/sendit_test";
//        Sql2o sql2o = new Sql2o(connectionString, "moringa", "moringa");
//
//        deliveriesDao = new Sql2oDeliveryDetailsDao(sql2o);
//        conn = sql2o.open();
//    }
//
//    @After
//    public void tearDown() throws Exception {
//        System.out.println("clearing database");
//        deliveriesDao.clearAll();
//        conn.close();
//    }
//
//    @AfterClass     //changed to @AfterClass (run once after all tests in this file completed)
//    public static void shutDown() throws Exception{ //changed to static
//        conn.close(); // close connection once after this entire test file is finished
//        System.out.println("connection closed");
//    }
//
//    //helper
//    public DeliveryDetails setUpDeliveryDetails(){
//        DeliveryDetails deliveryDetails = new DeliveryDetails("Cuban Link","2", 200, "Kinoo", "2020-02-10", "2020-02-15");
//        deliveriesDao.add(deliveryDetails);
//        return deliveryDetails;
//    }
//    //helper
//    public DeliveryDetails setUpAltDeliveryDetails(){
//        DeliveryDetails deliveryDetailsTwo = new DeliveryDetails("Cuban Link","2", 200, "Kinoo", "2020-02-10", "2020-02-15");
//        deliveriesDao.add(deliveryDetailsTwo);
//        return deliveryDetailsTwo;
//    }
//
//    @Test
//    public void instantiatesCorrectly(){
//        DeliveryDetails testDeliveryDetails = setUpDeliveryDetails();
//        assertEquals(true, testDeliveryDetails instanceof DeliveryDetails);
//    }
//
//    @Test
//    public void getAll_DeliveryDetailsInstances(){
//        DeliveryDetails one = setUpDeliveryDetails();
//        DeliveryDetails two = setUpAltDeliveryDetails();
//        assertEquals(2, deliveriesDao.getAll().size());
//    }
//
//    @Test
//    public void noInstanceReturnNothing(){
//        DeliveryDetails one = setUpDeliveryDetails();
////        deliveriesDao.deleteById(1);
////        deliveriesDao.deleteById(one.getId());
//        deliveriesDao.clearAll();                      //all these delete DeliveryDetails one
//        assertEquals(0,deliveriesDao.getAll().size());
//    }
//
//    @Test
//    public void findById_returnsFoundDeliveryDetails(){
//        DeliveryDetails one = setUpDeliveryDetails();
//        DeliveryDetails two = setUpAltDeliveryDetails();
//        assertEquals(one, deliveriesDao.findById(one.getId()));
//        assertEquals(two, deliveriesDao.findById(two.getId()));
//    }
//
//        //    @Test
////    public void DeliveryDetailsupdatesCorrectly(){
////        DeliveryDetails one = setUpDeliveryDetails();
////        deliveriesDao.update(one.getId(),"AirPods","5", "4500");
////        DeliveryDetails changed = deliveriesDao.findById(one.getId());
////        assertEquals("Airpods", changed.getitem());
////        assertEquals("5", changed.getquantity());
////    }
//
//    @Test
//    public void  DeliveryDetailsDeletedById(){
//        DeliveryDetails one = setUpDeliveryDetails();
//        DeliveryDetails two = setUpAltDeliveryDetails();
//        deliveriesDao.deleteById(one.getId());
//        assertEquals(1, deliveriesDao.getAll().size());
//    }
//
//    @Test
//    public void clearAllDeletesAllDeliveryDetailsInstances(){
//        DeliveryDetails one = setUpDeliveryDetails();
//        DeliveryDetails two = setUpAltDeliveryDetails();
//        DeliveryDetails three = new DeliveryDetails("Beats Headphones","1", 1000, "Muthiga", "2020-01-12", "2020-01-14");
//        deliveriesDao.clearAll();
//        assertEquals(0, deliveriesDao.getAll().size());
//    }
//
//
//}