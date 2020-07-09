package de.fraulyoner.timetracker;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * NOTE: Default constructor and getters and setters must be public so fields can be filled within form
 */
class TimeEntryDto {

    private LocalTime startTime;
    private LocalTime endTime;
    private LocalDate day;
    private String issue;
    private String description;

    public TimeEntryDto() {
        /* OK */
    }

    TimeEntryDto(LocalDate day) {

        this.day = day;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public LocalDate getDay() {
        return day;
    }

    public void setDay(LocalDate day) {
        this.day = day;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    TimeEntry toTimeEntry() {
        return new TimeEntry(day, startTime, endTime, issue, description);
    }
}
