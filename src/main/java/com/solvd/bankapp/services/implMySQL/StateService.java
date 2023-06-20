package com.solvd.bankapp.services.implMySQL;

import com.solvd.bankapp.dao.impl.StateDaoImpl;
import com.solvd.bankapp.dao.IStateDao;
import com.solvd.bankapp.bin.State;
import com.solvd.bankapp.services.mysql.IStateService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class StateService implements IStateService {
    private IStateDao stateDao = new StateDaoImpl();
    private static final Logger LOGGER = LogManager.getLogger(StateService.class);

    public State readFromDb(int id) {
        if (id <= 0) {
            LOGGER.error("invalid id argument");
            throw new IllegalArgumentException("Id must be int >=1");
        }
        return stateDao.get(id);
    }

    public List<State> readAllFromDb() {
        return stateDao.getAll();
    }

    public int writeToDb(State state) {
        if (state != null) {
            return stateDao.create(state);
        } else {
            LOGGER.error("invalid argument");
            throw new NullPointerException();
        }
    }

    public int updateInDb(State state) {
        if (state != null) {
            return stateDao.update(state);
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
        return stateDao.delete(id);
    }
}

