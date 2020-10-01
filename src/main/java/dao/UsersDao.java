package dao;

import models.Users;

import java.sql.SQLException;
import java.util.List;

public interface UsersDao {

    //create
    void add(Users users);

    //    read
    List<Users> getAll() throws SQLException;
    Users findById(int id) throws SQLException;

//    update

    //    delete
    public Users deleteById(int id);
    void clearAll();

}
