package de.fraulyoner.timetracker;

import java.time.LocalDate;
import java.util.*;

class WorkDay {

    private final LocalDate day;
    private final List<TimeEntry> timeEntries;
    private final List<TimeTrackProposal> trackingProposals;

    WorkDay(LocalDate date, List<TimeEntry> timeEntries) {
        this.day = date;
        this.timeEntries = timeEntries;
        this.trackingProposals = createProposalsBasedOnTimeEntries(timeEntries);
    }

    private List<TimeTrackProposal> createProposalsBasedOnTimeEntries(List<TimeEntry> timeEntries) {

        List<TimeTrackProposal> proposals = new ArrayList<>();

        Map<TimeTrackActivity, Float> durations = new LinkedHashMap<>();

        for (TimeEntry timeEntry : timeEntries) {
            TimeTrackActivity a = new TimeTrackActivity(timeEntry.getIssue(), timeEntry.getDescription());

            float duration = 0;

            if (durations.containsKey(a)) {
                duration = durations.get(a);
            }

            duration += timeEntry.getDuration();
            durations.put(a, duration);
        }

        for (Map.Entry<TimeTrackActivity, Float> timeTrackActivityEntry : durations.entrySet()) {
            proposals.add(new TimeTrackProposal(timeTrackActivityEntry.getKey(), timeTrackActivityEntry.getValue()));
        }

        return proposals;
    }

    LocalDate getDay() {
        return day;
    }

    float getWorkDuration() {

        float duration = 0;

        for (TimeEntry timeEntry : timeEntries) {
            duration += timeEntry.getDuration();
        }

        return duration;
    }

    List<TimeEntry> getTimeEntries() {
        return timeEntries;
    }

    List<TimeTrackProposal> getTrackingProposals() {
        return trackingProposals;
    }
}
