package de.fraulyoner.timetracker;

import org.springframework.util.Assert;

import java.util.Objects;

class TimeTrackActivity {
    private final String issue;
    private final String description;

    TimeTrackActivity(String issue, String description) {

        Assert.hasText(issue, "Issue must not be empty");
        Assert.hasText(description, "Description must not be empty");

        this.issue = issue;
        this.description = description;
    }

    String getIssue() {
        return issue;
    }

    String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TimeTrackActivity that = (TimeTrackActivity) o;
        return Objects.equals(issue, that.issue) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(issue, description);
    }
}
