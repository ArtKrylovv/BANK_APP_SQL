package com.solvd.bankapp.services.implMyBanatis;

import com.solvd.bankapp.bin.State;
import com.solvd.bankapp.mappers.StateMapper;
import com.solvd.bankapp.services.mysql.IStateService;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class StateService implements IStateService {
    private static final String CONFIG = "mybatis-config.xml";
    private static final Logger LOGGER = LogManager.getLogger(StateService.class);

    @Override
    public State readFromDb(int id) {
        State state = null;
        try (InputStream stream = Resources.getResourceAsStream(CONFIG);
             // open new session, optional parameter openSession(true) to autocommit
             SqlSession session = new SqlSessionFactoryBuilder().build(stream).openSession()) {
            // creates instance of State Mapper class
            StateMapper stateMapper = session.getMapper(StateMapper.class);
            // call interface methods, impl is provided automatically
            state = stateMapper.selectStateById(1);
        } catch (IOException e) {
            LOGGER.info(e.getMessage());
        }
        return state;
    }


    @Override
    public List<State> readAllFromDb() {
        List<State> stateList = null;
        try (InputStream stream = Resources.getResourceAsStream(CONFIG);
             // open new session, optional parameter openSession(true) to autocommit
             SqlSession session = new SqlSessionFactoryBuilder().build(stream).openSession()) {
            // creates instance of State Mapper class
            StateMapper stateMapper = session.getMapper(StateMapper.class);
            // call interface methods, impl is provided automatically
            stateList = stateMapper.selectAll();
        } catch (IOException e) {
            LOGGER.info(e.getMessage());
        }
        return stateList;
    }

    @Override
    public int writeToDb(State state) {
        try (InputStream stream = Resources.getResourceAsStream(CONFIG);
             SqlSession session = new SqlSessionFactoryBuilder().build(stream).openSession()) {
            StateMapper stateMapper = session.getMapper(StateMapper.class);
            stateMapper.createState(state);
            session.commit();
        } catch (IOException e) {
            LOGGER.info(e.getMessage());
        }
        return 1;
    }

    @Override
    public int updateInDb(State state) {
        try (InputStream stream = Resources.getResourceAsStream(CONFIG);
             SqlSession session = new SqlSessionFactoryBuilder().build(stream).openSession()) {
            StateMapper stateMapper = session.getMapper(StateMapper.class);
            stateMapper.updateState(state);
            session.commit();
        } catch (IOException e) {
            LOGGER.info(e.getMessage());
        }
        return 1;
    }

    @Override
    public int removeFromDb(int id) {
        try (InputStream stream = Resources.getResourceAsStream(CONFIG);
             SqlSession session = new SqlSessionFactoryBuilder().build(stream).openSession()) {
            StateMapper stateMapper = session.getMapper(StateMapper.class);
            stateMapper.deleteState(id);
            session.commit();
        } catch (IOException e) {
            LOGGER.info(e.getMessage());
        }
        return 1;
    }
}
