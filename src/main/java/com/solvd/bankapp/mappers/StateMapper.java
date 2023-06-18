package com.solvd.bankapp.mappers;

import com.solvd.bankapp.bin.State;

import java.util.List;

public interface StateMapper {
    State selectStateById(int id);
    List<State> selectAll();
    void createState(State state);
    void updateState(State state);
    void deleteState(int id);
}
