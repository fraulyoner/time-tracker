package de.fraulyoner.timetracker.timeentry;

import org.springframework.util.Assert;

class TimeTrackProposal {

    private final String description;
    private final float duration;

    TimeTrackProposal(String description, float duration) {
        Assert.hasText(description, "Description must not be empty");
        Assert.isTrue(duration > 0, "Duration must be > 0");
        Assert.isTrue(duration <= 24, "Duration must be <= 24");

        this.description = description;
        this.duration = duration;
    }

    String getDescription() {
        return description;
    }

    String getIdentifier() {

        return getDescription().toLowerCase().replace(" ", "-");
    }

    float getDuration() {
        return duration;
    }
}
