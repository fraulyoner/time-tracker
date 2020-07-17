package de.fraulyoner.timetracker.timeentry;

import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.util.Assert;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

@ValidDuration(message = "Start time must be before end time")
@Entity
class TimeEntry extends AbstractPersistable<Integer> {

    @NotNull(message = "Start time is mandatory")
    private LocalTime startTime;

    @NotNull(message = "End time is mandatory")
    private LocalTime endTime;

    @NotNull(message = "Day is mandatory")
    private LocalDate day;

    @NotBlank(message = "Description is mandatory")
    private String description;

    TimeEntry() {
        /* OK */
    }

    TimeEntry(LocalDate day, LocalTime startTime, LocalTime endTime, String description) {
        Assert.notNull(day, "Day must not be null");
        Assert.notNull(startTime, "Start time must not be null");
        Assert.notNull(endTime, "End time must not be null");
        Assert.hasText(description, "Description must not be empty");
        Assert.isTrue(startTime.isBefore(endTime), "Start time must be before end time");

        this.startTime = startTime;
        this.endTime = endTime;
        this.day = day;
        this.description = description;
    }

    public Integer getId() {
        return super.getId();
    }

    public void setId(Integer id) {
        super.setId(id);
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getDuration() {

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
                ", description='" + description + '\'' +
                '}';
    }
}
