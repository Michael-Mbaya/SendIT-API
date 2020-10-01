package models;

import java.util.Objects;

public class Users {

    private int id;
    private String name;
    private String address;
    private int phone_number;

    public Users (String name, String address, int phone_number) {
        this.name = name;
        this.address = address;
        this.phone_number = phone_number;
    }

    public String getName () {
        return name;
    }

    public String getAddress () {
        return address;
    }

    public int getId () {
        return id;
    }

    public int getPhone_number () {
        return phone_number;
    }

    public void setName (String name) {
        this.name = name;
    }

    public void setAddress (String address) {
        this.address = address;
    }

    public void setId (int id) {
        this.id = id;
    }

    public void setPhone_number (int phone_number) {
        this.phone_number = phone_number;
    }

    @Override
    public boolean equals (Object o) {
        if (this == o)
            return true;
        if (o == null || getClass () != o.getClass ())
            return false;
        Users users = (Users) o;
        return id == users.id &&
                phone_number == users.phone_number &&
                Objects.equals (name, users.name) &&
                Objects.equals (address, users.address);
    }

    @Override
    public int hashCode () {
        return Objects.hash (name, address, id, phone_number);
    }
}
