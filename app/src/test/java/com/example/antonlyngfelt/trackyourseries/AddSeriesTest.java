package com.example.antonlyngfelt.trackyourseries;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class AddSeriesTest {

    @Test
    public void firstTestcases(){
        //fail();

        int first = 2;
        int second = 3;
        int result = AddSeries.add(first, second);
        assertEquals(5, result);
    }

}
