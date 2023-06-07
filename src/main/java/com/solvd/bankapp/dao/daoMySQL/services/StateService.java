package com.solvd.bankapp.dao.daoMySQL.services;

import com.solvd.bankapp.dao.daoMySQL.impl.AddressDaoImpl;
import com.solvd.bankapp.dao.daoMySQL.impl.StateDaoImpl;
import com.solvd.bankapp.dao.iAddressDao;
import com.solvd.bankapp.dao.iStateDao;
import com.solvd.bankapp.model.Address;
import com.solvd.bankapp.model.State;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StateService {
    private iStateDao stateDao = new StateDaoImpl();
    private static final Logger LOGGER = LogManager.getLogger(StateService.class);

    public State get(int id) throws SQLException {
        if (id <= 0) {
            LOGGER.error("invalid id argument");
            throw new IllegalArgumentException("Id must be int >=1");
        }
        return stateDao.get(id);
    }

    public List<State> getAll() throws SQLException {
        return stateDao.getAll();
    }

    public int create(State state) throws SQLException {
        return stateDao.create(state);
    }

    public int update(State state) throws SQLException {
        return stateDao.update (state);
    }

    public int delete(int id) throws SQLException {
        if (id <= 0) {
            LOGGER.error("invalid id argument");
            throw new IllegalArgumentException("Id must be int >=1");
        }
        return stateDao.delete(id);
    }
}

