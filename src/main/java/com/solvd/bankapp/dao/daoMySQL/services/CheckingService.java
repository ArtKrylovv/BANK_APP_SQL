package com.solvd.bankapp.dao.daoMySQL.services;

import com.solvd.bankapp.dao.daoMySQL.impl.CheckingDaoImpl;
import com.solvd.bankapp.dao.daoMySQL.impl.StateDaoImpl;
import com.solvd.bankapp.dao.iCheckingDao;
import com.solvd.bankapp.dao.iStateDao;
import com.solvd.bankapp.model.CheckingAccount;
import com.solvd.bankapp.model.State;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.List;

public class CheckingService {
    private iCheckingDao checkingDao = new CheckingDaoImpl();
    private static final Logger LOGGER = LogManager.getLogger(CheckingService.class);

    public CheckingAccount get(long id) throws SQLException {
        if (id <= 0) {
            LOGGER.error("invalid id argument");
            throw new IllegalArgumentException("Id must be int >=1");
        }
        return checkingDao.get(id);
    }

    public List<CheckingAccount> getAll() throws SQLException {
        return checkingDao.getAll();
    }

    public int create(CheckingAccount checkingAccount) throws SQLException {
        return checkingDao.create(checkingAccount);
    }

    public int update(CheckingAccount checkingAccount) throws SQLException {
        return checkingDao.update (checkingAccount);
    }

    public int delete(long id) throws SQLException {
        if (id <= 0) {
            LOGGER.error("invalid id argument");
            throw new IllegalArgumentException("Id must be int >=1");
        }
        return checkingDao.delete(id);
    }
}

