package com.solvd.bankapp.services.implMySQl;

import com.solvd.bankapp.dao.impl.CreditCardDaoImpl;
import com.solvd.bankapp.dao.ICreditCardDao;
import com.solvd.bankapp.bin.CreditCardAccount;
import com.solvd.bankapp.services.mysql.ICreditCardService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class CreditCardService implements ICreditCardService {
    private ICreditCardDao creditCardDao = new CreditCardDaoImpl();
    private static final Logger LOGGER = LogManager.getLogger(CreditCardService.class);

    @Override
    public CreditCardAccount readFromDb(int id) {
        throw new UnsupportedOperationException("use overloaded method with long type");
    }

    @Override
    public int removeFromDb(int id) {
        throw new UnsupportedOperationException("use overloaded method with long type");
    }

    public CreditCardAccount readFromDb(long id) {
        if (id <= 0) {
            LOGGER.error("invalid id argument");
            throw new IllegalArgumentException("Id must be int >=1");
        }
        return creditCardDao.get(id);
    }

    public List<CreditCardAccount> readAllFromDb()  {
        return creditCardDao.getAll();
    }

    public int writeToDb(CreditCardAccount creditCardAccount) {
        if (creditCardAccount != null) {
            return creditCardDao.create(creditCardAccount);
        } else {
            LOGGER.error("invalid argument");
            throw new NullPointerException();
        }
    }

    public int updateInDb(CreditCardAccount creditCardAccount){
        if (creditCardAccount != null) {
            return creditCardDao.update(creditCardAccount);
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
        return creditCardDao.delete(id);
    }
}

