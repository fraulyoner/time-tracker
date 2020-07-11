package de.fraulyoner.timetracker;

import org.springframework.util.Assert;

class TimeTrackProposal {

    private final TimeTrackActivity timeTrackActivity;
    private final float duration;

    TimeTrackProposal(TimeTrackActivity timeTrackActivity, float duration) {
        Assert.notNull(timeTrackActivity, "Time track activity must not be null");
        Assert.isTrue(duration > 0, "Duration must be > 0");
        Assert.isTrue(duration <= 24, "Duration must be <= 24");

        this.timeTrackActivity = timeTrackActivity;
        this.duration = duration;
    }

    String getIssue() {
        return timeTrackActivity.getIssue();
    }

    String getDescription() {
        return timeTrackActivity.getDescription();
    }

    String getIdentifier() {

        String description = getDescription().toLowerCase().replace(" ", "-");
        String issue = getIssue().toLowerCase().replace(" ", "-");

        return issue + "-" + description;
    }

    float getDuration() {
        return duration;
    }
}
