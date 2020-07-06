package de.fraulyoner.timetracker;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

class TimeEntryTest {

    @Test
    void ensureCanNotBeInitializedWithoutStart() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new TimeEntry(null, LocalDateTime.now(), "#ISSUE-123", "very important things");
        });

    }

    @Test
    void ensureCanNotBeInitializedWithoutEnd() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new TimeEntry(LocalDateTime.now(), null, "#ISSUE-123", "very important things");
        });

    }

    @Test
    void ensureCanNotBeInitializedWithoutDescription() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new TimeEntry(LocalDateTime.now(), LocalDateTime.now().plusHours(1), "#ISSUE-123", null);
        });

    }

    @Test
    void ensureCanNotBeInitializedWithEmptyDescription() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new TimeEntry(LocalDateTime.now(), LocalDateTime.now().plusHours(1), "#ISSUE-123", "");
        });

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new TimeEntry(LocalDateTime.now(), LocalDateTime.now().plusHours(1), "#ISSUE-123", " ");
        });

    }

    @Test
    void ensureCanNotBeInitializedWithoutIssue() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new TimeEntry(LocalDateTime.now(), LocalDateTime.now().plusHours(1),null, "very important things");
        });

    }

    @Test
    void ensureCanNotBeInitializedWithEmptyIssue() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new TimeEntry(LocalDateTime.now(), LocalDateTime.now().plusHours(1), "", "very important things");
        });

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new TimeEntry(LocalDateTime.now(), LocalDateTime.now().plusHours(1),  " ", "very important things");
        });

    }

    @Test
    void ensureCanNotBeInitializedIfStartIsAfterEnd() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new TimeEntry(LocalDateTime.now().plusHours(1), LocalDateTime.now(), "#ISSUE-123", "very important things");
        });

    }

    @Test
    void ensureCanNotBeInitializedIfStartEqualsEnd() {

        LocalDateTime now = LocalDateTime.now();

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new TimeEntry(now, now, "#ISSUE-123", "very important things");
        });

    }

    @Test
    void ensureCanNotBeInitializedIfStartAndEndNotTheSameDay() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new TimeEntry(LocalDateTime.now(), LocalDateTime.now().plusDays(1), "#ISSUE-123", "very important things");
        });

    }

    @Test
    void ensureCanBeInitializedWithValidFields() {

        TimeEntry timeEntry = new TimeEntry(LocalDateTime.now(), LocalDateTime.now().plusHours(1), "#ISSUE-123", "Something very important");

        Assertions.assertNotNull(timeEntry.getStart(), "Start is missing");
        Assertions.assertNotNull(timeEntry.getEnd(), "End is missing");
        Assertions.assertNotNull(timeEntry.getIssue(), "Issue is missing");
        Assertions.assertNotNull(timeEntry.getDescription(), "Description is missing");
    }

    @Test
    void ensureCanGetCorrectStartTime() {

        LocalDate today = LocalDate.now();

        TimeEntry timeEntry = new TimeEntry(today.atTime(8, 30), today.atTime(9,0), "#ISSUE-123", "Meeting");

        Assertions.assertNotNull(timeEntry.getStartTime(), "Start time is missing");
        Assertions.assertEquals("08:30", timeEntry.getStartTime(), "Wrong start time");
    }

    @Test
    void ensureCanGetCorrectEndTime() {

        LocalDate today = LocalDate.now();

        TimeEntry timeEntry = new TimeEntry(today.atTime(8, 30), today.atTime(9,0), "#ISSUE-123", "Meeting");

        Assertions.assertNotNull(timeEntry.getEndTime(), "End time is missing");
        Assertions.assertEquals("09:00", timeEntry.getEndTime(), "Wrong end time");
    }

    @Test
    void ensureCanGetCorrectDay() {

        LocalDate date = LocalDate.of(2020, 6, 23);

        TimeEntry timeEntry = new TimeEntry(date.atTime(8, 30), date.atTime(9,0), "#ISSUE-123", "Meeting");

        Assertions.assertNotNull(timeEntry.getDay(), "Day is missing");
        Assertions.assertEquals("23.06.2020", timeEntry.getDay(), "Wrong day");
    }

    @Test
    void ensureCanGetCorrectDuration() {

        LocalDate today = LocalDate.now();

        TimeEntry timeEntry = new TimeEntry(today.atTime(8, 30), today.atTime(10,0), "#ISSUE-123", "Meeting");
        Assertions.assertEquals(1.5, timeEntry.getDuration(), "Wrong duration");

        timeEntry = new TimeEntry(today.atTime(8, 30), today.atTime(9,0), "#ISSUE-123", "Meeting");
        Assertions.assertEquals(0.5, timeEntry.getDuration(), "Wrong duration");

        timeEntry = new TimeEntry(today.atTime(8, 30), today.atTime(8,45), "#ISSUE-123", "Meeting");
        Assertions.assertEquals(0.25, timeEntry.getDuration(), "Wrong duration");

        timeEntry = new TimeEntry(today.atTime(8, 30), today.atTime(9,30), "#ISSUE-123", "Meeting");
        Assertions.assertEquals(1, timeEntry.getDuration(), "Wrong duration");

    }
}
