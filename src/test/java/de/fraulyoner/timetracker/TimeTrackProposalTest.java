package de.fraulyoner.timetracker;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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
            new TimeTrackProposal("foo", 0);
        });

    }

    @Test
    void ensureCanNotBeInitializedWithTooBigDuration() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new TimeTrackProposal("foo", (float) 24.5);
        });

    }

    @Test
    void ensureCanBeInitializedWithValidFields() {

        TimeTrackProposal proposal = new TimeTrackProposal("foo", (float) 0.5);

        Assertions.assertEquals(proposal.getDuration(), 0.5, "Wrong duration");
        Assertions.assertEquals(proposal.getDescription(), "foo", "Wrong description");

    }

    @Test
    void ensureReturnsCorrectIdentifier() {

        TimeTrackProposal proposal = new TimeTrackProposal("Orga und so", (float) 0.5);

        Assertions.assertEquals("orga-und-so", proposal.getIdentifier(), "Wrong identifier");
    }
}
