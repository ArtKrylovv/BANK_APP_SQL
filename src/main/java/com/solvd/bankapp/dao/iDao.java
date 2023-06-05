package com.solvd.bankapp.dao;

import java.sql.SQLException;
import java.util.List;

public interface iDao<T> {
    List<T> getAll() throws SQLException;
    T get(int id) throws SQLException;
    int create(T t) throws SQLException;
    int update(T t) throws SQLException;
    int delete (int id) throws SQLException;
}

