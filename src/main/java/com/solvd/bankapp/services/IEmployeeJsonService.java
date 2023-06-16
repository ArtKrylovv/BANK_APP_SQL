package com.solvd.bankapp.services;

import com.solvd.bankapp.bin.Employee;

import java.io.File;

public interface IEmployeeJsonService {
    public void serialize(Employee employee);
    public Employee deserialize(File jsonFile);
}
