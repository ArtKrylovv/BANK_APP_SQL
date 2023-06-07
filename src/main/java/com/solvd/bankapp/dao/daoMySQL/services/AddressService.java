package com.solvd.bankapp.dao.daoMySQL.services;

import com.solvd.bankapp.dao.daoMySQL.impl.AddressDaoImpl;
import com.solvd.bankapp.dao.daoMySQL.impl.StateDaoImpl;
import com.solvd.bankapp.dao.iAddressDao;
import com.solvd.bankapp.dao.iStateDao;
import com.solvd.bankapp.model.Address;
import com.solvd.bankapp.model.State;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AddressService {
    private iAddressDao addressDao = new AddressDaoImpl();
    private iStateDao stateDao = new StateDaoImpl();
    private static final Logger LOGGER = LogManager.getLogger(AddressService.class);

    public Address get(int id, boolean full) throws SQLException {
        if (id <= 0) {
            LOGGER.error("invalid id argument");
            throw new IllegalArgumentException("Id must be int >=1");
        }
        Address address = null;
        if (full) {
            address = addressDao.get(id);
            int stateId = addressDao.getStateIdByAddressId(id);
            State state = stateDao.get(stateId);
            address.setState(state);
        } else {
            address = addressDao.get(id);
        }
        return address;
    }

    public List<Address> getAll(boolean full) throws SQLException {
        List<Address> addressesList = new ArrayList<>();
        if (full) {
            for (Address address : addressDao.getAll()) {
                int stateId = addressDao.getStateIdByAddressId(address.getId());
                address.setState(stateDao.get(stateId));
                addressesList.add(address);
            }
        } else {
            addressesList = addressDao.getAll();
        }
        return addressesList;
    }

    public int create(Address address) throws SQLException {
        return addressDao.create(address);
    }

    public int update(Address address) throws SQLException {
        return addressDao.update(address);
    }

    public int delete(int id) throws SQLException {
        if (id <= 0) {
            LOGGER.error("invalid id argument");
            throw new IllegalArgumentException("Id must be int >=1");
        }
        return addressDao.delete(id);

    }

    public int getStateIdByAddressId(int id) throws SQLException {
        if (id <= 0) {
            LOGGER.error("invalid id argument");
            throw new IllegalArgumentException("Id must be int >=1");
        }
        return addressDao.getStateIdByAddressId(id);
    }
}

