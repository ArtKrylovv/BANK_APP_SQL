package com.solvd.bankapp.dao;

import java.sql.SQLException;
import java.util.List;

public interface IDao<T> {
    List<T> getAll();
    T get(int id);
    int create(T t);
    int update(T t);
    int delete (int id);
}

