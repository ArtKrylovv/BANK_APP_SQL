package com.solvd.bankapp.services.implMyBanatis;

import com.solvd.bankapp.bin.CheckingAccount;
import com.solvd.bankapp.bin.Customer;
import com.solvd.bankapp.mappers.CheckingMapper;
import com.solvd.bankapp.mappers.CustomerMapper;
import com.solvd.bankapp.services.mysql.ICheckingService;
import com.solvd.bankapp.services.mysql.ICustomerService;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class CustomerSerivce implements ICustomerService {
    private static final String CONFIG = "mybatis-config.xml";
    private static final Logger LOGGER = LogManager.getLogger(CustomerSerivce.class);

    @Override
    public Customer readFromDb(int id) throws IOException {
        Customer customer = null;
        try (InputStream stream = Resources.getResourceAsStream(CONFIG);
             SqlSession session = new SqlSessionFactoryBuilder().build(stream).openSession()) {
            CustomerMapper customerMapper = session.getMapper(CustomerMapper.class);
            customer = customerMapper.selectCustomerById(id);
        } catch (IOException e) {
            LOGGER.info(e.getMessage());
        }
        return customer;
    }


    @Override
    public List<Customer> readAllFromDb() {
        return null;
    }

    @Override
    public int writeToDb(Customer customer) {
        return 0;
    }

    @Override
    public int updateInDb(Customer customer) {
        return 0;
    }

    @Override
    public int removeFromDb(int id) {
        return 0;
    }

    @Override
    public Customer readFromDb(int ssn, boolean full) {
        return null;
    }

    @Override
    public List<Customer> readAllFromDb(boolean full) {
        return null;
    }
}
