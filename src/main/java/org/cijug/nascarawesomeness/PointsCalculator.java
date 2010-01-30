package org.cijug.nascarawesomeness;

import java.util.Collections;
import java.util.List;

import org.cijug.nascarawesomeness.model.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PointsCalculator {
    private static final int TOP_DRIVER_CUTOFF = 12;
    private PointsDao pointsDao;

    public int determinePoints(int finished, boolean mostLaps) {
        int placementPoints = pointsDao.getPoints(finished);
        
        if(finished == 1) {
            placementPoints += 5;
        }
        
        if(mostLaps) {
            placementPoints += 5;
        }
        
        return placementPoints;
    }

    public List<Driver> getRaceForTheCupDrivers()  {
        List<Driver> topDrivers = pointsDao.getAllDrivers();
        
        if(notEnoughRacers(topDrivers)) {
            throw new PeopleFinallyRealizedNascarSucksException("Oh no - the jig is up!");
        }

        List<Driver> finalists = collectTopChaseDrivers(topDrivers);
        rebasePointsAndPosition(finalists);

        return finalists;
    }

    private boolean notEnoughRacers(List<Driver> topDrivers) {
        return topDrivers.size() < TOP_DRIVER_CUTOFF;
    }
    
    private List<Driver> collectTopChaseDrivers(List<Driver> topDrivers) {
        Collections.sort(topDrivers);
        return topDrivers.subList(0, TOP_DRIVER_CUTOFF);
    }
    
    private void rebasePointsAndPosition(List<Driver> topDrivers) {
        for(Driver topDriver : topDrivers) {
            int chasePoints = resetCupPoints(topDriver.getWins());
            topDriver.setPoints(chasePoints);
        }

        Collections.sort(topDrivers);
    }

    private int resetCupPoints(int numberOfWins) {
        int pointsBase = 5000;
        int pointsPerWin = 10;
        
        return pointsBase + (pointsPerWin * numberOfWins);
    }
    
    @Autowired
    public void setPointsDao(PointsDao pointsDao) {
        this.pointsDao = pointsDao;
    }



}
