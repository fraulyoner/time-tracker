package de.fraulyoner.timetracker;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TimeTrackProposalTest {

    @Test
    void ensureCanNotBeInitializedWithNullActivity() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new TimeTrackProposal(null, (float) 0.5);
        });

    }

    @Test
    void ensureCanNotBeInitializedWithZeroDuration() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new TimeTrackProposal(new TimeTrackActivity("123", "foo"), 0);
        });

    }

    @Test
    void ensureCanNotBeInitializedWithTooBigDuration() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new TimeTrackProposal(new TimeTrackActivity("123", "foo"), (float) 24.5);
        });

    }

    @Test
    void ensureCanBeInitializedWithValidFields() {

        TimeTrackProposal proposal = new TimeTrackProposal(new TimeTrackActivity("123", "foo"), (float) 0.5);

        Assertions.assertEquals(proposal.getDuration(), 0.5, "Wrong duration");
        Assertions.assertEquals(proposal.getIssue(), "123", "Wrong issue");
        Assertions.assertEquals(proposal.getDescription(), "foo", "Wrong description");

    }
}
