package de.fraulyoner.timetracker;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

class TimeEntryTest {

    @Test
    void ensureCanNotBeInitializedWithoutStart() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new TimeEntry(null, LocalDateTime.now(), "very important things");
        });

    }

    @Test
    void ensureCanNotBeInitializedWithoutEnd() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new TimeEntry(LocalDateTime.now(), null, "very important things");
        });

    }

    @Test
    void ensureCanNotBeInitializedWithoutDescription() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new TimeEntry(LocalDateTime.now(), LocalDateTime.now().plusHours(1), null);
        });

    }

    @Test
    void ensureCanNotBeInitializedWithEmptyDescription() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new TimeEntry(LocalDateTime.now(), LocalDateTime.now().plusHours(1), "");
        });

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new TimeEntry(LocalDateTime.now(), LocalDateTime.now().plusHours(1), " ");
        });

    }

    @Test
    void ensureCanNotBeInitializedIfStartIsAfterEnd() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new TimeEntry(LocalDateTime.now().plusHours(1), LocalDateTime.now(), "very important things");
        });

    }

    @Test
    void ensureCanNotBeInitializedIfStartEqualsEnd() {

        LocalDateTime now = LocalDateTime.now();

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new TimeEntry(now, now, "very important things");
        });

    }

    @Test
    void ensureCanNotBeInitializedIfStartAndEndNotTheSameDay() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new TimeEntry(LocalDateTime.now(), LocalDateTime.now().plusDays(1), "very important things");
        });

    }

    @Test
    void ensureCanBeInitializedWithValidStartEndAndDescription() {

        TimeEntry timeEntry = new TimeEntry(LocalDateTime.now(), LocalDateTime.now().plusHours(1), "Something very important");

        Assertions.assertNotNull(timeEntry.getStart(), "Start is missing");
        Assertions.assertNotNull(timeEntry.getEnd(), "End is missing");
        Assertions.assertNotNull(timeEntry.getDescription(), "Description is missing");
    }

    @Test
    void ensureCanGetCorrectStartTime() {

        LocalDate date = LocalDate.of(2020, 6, 23);

        TimeEntry timeEntry = new TimeEntry(date.atTime(8, 30), date.atTime(9,0), "Meeting");

        Assertions.assertNotNull(timeEntry.getStartTime(), "Start time is missing");
        Assertions.assertEquals("08:30", timeEntry.getStartTime(), "Wrong start time");
    }

    @Test
    void ensureCanGetCorrectEndTime() {

        LocalDate date = LocalDate.of(2020, 6, 23);

        TimeEntry timeEntry = new TimeEntry(date.atTime(8, 30), date.atTime(9,0), "Meeting");

        Assertions.assertNotNull(timeEntry.getEndTime(), "End time is missing");
        Assertions.assertEquals("09:00", timeEntry.getEndTime(), "Wrong end time");
    }

    @Test
    void ensureCanGetCorrectDay() {

        LocalDate date = LocalDate.of(2020, 6, 23);

        TimeEntry timeEntry = new TimeEntry(date.atTime(8, 30), date.atTime(9,0), "Meeting");

        Assertions.assertNotNull(timeEntry.getDay(), "Day is missing");
        Assertions.assertEquals("23.06.2020", timeEntry.getDay(), "Wrong day");
    }
}
