package com.solvd.bankapp.services.parsers;

import com.solvd.bankapp.bin.Employee;

import java.io.File;

public interface IEmployeeJsonService {
    public String serialize(Employee employee);
    public Employee deserialize(File jsonFile);
}
