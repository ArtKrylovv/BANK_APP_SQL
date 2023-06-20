package com.solvd.bankapp.services.implMySQL;

import com.solvd.bankapp.dao.impl.CheckingDaoImpl;
import com.solvd.bankapp.dao.ICheckingDao;
import com.solvd.bankapp.bin.CheckingAccount;
import com.solvd.bankapp.services.mysql.ICheckingService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.List;

public class CheckingService implements ICheckingService {
    private ICheckingDao checkingDao = new CheckingDaoImpl();
    private static final Logger LOGGER = LogManager.getLogger(CheckingService.class);

    @Override
    public CheckingAccount readFromDb(int id) {
        throw new UnsupportedOperationException("use overloaded method with long type");
    }

    @Override
    public int removeFromDb(int id) {
        throw new UnsupportedOperationException("use overloaded method with long type");
    }

    public CheckingAccount readFromDb(long id) {
        if (id <= 0) {
            LOGGER.error("invalid id argument");
            throw new IllegalArgumentException("Id must be int >=1");
        }
        return checkingDao.get(id);
    }

    public List<CheckingAccount> readAllFromDb() {
        return checkingDao.getAll();
    }

    public int writeToDb(CheckingAccount checkingAccount) {
        if (checkingAccount != null) {
            return checkingDao.create(checkingAccount);
        } else {
            LOGGER.error("invalid argument");
            throw new NullPointerException();
        }
    }

    public int updateInDb(CheckingAccount checkingAccount) {
        if (checkingAccount != null) {
            return checkingDao.update(checkingAccount);
        } else {
            LOGGER.error("invalid argument");
            throw new NullPointerException();
        }
    }

    public int removeFromDb(long id) {
        if (id <= 0) {
            LOGGER.error("invalid id argument");
            throw new IllegalArgumentException("Id must be int >=1");
        }
        return checkingDao.delete(id);
    }
}

