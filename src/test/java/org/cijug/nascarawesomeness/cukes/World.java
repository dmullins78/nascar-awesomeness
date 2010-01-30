package org.cijug.nascarawesomeness.cukes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.cijug.nascarawesomeness.model.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class World {
    public JdbcTemplate jdbc;

    public void clear(String table) {
        jdbc.update("delete from " + table);
    }
    
    public void addDriver(Map d) {
        jdbc.update("insert into drivers (name, points, wins) values (?,?,?)", d.get("driver"), d.get("points"), d .get("wins"));
    }
    
    public void addPoints(int place, int points) {
        jdbc.update("insert into points values  (?, ?)", place, points);
    }
    
    public List convertDriversToHash(List<Driver> drivers) {
        List hashes = new ArrayList();
        for(Driver driver : drivers) {
            hashes.add(hash(driver));
        }

        return hashes;
    }
    
    public Map hash(Driver driver) {
        Map map = new HashMap();
        map.put("driver",  driver.getName());
        map.put("points",  "" +  driver.getPoints());
        return map;
    }


    @Autowired
    public void setDataSource(DataSource ds) {
        jdbc = new JdbcTemplate(ds);
    }

}