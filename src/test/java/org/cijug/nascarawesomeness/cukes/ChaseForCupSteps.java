package org.cijug.nascarawesomeness.cukes;

import java.util.List;
import java.util.Map;

import org.cijug.nascarawesomeness.PointsCalculator;
import org.cijug.nascarawesomeness.model.Driver;
import org.springframework.beans.factory.annotation.Autowired;

import cuke4duke.Given;
import cuke4duke.Table;
import cuke4duke.Then;
import cuke4duke.When;
import cuke4duke.spring.StepDefinitions;

@StepDefinitions
public class ChaseForCupSteps {
    
    @Autowired private World world;

    @Autowired private PointsCalculator calculator;

    private List<Driver> drivers;
    
    @Given("^The current driver standings$")
    public void theCurrentDriverStandingsWithTable(Table table) {
        world.clear("drivers");
        
        for(Map hash : table.hashes()) {
            world.addDriver(hash);
        }
    }

    @When("^The cup starts$")
    public void theCupStarts() {
        drivers = calculator.getRaceForTheCupDrivers();
    }

    @Then("^The updated standings should be$")
    public void theUpdatedStandingsShouldBeWithTable(Table table) {    
        List driverHashes = world.convertDriversToHash(drivers);
        table.diffHashes(driverHashes);
    }
    
}
