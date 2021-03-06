package de.fraulyoner.timetracker.timeentry;

import de.fraulyoner.timetracker.timeentry.TimeEntry;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

class TimeEntryTest {

    @Test
    void ensureCanNotBeInitializedWithoutDay() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new TimeEntry(null, LocalTime.now(), LocalTime.now().plusHours(1), "very important things");
        });

    }

    @Test
    void ensureCanNotBeInitializedWithoutStart() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new TimeEntry( LocalDate.now(), null, LocalTime.now(), "very important things");
        });

    }

    @Test
    void ensureCanNotBeInitializedWithoutEnd() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new TimeEntry(LocalDate.now(), LocalTime.now(), null, "very important things");
        });

    }

    @Test
    void ensureCanNotBeInitializedWithoutDescription() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new TimeEntry(LocalDate.now(), LocalTime.now(), LocalTime.now().plusHours(1), null);
        });

    }

    @Test
    void ensureCanNotBeInitializedWithEmptyDescription() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new TimeEntry(LocalDate.now(), LocalTime.now(), LocalTime.now().plusHours(1), "");
        });

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new TimeEntry(LocalDate.now(), LocalTime.now(), LocalTime.now().plusHours(1), " ");
        });

    }

    @Test
    void ensureCanNotBeInitializedIfStartIsAfterEnd() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new TimeEntry(LocalDate.now(), LocalTime.of(11,0), LocalTime.of(10, 0), "very important things");
        });

    }

    @Test
    void ensureCanNotBeInitializedIfStartEqualsEnd() {

        LocalTime now = LocalTime.now();

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new TimeEntry(LocalDate.now(), now, now, "very important things");
        });

    }

    @Test
    void ensureCanBeInitializedWithValidFields() {

        TimeEntry timeEntry = new TimeEntry(LocalDate.now(), LocalTime.of(10, 0), LocalTime.of(11, 0), "Something very important");

        Assertions.assertNotNull(timeEntry.getDay(), "Day is missing");
        Assertions.assertNotNull(timeEntry.getStartTime(), "Start time is missing");
        Assertions.assertNotNull(timeEntry.getEndTime(), "End time is missing");
        Assertions.assertNotNull(timeEntry.getDescription(), "Description is missing");
    }

    @Test
    void ensureDayIsSetCorrectly() {

        LocalDate date = LocalDate.of(2020, 6, 23);

        TimeEntry timeEntry = new TimeEntry(date, LocalTime.of(8, 30), LocalTime.of(9,0), "Meeting");

        Assertions.assertNotNull(timeEntry.getDay(), "Day is missing");
        Assertions.assertEquals(date, timeEntry.getDay(), "Wrong day");
    }

    @Test
    void ensureCanGetCorrectDuration() {

        LocalDate today = LocalDate.now();

        TimeEntry timeEntry = new TimeEntry(today, LocalTime.of(8, 30), LocalTime.of(10,0), "Meeting");
        Assertions.assertEquals(1.5, timeEntry.getDuration(), "Wrong duration");

        timeEntry = new TimeEntry(today, LocalTime.of(8, 30), LocalTime.of(9,0), "Meeting");
        Assertions.assertEquals(0.5, timeEntry.getDuration(), "Wrong duration");

        timeEntry = new TimeEntry(today, LocalTime.of(8, 30), LocalTime.of(8,45), "Meeting");
        Assertions.assertEquals(0.25, timeEntry.getDuration(), "Wrong duration");

        timeEntry = new TimeEntry(today, LocalTime.of(8, 30), LocalTime.of(9,30), "Meeting");
        Assertions.assertEquals(1, timeEntry.getDuration(), "Wrong duration");

    }
}
