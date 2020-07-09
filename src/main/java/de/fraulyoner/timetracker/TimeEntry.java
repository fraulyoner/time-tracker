package de.fraulyoner.timetracker;

import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.util.Assert;

import javax.persistence.Entity;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
class TimeEntry extends AbstractPersistable<Integer> {

    private LocalTime startTime;
    private LocalTime endTime;
    private LocalDate day;
    private String issue;
    private String description;

    // Just for Hibernate, do not use this in code
    private TimeEntry() {}

    TimeEntry(LocalDate day, LocalTime startTime, LocalTime endTime, String issue, String description) {
        Assert.notNull(day, "Day must not be null");
        Assert.notNull(startTime, "Start time must not be null");
        Assert.notNull(endTime, "End time must not be null");
        Assert.hasText(issue, "Issue must not be empty");
        Assert.hasText(description, "Description must not be empty");
        Assert.isTrue(startTime.isBefore(endTime), "Start time must be before end time");

        this.startTime = startTime;
        this.endTime = endTime;
        this.day = day;
        this.issue = issue;
        this.description = description;
    }

    LocalTime getStartTime() {
        return startTime;
    }

    LocalTime getEndTime() {
        return endTime;
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

    float getDuration() {

        Duration duration = Duration.between(startTime, endTime);
        long durationInMinutes = duration.toMinutes();

        return (float) durationInMinutes / 60;
    }

    @Override
    public String toString() {
        return "TimeEntry{" +
                "startTime=" + startTime +
                ", endTime=" + endTime +
                ", day=" + day +
                ", issue='" + issue + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
