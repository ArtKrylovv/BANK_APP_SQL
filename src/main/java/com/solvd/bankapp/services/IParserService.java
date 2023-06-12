package com.solvd.bankapp.services;


public interface IParserService <T> {
    T getResult(String uri);
    String validate(String uriXml);
    void marshall(T t);
    T unmarshall(String xmlPath);
}
