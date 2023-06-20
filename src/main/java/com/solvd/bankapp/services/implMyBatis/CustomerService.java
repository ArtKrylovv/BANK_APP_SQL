package com.solvd.bankapp.services.implMyBatis;

import com.solvd.bankapp.bin.Customer;
import com.solvd.bankapp.mappers.CustomerMapper;
import com.solvd.bankapp.services.mysql.ICustomerService;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class CustomerService implements ICustomerService {
    private static final String CONFIG = "mybatis-config.xml";
    private static final Logger LOGGER = LogManager.getLogger(CustomerService.class);

    @Override
    public Customer readFromDb(int id) {
        throw new UnsupportedOperationException("use overloaded method with boolean type");
    }

    @Override
    public List<Customer> readAllFromDb() {
        throw new UnsupportedOperationException("use overloaded method with boolean type");
    }

    @Override
    public int writeToDb(Customer customer) {
        try (InputStream stream = Resources.getResourceAsStream(CONFIG);
             SqlSession session = new SqlSessionFactoryBuilder().build(stream).openSession()) {
            CustomerMapper customerMapper = session.getMapper(CustomerMapper.class);
            customerMapper.createCustomer(customer);
            session.commit();
        } catch (IOException e) {
            LOGGER.info(e.getMessage());
        }
        return 1;
    }

    @Override
    public int updateInDb(Customer customer) {
        try (InputStream stream = Resources.getResourceAsStream(CONFIG);
             SqlSession session = new SqlSessionFactoryBuilder().build(stream).openSession()) {
            CustomerMapper customerMapper = session.getMapper(CustomerMapper.class);
            customerMapper.updateCustomer(customer);
            session.commit();
        } catch (IOException e) {
            LOGGER.info(e.getMessage());
        }
        return 1;
    }

    @Override
    public int removeFromDb(int id) {
        try (InputStream stream = Resources.getResourceAsStream(CONFIG);
             SqlSession session = new SqlSessionFactoryBuilder().build(stream).openSession()) {
            CustomerMapper customerMapper = session.getMapper(CustomerMapper.class);
            customerMapper.deleteCustomer(id);
            session.commit();
        } catch (IOException e) {
            LOGGER.info(e.getMessage());
        }
        return 1;
    }

    @Override
    public Customer readFromDb(int ssn, boolean full) {
        Customer customer = null;
        if (full) {
            try (InputStream stream = Resources.getResourceAsStream(CONFIG);
                 SqlSession session = new SqlSessionFactoryBuilder().build(stream).openSession()) {
                CustomerMapper customerMapper = session.getMapper(CustomerMapper.class);
                customer = customerMapper.selectCustomerByIdFull(ssn);
            } catch (IOException e) {
                LOGGER.info(e.getMessage());
            }
        } else {
            try (InputStream stream = Resources.getResourceAsStream(CONFIG);
                 SqlSession session = new SqlSessionFactoryBuilder().build(stream).openSession()) {
                CustomerMapper customerMapper = session.getMapper(CustomerMapper.class);
                customer = customerMapper.selectCustomerById(ssn);
            } catch (IOException e) {
                LOGGER.info(e.getMessage());
            }
        }
        return customer;
    }


    @Override
    public List<Customer> readAllFromDb(boolean full) {
        List<Customer> customerList = null;
        if (full) {
            try (InputStream stream = Resources.getResourceAsStream(CONFIG);
                 SqlSession session = new SqlSessionFactoryBuilder().build(stream).openSession()) {
                CustomerMapper customerMapper = session.getMapper(CustomerMapper.class);
                customerList = customerMapper.selectAllFull();
            } catch (IOException e) {
                LOGGER.info(e.getMessage());
            }
        } else {
            try (InputStream stream = Resources.getResourceAsStream(CONFIG);
                 SqlSession session = new SqlSessionFactoryBuilder().build(stream).openSession()) {
                CustomerMapper customerMapper = session.getMapper(CustomerMapper.class);
                customerList = customerMapper.selectAll();
            } catch (IOException e) {
                LOGGER.info(e.getMessage());
            }
        }
        return customerList;
    }
}
