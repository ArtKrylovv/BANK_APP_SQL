package com.solvd.bankapp.utils.jsonparser;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.solvd.bankapp.bin.Employee;

public interface Mapper {
     public static ObjectMapper get() {
         ObjectMapper objectMapper = new ObjectMapper();
         return objectMapper;
     }
}
