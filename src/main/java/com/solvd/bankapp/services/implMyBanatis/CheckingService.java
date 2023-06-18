package com.solvd.bankapp.services.implMyBanatis;

import com.solvd.bankapp.bin.CheckingAccount;
import com.solvd.bankapp.bin.State;
import com.solvd.bankapp.mappers.CheckingMapper;
import com.solvd.bankapp.mappers.StateMapper;
import com.solvd.bankapp.services.mysql.ICheckingService;
import com.solvd.bankapp.services.mysql.IStateService;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class CheckingService implements ICheckingService {
    private static final String CONFIG = "mybatis-config.xml";
    private static final Logger LOGGER = LogManager.getLogger(CheckingService.class);

    @Override
    public CheckingAccount readFromDb(int id) throws IOException {
        throw new UnsupportedOperationException("use overloaded method with long type");
    }

    @Override
    public List<CheckingAccount> readAllFromDb() {
        List<CheckingAccount> checkingAccountList = null;
        try (InputStream stream = Resources.getResourceAsStream(CONFIG);
             SqlSession session = new SqlSessionFactoryBuilder().build(stream).openSession()) {
            CheckingMapper checkingMapper = session.getMapper(CheckingMapper.class);
            checkingAccountList = checkingMapper.selectAccountAll();
        } catch (IOException e) {
            LOGGER.info(e.getMessage());
        }
        return checkingAccountList;
    }

    @Override
    public int writeToDb(CheckingAccount checkingAccount) {
        try (InputStream stream = Resources.getResourceAsStream(CONFIG);
             SqlSession session = new SqlSessionFactoryBuilder().build(stream).openSession()) {
            CheckingMapper checkingMapper = session.getMapper(CheckingMapper.class);
            checkingMapper.createChecking(checkingAccount);
            session.commit();
        } catch (IOException e) {
            LOGGER.info(e.getMessage());
        }
        return 1;
    }

    @Override
    public int updateInDb(CheckingAccount checkingAccount) {
        try (InputStream stream = Resources.getResourceAsStream(CONFIG);
             SqlSession session = new SqlSessionFactoryBuilder().build(stream).openSession()) {
            CheckingMapper checkingMapper = session.getMapper(CheckingMapper.class);
            checkingMapper.updateChecking(checkingAccount);
            session.commit();
        } catch (IOException e) {
            LOGGER.info(e.getMessage());
        }
        return 1;
    }

    @Override
    public int removeFromDb(int id) {
        throw new UnsupportedOperationException("use overloaded method with long type");
    }

    @Override
    public int removeFromDb(long id) {
        try (InputStream stream = Resources.getResourceAsStream(CONFIG);
             SqlSession session = new SqlSessionFactoryBuilder().build(stream).openSession()) {
            CheckingMapper checkingMapper = session.getMapper(CheckingMapper.class);
            checkingMapper.deleteChecking(id);
            session.commit();
        } catch (IOException e) {
            LOGGER.info(e.getMessage());
        }
        return 1;
    }

    @Override
    public CheckingAccount readFromDb(long id) {
        CheckingAccount checkingAccount = null;
        try (InputStream stream = Resources.getResourceAsStream(CONFIG);
             SqlSession session = new SqlSessionFactoryBuilder().build(stream).openSession()) {
            CheckingMapper checkingMapper = session.getMapper(CheckingMapper.class);
            checkingAccount = checkingMapper.selectAccountById(id);
        } catch (IOException e) {
            LOGGER.info(e.getMessage());
        }
        return checkingAccount;
    }
}
