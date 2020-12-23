package database;

import java.sql.Connection;

public abstract class DAO<T> {

    public Connection connect = ConnectionDB.getInstance();

    public abstract T insert(T obj);
}
