package de.fraulyoner.timetracker;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class WebConfigTest {

    @Test
    void ensureCanCovertStringToLocalDate() {
        WebConfig.StringToLocalDateConverter converter = new WebConfig.StringToLocalDateConverter("dd.MM.yyyy");

        LocalDate date = converter.convert("23.07.2020");

        Assertions.assertNotNull(date, "Should be a date");
        Assertions.assertEquals(LocalDate.of(2020, 7, 23), date);
    }

    @Test
    void ensureCanCovertLocalDateToString() {
        WebConfig.LocalDateToStringConverter converter = new WebConfig.LocalDateToStringConverter("dd.MM.yyyy");

        LocalDate date = LocalDate.of(2020, 7, 23);
        String dateString = converter.convert(date);

        Assertions.assertNotNull(dateString, "Should be textual date");
        Assertions.assertEquals("23.07.2020", dateString);
    }

    @Test
    void ensureDoesNotBreakOnEmptyString() {
        WebConfig.StringToLocalDateConverter converter = new WebConfig.StringToLocalDateConverter("dd.MM.yyyy");

        LocalDate date = converter.convert("");

        Assertions.assertNull(date);
    }

    @Test
    void ensureDoesNotBreakOnNullDate() {
        WebConfig.LocalDateToStringConverter converter = new WebConfig.LocalDateToStringConverter("dd.MM.yyyy");

        String dateString = converter.convert(null);

        Assertions.assertNotNull(dateString);
        Assertions.assertEquals("", dateString);
    }
}
