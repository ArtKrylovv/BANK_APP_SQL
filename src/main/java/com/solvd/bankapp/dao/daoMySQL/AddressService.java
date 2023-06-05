package com.solvd.bankapp.dao.daoMySQL;

import com.solvd.bankapp.dao.iAddressDao;
import com.solvd.bankapp.dao.iStateDao;
import com.solvd.bankapp.model.Address;
import com.solvd.bankapp.model.State;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AddressService {
    private iAddressDao addressDao = new AddressDaoImpl();
    private iStateDao stateDao = new StateDaoImpl();

    public Address get(int id) throws SQLException {
        Address address = null;
        int stateId = addressDao.getStateIdByAddressId(id);
        State state = stateDao.get(stateId);
        address = addressDao.get(id);
        address.setState(state);
        return address;
    }

    public List<Address> getAll() throws SQLException {
        List<Address> addressesList = new ArrayList<>();
        for (Address address : addressDao.getAll()) {
            int stateId = addressDao.getStateIdByAddressId(address.getId());
            address.setState(stateDao.get(stateId));
            addressesList.add(address);
        }
        return addressesList;
    }
}

