package com.solvd.bankapp.services.implMySQl;

import com.solvd.bankapp.dao.impl.SavingsDaoImpl;
import com.solvd.bankapp.dao.ISavingsDao;
import com.solvd.bankapp.bin.SavingsAccount;
import com.solvd.bankapp.services.mysql.ISavingsService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class SavingsService implements ISavingsService {
    private ISavingsDao savingsDao = new SavingsDaoImpl();
    private static final Logger LOGGER = LogManager.getLogger(SavingsService.class);

    @Override
    public SavingsAccount readFromDb(int id) {
        throw new UnsupportedOperationException("use overloaded method with long type");
    }

    @Override
    public int removeFromDb(int id) {
        throw new UnsupportedOperationException("use overloaded method with long type");
    }

    public SavingsAccount readFromDb(long id) {
        if (id <= 0) {
            LOGGER.error("invalid id argument");
            throw new IllegalArgumentException("Id must be int >=1");
        }
        return savingsDao.get(id);
    }

    public List<SavingsAccount> readAllFromDb() {
        return savingsDao.getAll();
    }

    public int writeToDb(SavingsAccount savingsAccount) {
        if (savingsAccount != null) {
            return savingsDao.create(savingsAccount);
        } else {
            LOGGER.error("invalid argument");
            throw new NullPointerException();
        }
    }

    public int updateInDb(SavingsAccount savingsAccount) {
        if (savingsAccount != null) {
            return savingsDao.update(savingsAccount);
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
        return savingsDao.delete(id);
    }
}

