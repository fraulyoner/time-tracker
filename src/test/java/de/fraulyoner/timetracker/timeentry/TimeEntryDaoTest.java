package de.fraulyoner.timetracker.timeentry;

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
import java.time.LocalTime;
import java.util.List;


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

        TimeEntry timeEntry1 = new TimeEntry(date1, LocalTime.of(8, 30), LocalTime.of(8, 45), "Daily");
        TimeEntry timeEntry2 = new TimeEntry(date2, LocalTime.of(8, 30), LocalTime.of(8, 45), "Daily");
        TimeEntry timeEntry3 = new TimeEntry(date3, LocalTime.of(8, 30), LocalTime.of(8, 45), "Daily");

        timeEntryDao.save(timeEntry1);
        timeEntryDao.save(timeEntry2);
        timeEntryDao.save(timeEntry3);

        List<TimeEntry> timeEntries = timeEntryDao.findByDayOrderByStartTime(date1);

        Assertions.assertNotNull(timeEntries, "Missing time entries");
        Assertions.assertEquals(1, timeEntries.size(), "Wrong number of time entries");
        Assertions.assertEquals(LocalTime.of(8, 30), timeEntries.get(0).getStartTime(), "Wrong time entry");
    }

    @Test
    void ensureFoundTimeEntriesForDayAreSortedByStartTime() {

        LocalDate date = LocalDate.of(2020, 7, 1);

        TimeEntry timeEntry1 = new TimeEntry(date, LocalTime.of(8, 30), LocalTime.of(8, 45), "first");
        TimeEntry timeEntry2 = new TimeEntry(date, LocalTime.of(10, 30), LocalTime.of(11, 0), "third");
        TimeEntry timeEntry3 = new TimeEntry(date, LocalTime.of(9, 0), LocalTime.of(9, 30), "second");

        timeEntryDao.save(timeEntry1);
        timeEntryDao.save(timeEntry2);
        timeEntryDao.save(timeEntry3);

        List<TimeEntry> timeEntries = timeEntryDao.findByDayOrderByStartTime(date);

        Assertions.assertNotNull(timeEntries, "Missing time entries");
        Assertions.assertEquals(3, timeEntries.size(), "Wrong number of time entries");
        Assertions.assertEquals(LocalTime.of(8, 30), timeEntries.get(0).getStartTime(), "Wrong time entry");
        Assertions.assertEquals(LocalTime.of(9, 0), timeEntries.get(1).getStartTime(), "Wrong time entry");
        Assertions.assertEquals(LocalTime.of(10, 30), timeEntries.get(2).getStartTime(), "Wrong time entry");
    }

}