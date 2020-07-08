package de.fraulyoner.timetracker;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class TimeTrackActivityTest {


    @Test
    void ensureCanNotBeInitializedWithNullIssue() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new TimeTrackActivity(null, "Orga");
        });

    }

    @Test
    void ensureCanNotBeInitializedWithEmptyIssue() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new TimeTrackActivity("", "Orga");
        });

    }

    @Test
    void ensureCanNotBeInitializedWithNullDescription() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new TimeTrackActivity("123", null);
        });

    }

    @Test
    void ensureCanNotBeInitializedWithEmptyDescription() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new TimeTrackActivity("123", "");
        });

    }

    @Test
    void ensureEqualsIfSameIssueAndDescription() {

        TimeTrackActivity a1 = new TimeTrackActivity("123", "Orga");
        TimeTrackActivity a2 = new TimeTrackActivity("123", "Orga");

        Assertions.assertTrue(a1.equals(a2));

    }

    @Test
    void ensureNotEqualsIfDifferentIssue() {

        TimeTrackActivity a1 = new TimeTrackActivity("123", "Orga");
        TimeTrackActivity a2 = new TimeTrackActivity("456", "Orga");

        Assertions.assertFalse(a1.equals(a2));

    }

    @Test
    void ensureNotEqualsIfDifferentDescription() {

        TimeTrackActivity a1 = new TimeTrackActivity("123", "Orga");
        TimeTrackActivity a2 = new TimeTrackActivity("123", "Daily");

        Assertions.assertFalse(a1.equals(a2));

    }
}
