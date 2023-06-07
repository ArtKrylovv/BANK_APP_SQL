package com.solvd.bankapp.services;

import java.util.List;

public interface IService <T> {
    T readFromDb(int id);
    List<T> readAllFromDb();
    int writeToDb(T t);
    int updateInDb(T t);
    int removeFromDb(int id);
}