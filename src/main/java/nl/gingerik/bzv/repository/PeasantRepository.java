package nl.gingerik.bzv.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import nl.gingerik.bzv.model.Peasant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class PeasantRepository {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    protected JdbcTemplate jdbc;

    public Peasant getById(long id) {
        return jdbc.queryForObject("SELECT * FROM bzv_peasants WHERE id=?", mapper, id);
    }

    public List<Peasant> getAll() {
        return jdbc.query("SELECT * FROM bzv_peasants", mapper);
    }

    private static final RowMapper<Peasant> mapper = new RowMapper<Peasant>() {
        public Peasant mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Peasant(rs.getLong("id"),
            		rs.getInt("year"), rs.getString("name"));
        }
    };

} 