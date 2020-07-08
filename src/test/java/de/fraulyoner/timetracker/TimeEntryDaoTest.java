package de.fraulyoner.timetracker;

import org.apache.tomcat.jni.Local;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class TimeEntryDaoTest {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private TimeEntryDao timeEntryDao;

    @Test
    void injectedComponentsAreNotNull(){

        Assertions.assertNotNull(dataSource);
        Assertions.assertNotNull(jdbcTemplate);
        Assertions.assertNotNull(entityManager);
        Assertions.assertNotNull(timeEntryDao);
    }

    @Test
    void ensureCanFindTimeEntriesForDay() {

        LocalDate date1 = LocalDate.of(2020, 7, 1);
        LocalDate date2 = LocalDate.of(2020, 7, 8);
        LocalDate date3 = LocalDate.of(2020, 5, 22);

        TimeEntry timeEntry1 = new TimeEntry(date1.atTime(8, 30), date1.atTime(8, 45), "123", "Daily");
        TimeEntry timeEntry2 = new TimeEntry(date2.atTime(8, 30), date2.atTime(8, 45), "123", "Daily");
        TimeEntry timeEntry3 = new TimeEntry(date3.atTime(8, 30), date3.atTime(8, 45), "123", "Daily");

        timeEntryDao.save(timeEntry1);
        timeEntryDao.save(timeEntry2);
        timeEntryDao.save(timeEntry3);

        List<TimeEntry> timeEntries = timeEntryDao.findByDay(date1);

        Assertions.assertNotNull(timeEntries, "Missing time entries");
        Assertions.assertEquals(1, timeEntries.size(), "Wrong number of time entries");
        Assertions.assertEquals(date1.atTime(8, 30), timeEntries.get(0).getStart(), "Wrong time entry");
    }

    @Test
    void ensureCanFindAllDays() {

        LocalDate date1 = LocalDate.of(2020, 7, 1);
        LocalDate date2 = LocalDate.of(2020, 7, 8);
        LocalDate date3 = LocalDate.of(2020, 8, 22);

        TimeEntry timeEntry1 = new TimeEntry(date1.atTime(8, 30), date1.atTime(8, 45), "123", "Daily");
        TimeEntry timeEntry2 = new TimeEntry(date2.atTime(8, 30), date2.atTime(8, 45), "123", "Daily");
        TimeEntry timeEntry3 = new TimeEntry(date3.atTime(8, 30), date3.atTime(8, 45), "123", "Daily");
        TimeEntry timeEntry4 = new TimeEntry(date3.atTime(10, 30), date3.atTime(11, 0), "123", "Foo");

        timeEntryDao.save(timeEntry1);
        timeEntryDao.save(timeEntry2);
        timeEntryDao.save(timeEntry3);
        timeEntryDao.save(timeEntry4);

        List<LocalDate> allDays = timeEntryDao.findAllDays();

        Assertions.assertNotNull(allDays, "Missing days");
        Assertions.assertEquals(3, allDays.size(), "Wrong number of days");
        Assertions.assertEquals(date1, allDays.get(0), "Wrong day");
        Assertions.assertEquals(date2, allDays.get(1), "Wrong day");
        Assertions.assertEquals(date3, allDays.get(2), "Wrong day");
    }

}