package de.fraulyoner.timetracker;

import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.util.Assert;

import javax.persistence.Entity;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
class TimeEntry extends AbstractPersistable<Integer> {

    // TODO: Think about if this class is the right place for doing date formatting things
    private static final String FORMAT_PATTERN_TIME = "HH:mm";
    private static final String FORMAT_PATTERN_DATE = "yyyy-MM-dd";

    private LocalDateTime start;
    private LocalDateTime end;
    private LocalDate day;
    private String issue;
    private String description;

    // Just for Hibernate, do not use this in code
    private TimeEntry() {}

    TimeEntry(LocalDateTime start, LocalDateTime end, String issue, String description) {
        Assert.notNull(start, "Start must not be null");
        Assert.notNull(end, "End must not be null");
        Assert.hasText(issue, "Issue must not be empty");
        Assert.hasText(description, "Description must not be empty");
        Assert.isTrue(start.isBefore(end), "Start must be before end");
        Assert.isTrue(start.toLocalDate().equals(end.toLocalDate()), "Start and end must be on the same day");

        this.start = start;
        this.end = end;
        this.day = start.toLocalDate();
        this.issue = issue;
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

    String getIssue() {
        return issue;
    }

    LocalDate getDay() {
        return day;
    }

    String getStartTimeAsString() {

        return start.format(DateTimeFormatter.ofPattern(FORMAT_PATTERN_TIME));
    }

    String getEndTimeAsString() {

        return end.format(DateTimeFormatter.ofPattern(FORMAT_PATTERN_TIME));
    }

    String getDayAsString() {

        return start.format(DateTimeFormatter.ofPattern(FORMAT_PATTERN_DATE));
    }

    float getDuration() {

        Duration duration = Duration.between(start, end);
        long durationInMinutes = duration.toMinutes();

        return (float) durationInMinutes / 60;
    }

    @Override
    public String toString() {
        return "TimeEntry {" +
                "day=" + getDayAsString() +
                ", start=" + getStartTimeAsString() +
                ", end=" + getEndTimeAsString() +
                ", issue=" + issue +
                ", description=" + description +
                '}';
    }
}
