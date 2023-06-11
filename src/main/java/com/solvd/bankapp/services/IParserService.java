package com.solvd.bankapp.services;


public interface IParserService <T> {
    T getResult(String uri);
    boolean isValid(String uriXml);
}
