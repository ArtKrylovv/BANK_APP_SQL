package com.solvd.bankapp.services.parsers;

import java.io.IOException;
import java.util.List;

public interface IService <T> {
    T readFromDb(int id) throws IOException;
    List<T> readAllFromDb();
    int writeToDb(T t);
    int updateInDb(T t);
    int removeFromDb(int id);
}