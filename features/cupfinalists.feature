Feature: Race Points Cdalculator
	In order to determine the nascar winner
	As a nascar official
	I need to get a handle on the point system

  Scenario: Barely won the race
      Given The race winner gets "185" points
      When the race ends
      Then The point total should be "190"      
  
	Scenario: Big Winner
	    Given The race winner gets "185" points
		    And lead the most laps
	    When the race ends
	    Then The point total should be "195"      
    
 	Scenario: Race for the cup finalists
 		Given The current driver standings
			|    driver					     |     points   |    wins    |
			|    Tony Stewart		     |     3694     |    3        |
			|    Jeff Gordon              |     3457     |    1        |
			|    Jimmie Johnson       |     3404      |   3        |
			|    Denny Hamlin		     |     3296     |    1        |
			|    Carl Edwards		     |     3162     |    0        |
			|    Kasey Kahne		     |     3153     |    2        |
			|    Kurt Busch			     |     3152     |    1        |
			|    Juan Montoya		     |     3145     |    0        |
			|    Ryan Newman	     |     3138     |    0        |
			|    Mark Martin		     |     3126     |    4        |
			|    Greg Biffle			     |     3125     |    0        |
			|    Matt Kenseth		     |     3077     |    2        |
			|    Brian Vickers		     |     3057     |    1        |
			|    Kyle Busch               |     3040     |    4        |
			|    David Reutimann     |     2945     |    0        |
		When The cup starts
		Then The updated standings should be
            |   driver                  |   points   |
            |   Mark Martin        |     5040   |
            |   Jimmie Johnson   |    5030    |
            |   Tony Stewart       |    5030    |
            |   Kasey Kahne       |    5020    |
            |   Matt Kenseth       |    5020    |
            |   Denny Hamlin     |     5010    |
            |   Jeff Gordon         |     5010    |
            |   Kurt Busch          |     5010   |
            |   Carl Edwards      |     5000   |
            |   Greg Biffle          |     5000   |
            |   Juan Montoya     |     5000   |
            |   Ryan Newman    |    5000    |
            
            
            		      