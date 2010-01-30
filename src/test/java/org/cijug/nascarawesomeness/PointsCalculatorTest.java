package org.cijug.nascarawesomeness;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.cijug.nascarawesomeness.model.Driver;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


public class PointsCalculatorTest {

    @Mock
    private PointsDao pointsDao;
    
    private PointsCalculator calculator;
    
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        
        calculator = new PointsCalculator();
        calculator.setPointsDao(pointsDao);
    }

    @Test
    public void big_winner() {
        when(pointsDao.getPoints(1)).thenReturn(185);
        
        int points = calculator.determinePoints(1, true);
        assertEquals(195, points);
    }

    @Test
    public void cup_finalists() {
        when(pointsDao.getAllDrivers()).thenReturn(padDrivers(20, new Driver("Jimmy", 1000, 2)));
        
        List<Driver> topDrivers = calculator.getRaceForTheCupDrivers();
        assertEquals(12, topDrivers.size());
        
        assertEquals(5020, topDrivers.get(0).getPoints().intValue());
    }

    @Test
    public void cup_finalists_same_score() {
        Driver d1 = new Driver("Jimmy", 1000, 2);
        Driver d2 = new Driver("David", 1000, 2);
        when(pointsDao.getAllDrivers()).thenReturn(padDrivers(20, d1, d2));
        
        List<Driver> topDrivers = calculator.getRaceForTheCupDrivers();
        assertEquals(12, topDrivers.size());
        
        Driver topDriver = topDrivers.get(0);
        assertEquals(d2, topDriver);
    }
    
    @Test(expected=PeopleFinallyRealizedNascarSucksException.class)
    public void not_enough_cup_finalists() {
        when(pointsDao.getAllDrivers()).thenReturn(padDrivers(5, new Driver("Jimmy", 1000, 2)));
        
        calculator.getRaceForTheCupDrivers();
        fail();
    }

    @Test
    public void trim_based_on_season_points() {

    }

    private List<Driver> padDrivers(int field, Driver... drivers) {
        List<Driver> arrayList = new ArrayList<Driver>(Arrays.asList(drivers));
        for(int a = drivers.length; a < field; a++) {
            arrayList.add(new Driver("D" + a, a, 0));
        }
        
        return arrayList;
    }

}
