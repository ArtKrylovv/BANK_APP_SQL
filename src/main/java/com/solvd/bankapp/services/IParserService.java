package com.solvd.bankapp.services;


public interface IParserService <T> {
    T getResult(String uri);
    String validate(String uriXml);
}
