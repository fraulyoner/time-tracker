package de.fraulyoner.timetracker.timeentry;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ TYPE, TYPE_USE, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = { ValidDurationValidator.class })
@Documented
public @interface ValidDuration {

    String message() default "Non valid duration";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
