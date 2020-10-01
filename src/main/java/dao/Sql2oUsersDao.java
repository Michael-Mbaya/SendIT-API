package dao;

import models.Users;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.sql.SQLException;
import java.util.List;

//import java.sql.Connection;
//import java.sql.SQLException;


public class Sql2oUsersDao implements UsersDao {
    private final Sql2o sql2o;

    public Sql2oUsersDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }


    @Override
    public void add (Users users) {

        try (Connection con= (Connection) sql2o.open()) {
            String sql = "INSERT INTO users (name,address,phone_number) VALUES (:name, :address,:phone_number)";

            int id = (int) con.createQuery (sql, true)
                    .bind (users)
                    .executeUpdate ()
                    .getKey ();
            users.setId (id);
        }catch (Sql2oException ex){
            System.out.println (ex);
        }
    }

    @Override
    public List<Users> getAll () throws SQLException {
        try (Connection con= (Connection) sql2o.open()){
            String sql=("SELECT * FROM users");
            return con.createQuery(sql)
                    .executeAndFetch(Users.class);
        }
    }

    @Override
    public Users findById (int id) throws SQLException {
        try (org.sql2o.Connection con=sql2o.open()){
            String sql=("SELECT * FROM users WHERE id=:id");
            return con.createQuery(sql)
                    .addParameter("id",id)
                    .executeAndFetchFirst(Users.class);
        }
    }

    @Override
    public Users deleteById(int id){
        String sql = "DELETE from users WHERE id = :id"; //raw sql
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
    public void clearAll() {

        try (org.sql2o.Connection con = sql2o.open ()) {
            String sql = "DELETE FROM users ";
            con.createQuery (sql).executeUpdate ();
            String sqlUsersDepartments = "DELETE FROM users";
            con.createQuery (sqlUsersDepartments).executeUpdate ();


        } catch (Sql2oException e) {
            System.out.println (e);
        }
    }
}

