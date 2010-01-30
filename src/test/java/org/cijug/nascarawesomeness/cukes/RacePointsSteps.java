package org.cijug.nascarawesomeness.cukes;

import static org.junit.Assert.assertEquals;

import org.cijug.nascarawesomeness.PointsCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cuke4duke.Given;
import cuke4duke.Pending;
import cuke4duke.Then;
import cuke4duke.When;
import cuke4duke.spring.StepDefinitions;

@StepDefinitions
public class RacePointsSteps {

    private int points;
    
    private int placement;

    private boolean leadMostLaps;

    @Autowired private World world;
    
    @Autowired private PointsCalculator calculator;

    @Given("^The race winner gets \"([^\"]*)\" points$")
    public void theRaceWinnerGetsPoints(int points) {
        placement = 1;
        
        world.clear("points");
        world.addPoints(1, 185);
    }

    @Given("^lead the most laps$")
    public void leadTheMostLaps() {
        leadMostLaps = true;
    }

    @When("^the race ends$")
    public void theRaceEnds() {
        points = calculator.determinePoints(placement, leadMostLaps);
    }

    @Then("^The point total should be \"(.*)\"")
    public void thePointTotalShouldBe(int total) {
        assertEquals(total, points);
    }

}
