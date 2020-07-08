package de.fraulyoner.timetracker;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

class WorkDayTest {

    private final List<TimeEntry> sampleTimeEntries = new ArrayList<>();
    private final LocalDate date = LocalDate.of(2020, 7, 8);

    @BeforeEach
    void setUp() {

        TimeEntry daily = new TimeEntry(date.atTime(8, 30), date.atTime(9, 0), "123", "Daily");
        TimeEntry orga1 = new TimeEntry(date.atTime(9, 0), date.atTime(10, 0), "123", "Orga");
        TimeEntry intern = new TimeEntry(date.atTime(10, 0), date.atTime(11, 15), "456", "Interna");
        TimeEntry orga2 = new TimeEntry(date.atTime(11, 15), date.atTime(11, 45), "123", "Orga");

        sampleTimeEntries.add(daily);
        sampleTimeEntries.add(orga1);
        sampleTimeEntries.add(intern);
        sampleTimeEntries.add(orga2);

    }

    @Test
    void ensureReturnsCorrectDay() {

        WorkDay workDay = new WorkDay(date, sampleTimeEntries);

        Assertions.assertNotNull(workDay.getDay(), "Missing day");
        Assertions.assertEquals(LocalDate.of(2020, 7, 8), workDay.getDay(), "Wrong day");

    }

    @Test
    void ensureReturnsCorrectWorkDuration() {

        WorkDay workDay = new WorkDay(date, sampleTimeEntries);

        Assertions.assertEquals(3.25, workDay.getWorkDuration(), "Wrong working duration");

    }

    @Test
    void ensureReturnsCorrectTimeTrackProposals() {

        WorkDay workDay = new WorkDay(date, sampleTimeEntries);
        List<TimeTrackProposal> proposals = workDay.getTrackingProposals();

        Assertions.assertNotNull(proposals, "Missing tracking proposals");
        Assertions.assertEquals(3, proposals.size(), "Wrong number of tracking proposals");

        // Daily
        TimeTrackProposal daily = proposals.get(0);
        Assertions.assertEquals("123", daily.getIssue(), "Wrong issue");
        Assertions.assertEquals("Daily", daily.getDescription(), "Wrong description");
        Assertions.assertEquals(0.5, daily.getDuration(), "Wrong duration");

        // Orga
        TimeTrackProposal orga = proposals.get(1);
        Assertions.assertEquals("123", orga.getIssue(), "Wrong issue");
        Assertions.assertEquals("Orga", orga.getDescription(), "Wrong description");
        Assertions.assertEquals(1.5, orga.getDuration(), "Wrong duration");

        // Interna
        TimeTrackProposal intern = proposals.get(2);
        Assertions.assertEquals("456", intern.getIssue(), "Wrong issue");
        Assertions.assertEquals("Interna", intern.getDescription(), "Wrong description");
        Assertions.assertEquals(1.25, intern.getDuration(), "Wrong duration");

    }
}
