package com.solvd.bankapp.handlers;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class DateAdapter extends XmlAdapter<String, Date> {
    public static final Logger LOGGER = LogManager.getLogger(DateAdapter.class);

    @Override
    public Date unmarshal(String dateString) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date parsedDate = null;
        try {
            parsedDate = sdf.parse(dateString);
        } catch (java.text.ParseException e) {
        LOGGER.error(e.getMessage());
        }
        return new Date(parsedDate.getTime());
    }
    @Override
    public String marshal(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }
}
