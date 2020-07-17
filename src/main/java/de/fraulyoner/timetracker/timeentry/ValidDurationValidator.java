package de.fraulyoner.timetracker.timeentry;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalTime;

/**
 * Ensure this class is public!
 */
public class ValidDurationValidator implements ConstraintValidator<ValidDuration, TimeEntry> {

    @Override
    public void initialize(ValidDuration constraintAnnotation) {
        // OK
    }

    @Override
    public boolean isValid(TimeEntry timeEntry, ConstraintValidatorContext constraintValidatorContext) {

        if ( timeEntry == null ) {
            return true;
        }

        LocalTime startTime = timeEntry.getStartTime();
        LocalTime endTime = timeEntry.getEndTime();

        if(startTime == null | endTime == null) {
            return false;
        }

        return startTime.isBefore(endTime);

    }
}
