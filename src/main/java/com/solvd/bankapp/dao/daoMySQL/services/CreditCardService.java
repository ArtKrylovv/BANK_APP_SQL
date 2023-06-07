package com.solvd.bankapp.dao.daoMySQL.services;

import com.solvd.bankapp.dao.daoMySQL.impl.CreditCardDaoImpl;
import com.solvd.bankapp.dao.iCreditCardDao;
import com.solvd.bankapp.model.CreditCardAccount;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.List;

public class CreditCardService {
    private iCreditCardDao creditCardDao = new CreditCardDaoImpl();
    private static final Logger LOGGER = LogManager.getLogger(CreditCardService.class);

    public CreditCardAccount get(long id) throws SQLException {
        if (id <= 0) {
            LOGGER.error("invalid id argument");
            throw new IllegalArgumentException("Id must be int >=1");
        }
        return creditCardDao.get(id);
    }

    public List<CreditCardAccount> getAll() throws SQLException {
        return creditCardDao.getAll();
    }

    public int create(CreditCardAccount creditCardAccount) throws SQLException {
        return creditCardDao.create(creditCardAccount);
    }

    public int update(CreditCardAccount creditCardAccount) throws SQLException {
        return creditCardDao.update (creditCardAccount);
    }

    public int delete(long id) throws SQLException {
        if (id <= 0) {
            LOGGER.error("invalid id argument");
            throw new IllegalArgumentException("Id must be int >=1");
        }
        return creditCardDao.delete(id);
    }
}

