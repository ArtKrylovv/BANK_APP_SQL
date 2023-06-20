package com.solvd.bankapp.services.implMySQL;

import com.solvd.bankapp.dao.impl.AddressDaoImpl;
import com.solvd.bankapp.dao.impl.StateDaoImpl;
import com.solvd.bankapp.dao.IAddressDao;
import com.solvd.bankapp.dao.IStateDao;
import com.solvd.bankapp.bin.Address;
import com.solvd.bankapp.bin.State;
import com.solvd.bankapp.services.mysql.IAddressService;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.util.ArrayList;
import java.util.List;

public class AddressService implements IAddressService {
    private IAddressDao addressDao = new AddressDaoImpl();
    private IStateDao stateDao = new StateDaoImpl();
    private static final Logger LOGGER = LogManager.getLogger(AddressService.class);

    @Override
    public Address readFromDb(int id) {
        throw new UnsupportedOperationException("use overloaded method with boolean type");
    }

    @Override
    public List<Address> readAllFromDb() {
        throw new UnsupportedOperationException("use overloaded method with boolean type");
    }

    public Address readFromDb(int id, boolean full) {
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

    public List<Address> readAllFromDb(boolean full) {
        List<Address> addressesList = new ArrayList<>();
        if (full) {
            List<Address> finalAddressesList = addressesList;
            addressDao.getAll().forEach(address ->  {
                int stateId = addressDao.getStateIdByAddressId(address.getId());
                address.setState(stateDao.get(stateId));
                finalAddressesList.add(address);
            });
        } else {
            addressesList = addressDao.getAll();
        }
        return addressesList;
    }

    public int writeToDb(Address address) {
        if (address != null) {
            return addressDao.create(address);
        } else {
            LOGGER.error("invalid argument");
            throw new NullPointerException();
        }
    }

    public int updateInDb(Address address) {
        if (address != null) {
            return addressDao.update(address);
        } else {
            LOGGER.error("invalid argument");
            throw new NullPointerException();
        }
    }

    public int removeFromDb(int id) {
        if (id <= 0) {
            LOGGER.error("invalid id argument");
            throw new IllegalArgumentException("Id must be int >=1");
        }
        return addressDao.delete(id);

    }

    public int getStateIdByAddressId(int id) {
        if (id <= 0) {
            LOGGER.error("invalid id argument");
            throw new IllegalArgumentException("Id must be int >=1");
        }
        return addressDao.getStateIdByAddressId(id);
    }
}

