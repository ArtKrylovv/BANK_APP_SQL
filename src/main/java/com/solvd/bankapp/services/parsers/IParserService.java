package com.solvd.bankapp.services.parsers;


public interface IParserService <T> {
    T getResult(String uri);
    String validate(String uriXml);
    String marshall(T t);
    T unmarshall(String xmlPath);
}
