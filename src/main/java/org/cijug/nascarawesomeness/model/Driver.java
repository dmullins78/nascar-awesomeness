package org.cijug.nascarawesomeness.model;


public class Driver implements Comparable< Driver>{

    private Integer points;
    private Integer wins;
    private String name;

    public Driver(String name) {
        this.name = name;
    }

    public Driver(String name, int points, int wins) {
        this.name = name;
        this.points = points;
        this.wins = wins;
    }

    public Integer getWins() {
        return wins;
    }

    public void setWins(Integer wins) {
        this.wins = wins;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    
    public int compareTo(Driver o) {
        if(points > o.getPoints()) {
            return -1;
        }
        else if(points < o.getPoints()) {
            return 1;
        }
        else {
            return name.compareTo(o.getName());
        }
    }

}
