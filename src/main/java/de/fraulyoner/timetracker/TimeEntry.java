package de.fraulyoner.timetracker;

import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class TimeEntry {

    private static final String FORMAT_PATTERN_TIME = "HH:mm";
    private static final String FORMAT_PATTERN_DATE = "dd.MM.yyyy";

    private LocalDateTime start;
    private LocalDateTime end;
    private String description;

    TimeEntry(LocalDateTime start, LocalDateTime end, String description) {
        Assert.notNull(start, "Start must not be null");
        Assert.notNull(end, "End must not be null");
        Assert.hasText(description, "Description must not be empty");
        Assert.isTrue(start.isBefore(end), "Start must be before end");
        Assert.isTrue(start.toLocalDate().equals(end.toLocalDate()), "Start and end must be on the same day");

        this.start = start;
        this.end = end;
        this.description = description;
    }

    LocalDateTime getStart() {
        return start;
    }

    LocalDateTime getEnd() {
        return end;
    }

    String getDescription() {
        return description;
    }

    String getStartTime() {

        return start.format(DateTimeFormatter.ofPattern(FORMAT_PATTERN_TIME));
    }

    String getEndTime() {

        return end.format(DateTimeFormatter.ofPattern(FORMAT_PATTERN_TIME));
    }

    String getDay() {

        return start.format(DateTimeFormatter.ofPattern(FORMAT_PATTERN_DATE));
    }
}
