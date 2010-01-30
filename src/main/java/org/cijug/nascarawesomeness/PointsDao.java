package org.cijug.nascarawesomeness;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.cijug.nascarawesomeness.model.Driver;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

@Repository
public class PointsDao extends JdbcDaoSupport {

    public int getPoints(int place) {
        return getJdbcTemplate().queryForInt("select points from points where place = ?", place);
    }

    public List<Driver> getAllDrivers() {
        return getJdbcTemplate().query("select * from drivers", new ParameterizedRowMapper<Driver>() {
            @Override
            public Driver mapRow(ResultSet rs, int arg1) throws SQLException {
                Driver driver = new Driver(rs.getString("name"));
                driver.setPoints(rs.getInt("points"));
                driver.setWins(rs.getInt("wins"));
                return driver;
            }
        });
    }

}
