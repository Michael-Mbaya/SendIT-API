import com.google.gson.Gson;
import dao.Sql2oDeliveryDetailsDao;
import dao.Sql2oUsersDao;
import exceptions.ApiException;
import models.DeliveryDetails;
import models.Users;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;


public class App {
    private static int getHerokuAssignedPort () {
        ProcessBuilder processBuilder = new ProcessBuilder ();
        if (processBuilder.environment ().get ("PORT") != null) {
            return Integer.parseInt (processBuilder.environment ().get ("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }

    public static void main (String[] args) {
        port (getHerokuAssignedPort ());
        staticFileLocation ("/public");
        Sql2oUsersDao usersDao;
        Sql2oDeliveryDetailsDao deliveriesDao;
        Connection conn;
        Gson gson = new Gson ();

//        For h2
//        String connectionString = "jdbc:h2:~/jadle.db;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
//        Sql2o sql2o = new Sql2o (connectionString, "", "");
        //for postgresql
//        String connectionString = "jdbc:postgresql://localhost:5432/sendit";
//        Sql2o sql2o = new Sql2o(connectionString, "moringa", "moringa");
        //for herokupostgresql
        String connectionString = "jdbc:postgresql://ec2-52-200-82-50.compute-1.amazonaws.com:5432/d9it5p18p3l75c";
        Sql2o sql2o = new Sql2o(connectionString, "ymhlkvtkgqzews", "4249e594aab952f2f9b10431efba02b79e814f8e91ff6b02ace16fe7616f23ad");


        usersDao = new Sql2oUsersDao (sql2o);
        conn = sql2o.open ();
        deliveriesDao = new Sql2oDeliveryDetailsDao(sql2o);
        conn = sql2o.open();

        get("/", "application/json", (req, res) ->
                "{\"message\":\"Welcome to the main page of SendIT API.\"}");

        //Users
        //postman posts new Users object (Json Format)
        post("/user/new", "application/json", (req, res)->{
            Users newUser = gson.fromJson(req.body(), Users.class);
            usersDao.add(newUser);
//            res.status(201);
            return gson.toJson(newUser);
        });

        //postman gets List of Users objects
        get("/users", "application/json", (req, res) -> {
            System.out.println(usersDao.getAll());
            if(usersDao.getAll().size() > 0) {
                //                res.status(200);
                return gson.toJson(usersDao.getAll());
            }
            else{
                //                res.status(100);
                return "{\"message\":\"I'm sorry, but no Users items are currently listed in the database.\"}";
            }
        });

        //postman gets Users objects by their id (Json format)
        get("/user/:id", "application/json", (req, res) -> { //accept a request in format JSON from an app
            int destinationId = Integer.parseInt(req.params("id"));
            Users userToFind = usersDao.findById(destinationId);
            try {
                if (userToFind == null) {
                    throw new ApiException(404, String.format("No destination item with the id: \"%s\" exists", req.params("id")));
                }
            }catch (ApiException ex){
                System.out.println(ex);
            }
            if(userToFind == null){
                //                    res.status(100);
                return "{\"message\":\"I'm sorry, but no destinations items are currently listed in the database.\"}";
            }else{
                //                    res.status(201);
                return gson.toJson(userToFind);
            }
        });

        //postman deletes Users objects by their id (Json format)
        get("/user/:id/delete", "application/json", (req, res) -> { //accept a request in format JSON from an app
            int userId = Integer.parseInt(req.params("id"));
            Users userToFind = usersDao.deleteById(userId);
            try {
                if (userToFind == null) {
                    throw new ApiException(404, String.format("No destination item with the id: \"%s\" exists", req.params("id")));
                }
            }catch (ApiException ex){
                System.out.println(ex);
            }
//            res.status(300);
            res.redirect("/users");
            return gson.toJson(userToFind);
        });

//deliveries
        //postman posts new DeliveryDetails object (Json Format)
        post("/delivery/new", "application/json", (req, res)->{
            DeliveryDetails newDeliveryDetails = gson.fromJson(req.body(), DeliveryDetails.class);
            deliveriesDao.add(newDeliveryDetails);
//            res.status(201);
            return gson.toJson(newDeliveryDetails);
        });

        //postman gets List of DeliveryDetails objects
        get("/deliveries", "application/json", (req, res) -> {
            System.out.println(deliveriesDao.getAll());

            if(deliveriesDao.getAll().size() > 0) {
//                res.status(200);
                return gson.toJson(deliveriesDao.getAll());
            }
            else{
//                res.status(100);
                return "{\"message\":\"I'm sorry, but no deliveries items are currently listed in the database.\"}";
            }
        });

        //postman gets DeliveryDetails objects by their id (Json format)
        get("/delivery/:id", "application/json", (req, res) -> { //accept a request in format JSON from an app
            int DeliveryDetailsId = Integer.parseInt(req.params("id"));
            DeliveryDetails deliveryDetailsToFind = deliveriesDao.findById(DeliveryDetailsId);
            try {
                if (deliveryDetailsToFind == null) {
                    throw new ApiException(404, String.format("No DeliveryDetails item with the id: \"%s\" exists", req.params("id")));
                }
            }catch (ApiException ex){
                System.out.println(ex);
            }
            if(deliveryDetailsToFind == null){
//                    res.status(100);
                return "{\"message\":\"I'm sorry, but no deliveries items are currently listed in the database.\"}";
            }else{
//                    res.status(201);
                return gson.toJson(deliveryDetailsToFind);
            }
        });

        //postman deletes DeliveryDetails objects by their id (Json format)
        get("/delivery/:id/delete", "application/json", (req, res) -> { //accept a request in format JSON from an app
            int DeliveryDetailsId = Integer.parseInt(req.params("id"));
            DeliveryDetails deliveryDetailsToFind = deliveriesDao.deleteById(DeliveryDetailsId);

            try {
                if (deliveryDetailsToFind == null) {
                    throw new ApiException(404, String.format("No DeliveryDetails item with the id: \"%s\" exists", req.params("id")));
                }
            }catch (ApiException ex){
                System.out.println(ex);
            }

//            res.status(300);
            res.redirect("/deliveries");
            return gson.toJson(deliveryDetailsToFind);
        });


        //FILTERS
        exception(ApiException.class, (exception, req, res) -> {
            ApiException err = exception;
            Map<String, Object> jsonMap = new HashMap<>();
            jsonMap.put("status", err.getStatusCode());
            jsonMap.put("errorMessage", err.getMessage());
            res.type("application/json");
            res.status(err.getStatusCode());
            res.body(gson.toJson(jsonMap));
        });


        after((req, res) ->{
            res.type("application/json");
        });

    }
}