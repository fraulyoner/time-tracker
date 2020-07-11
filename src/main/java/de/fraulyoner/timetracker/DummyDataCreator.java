package de.fraulyoner.timetracker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.LocalTime;

@Service
@ConditionalOnProperty(name = "testdata.create",
        havingValue = "true")
class DummyDataCreator {

    private static final Logger LOG = LoggerFactory.getLogger(DummyDataCreator.class);

    private final TimeEntryDao timeEntryDao;

    @Autowired
    DummyDataCreator(TimeEntryDao timeEntryDao) {
        this.timeEntryDao = timeEntryDao;
    }

    @PostConstruct
    void createTestData() {

        LocalDate day = LocalDate.now();

        LOG.info("Creating test data for " + day.toString());

        createTimeEntry(new TimeEntry(day, LocalTime.of(8, 30), LocalTime.of(9, 0), "Daily"));
        createTimeEntry(new TimeEntry(day, LocalTime.of(9, 0), LocalTime.of(10, 0), "Organisation und Co."));
        createTimeEntry(new TimeEntry(day, LocalTime.of(13, 15), LocalTime.of(14, 0), "Organisation und Co."));

    }

    void createTimeEntry(TimeEntry timeEntry) {

        timeEntryDao.save(timeEntry);
        LOG.info("Created time entry: " + timeEntry.toString());

    }

}
