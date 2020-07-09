package de.fraulyoner.timetracker;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

class TimeEntryTest {

    @Test
    void ensureCanNotBeInitializedWithoutDay() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new TimeEntry(null, LocalTime.now(), LocalTime.now().plusHours(1),"123", "very important things");
        });

    }

    @Test
    void ensureCanNotBeInitializedWithoutStart() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new TimeEntry( LocalDate.now(), null, LocalTime.now(), "#ISSUE-123", "very important things");
        });

    }

    @Test
    void ensureCanNotBeInitializedWithoutEnd() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new TimeEntry(LocalDate.now(), LocalTime.now(), null, "#ISSUE-123", "very important things");
        });

    }

    @Test
    void ensureCanNotBeInitializedWithoutDescription() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new TimeEntry(LocalDate.now(), LocalTime.now(), LocalTime.now().plusHours(1), "#ISSUE-123", null);
        });

    }

    @Test
    void ensureCanNotBeInitializedWithEmptyDescription() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new TimeEntry(LocalDate.now(), LocalTime.now(), LocalTime.now().plusHours(1), "#ISSUE-123", "");
        });

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new TimeEntry(LocalDate.now(), LocalTime.now(), LocalTime.now().plusHours(1), "#ISSUE-123", " ");
        });

    }

    @Test
    void ensureCanNotBeInitializedWithoutIssue() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new TimeEntry(LocalDate.now(), LocalTime.now(), LocalTime.now().plusHours(1),null, "very important things");
        });

    }

    @Test
    void ensureCanNotBeInitializedWithEmptyIssue() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new TimeEntry(LocalDate.now(), LocalTime.now(), LocalTime.now().plusHours(1), "", "very important things");
        });

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new TimeEntry(LocalDate.now(), LocalTime.now(), LocalTime.now().plusHours(1),  " ", "very important things");
        });

    }

    @Test
    void ensureCanNotBeInitializedIfStartIsAfterEnd() {

        LocalTime now = LocalTime.now();

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new TimeEntry(LocalDate.now(), now.plusHours(1), now, "#ISSUE-123", "very important things");
        });

    }

    @Test
    void ensureCanNotBeInitializedIfStartEqualsEnd() {

        LocalTime now = LocalTime.now();

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new TimeEntry(LocalDate.now(), now, now, "#ISSUE-123", "very important things");
        });

    }

    @Test
    void ensureCanBeInitializedWithValidFields() {

        TimeEntry timeEntry = new TimeEntry(LocalDate.now(), LocalTime.now(), LocalTime.now().plusHours(1), "#ISSUE-123", "Something very important");

        Assertions.assertNotNull(timeEntry.getDay(), "Day is missing");
        Assertions.assertNotNull(timeEntry.getStartTime(), "Start time is missing");
        Assertions.assertNotNull(timeEntry.getEndTime(), "End time is missing");
        Assertions.assertNotNull(timeEntry.getIssue(), "Issue is missing");
        Assertions.assertNotNull(timeEntry.getDescription(), "Description is missing");
    }

    @Test
    void ensureDayIsSetCorrectly() {

        LocalDate date = LocalDate.of(2020, 6, 23);

        TimeEntry timeEntry = new TimeEntry(date, LocalTime.of(8, 30), LocalTime.of(9,0), "#ISSUE-123", "Meeting");

        Assertions.assertNotNull(timeEntry.getDay(), "Day is missing");
        Assertions.assertEquals(date, timeEntry.getDay(), "Wrong day");
    }

    @Test
    void ensureCanGetCorrectDuration() {

        LocalDate today = LocalDate.now();

        TimeEntry timeEntry = new TimeEntry(today, LocalTime.of(8, 30), LocalTime.of(10,0), "#ISSUE-123", "Meeting");
        Assertions.assertEquals(1.5, timeEntry.getDuration(), "Wrong duration");

        timeEntry = new TimeEntry(today, LocalTime.of(8, 30), LocalTime.of(9,0), "#ISSUE-123", "Meeting");
        Assertions.assertEquals(0.5, timeEntry.getDuration(), "Wrong duration");

        timeEntry = new TimeEntry(today, LocalTime.of(8, 30), LocalTime.of(8,45), "#ISSUE-123", "Meeting");
        Assertions.assertEquals(0.25, timeEntry.getDuration(), "Wrong duration");

        timeEntry = new TimeEntry(today, LocalTime.of(8, 30), LocalTime.of(9,30), "#ISSUE-123", "Meeting");
        Assertions.assertEquals(1, timeEntry.getDuration(), "Wrong duration");

    }
}
