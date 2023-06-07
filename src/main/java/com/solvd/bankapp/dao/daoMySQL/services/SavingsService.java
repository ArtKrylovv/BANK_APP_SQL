package com.solvd.bankapp.dao.daoMySQL.services;

import com.solvd.bankapp.dao.daoMySQL.impl.SavingsDaoImpl;
import com.solvd.bankapp.dao.daoMySQL.impl.StateDaoImpl;
import com.solvd.bankapp.dao.iSavingsDao;
import com.solvd.bankapp.dao.iStateDao;
import com.solvd.bankapp.model.SavingsAccount;
import com.solvd.bankapp.model.State;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.List;

public class SavingsService {
    private iSavingsDao savingsDao = new SavingsDaoImpl();
    private static final Logger LOGGER = LogManager.getLogger(SavingsService.class);

    public SavingsAccount get(long id) throws SQLException {
        if (id <= 0) {
            LOGGER.error("invalid id argument");
            throw new IllegalArgumentException("Id must be int >=1");
        }
        return savingsDao.get(id);
    }

    public List<SavingsAccount> getAll() throws SQLException {
        return savingsDao.getAll();
    }

    public int create(SavingsAccount savingsAccount) throws SQLException {
        return savingsDao.create(savingsAccount);
    }

    public int update(SavingsAccount savingsAccount) throws SQLException {
        return savingsDao.update (savingsAccount);
    }

    public int delete(long id) throws SQLException {
        if (id <= 0) {
            LOGGER.error("invalid id argument");
            throw new IllegalArgumentException("Id must be int >=1");
        }
        return savingsDao.delete(id);
    }
}

