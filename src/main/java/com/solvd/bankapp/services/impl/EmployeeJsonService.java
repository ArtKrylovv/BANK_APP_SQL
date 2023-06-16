package com.solvd.bankapp.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.solvd.bankapp.bin.Employee;
import com.solvd.bankapp.services.IEmployeeJsonService;
import com.solvd.bankapp.utils.jsonparser.Mapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;

public class EmployeeJsonService implements IEmployeeJsonService {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public void serialize(Employee employee) {
    }

    @Override
    public Employee deserialize(File jsonFile) {
        Employee employee = null;
        ObjectMapper objectMapper = Mapper.get();
        try {
            employee = objectMapper.readValue(jsonFile, Employee.class);
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
        return employee;
    }
}
