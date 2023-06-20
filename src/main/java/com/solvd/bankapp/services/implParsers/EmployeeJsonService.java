package com.solvd.bankapp.services.implParsers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.solvd.bankapp.bin.Employee;
import com.solvd.bankapp.services.parsers.IEmployeeJsonService;
import com.solvd.bankapp.utils.jsonparser.Mapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;

public class EmployeeJsonService implements IEmployeeJsonService {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public String serialize(Employee employee) {
        String jsonString = null;
        ObjectMapper objectMapper = Mapper.get();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        objectMapper.enable(SerializationFeature.WRAP_ROOT_VALUE);
        try {
            jsonString = objectMapper.writeValueAsString(employee);
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
        return jsonString;
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
