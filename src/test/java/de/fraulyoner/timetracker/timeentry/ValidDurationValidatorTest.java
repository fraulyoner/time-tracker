package de.fraulyoner.timetracker.timeentry;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.LocalTime;

class ValidDurationValidatorTest {

    private ValidDurationValidator validator;
    private ConstraintValidatorContext validatorContextMock;
    private TimeEntry timeEntry;

    @BeforeEach
    void setUp() {
        validator = new ValidDurationValidator();
        validatorContextMock = Mockito.mock(ConstraintValidatorContext.class);
        timeEntry = new TimeEntry(LocalDate.now(),
                LocalTime.of(10, 0),
                LocalTime.of(11, 0),
                "doings");
    }

    @Test
    void ensureNullTimeEntryIsValid() {

        Assertions.assertTrue(validator.isValid(null, validatorContextMock));

    }

    @Test
    void ensureTimeEntryWithValidDurationIsValid() {

        Assertions.assertTrue(validator.isValid(timeEntry, validatorContextMock));

    }

    @Test
    void ensureTimeEntryWithNullStartTimeIsNotValid() {

        timeEntry.setStartTime(null);

        Assertions.assertFalse(validator.isValid(timeEntry, validatorContextMock));

    }

    @Test
    void ensureTimeEntryWithNullEndTimeIsNotValid() {

        timeEntry.setEndTime(null);

        Assertions.assertFalse(validator.isValid(timeEntry, validatorContextMock));

    }

    @Test
    void ensureTimeEntryWithInvalidDurationIsNotValid() {

        timeEntry.setStartTime(LocalTime.of(10, 30));
        timeEntry.setEndTime(LocalTime.of(9, 30));

        Assertions.assertFalse(validator.isValid(timeEntry, validatorContextMock));

    }
}