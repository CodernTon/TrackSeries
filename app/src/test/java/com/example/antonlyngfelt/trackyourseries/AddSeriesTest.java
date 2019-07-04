package com.example.antonlyngfelt.trackyourseries;

import org.junit.Assert;
import org.junit.Test;
public class AddSeriesTest {
    AddSeries addSeries = new AddSeries();

    @Test
    public void shouldReturnADatabaseHelper() {
        DataBaseHelper dataBaseHelper = addSeries.createDbhandler();
        Assert.assertNotEquals(dataBaseHelper, new DataBaseHelper(addSeries, "bla",null,231));
    }
}
