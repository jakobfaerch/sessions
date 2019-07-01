package com.example.sessions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.sql.Timestamp;
import java.time.Duration;

@Component
public class SessionsLogger {
    Logger log = LoggerFactory.getLogger(SessionsLogger.class);

    @Autowired
    JdbcTemplate jdbcTemplate;

    @PostConstruct
    public void logRawSessions() {
        jdbcTemplate.query("SELECT userid, starttime, endtime from sessions", rs -> {
            Timestamp start = rs.getTimestamp("starttime");
            Timestamp end = rs.getTimestamp("endtime");
            Duration length = Duration.between(start.toInstant(), end.toInstant());
            log.info("Session: User {}, {} - {}, duration {}", rs.getInt("userid"), start, end, length);
        });
    }
}
