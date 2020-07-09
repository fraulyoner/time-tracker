package de.fraulyoner.timetracker;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Configuration
class WebConfig implements WebMvcConfigurer {

    private static final String FORMAT_PATTERN_DATE = "yyyy-MM-dd";

    @Override
    public void addFormatters(FormatterRegistry registry) {

        registry.addConverter(new StringToLocalDateConverter(FORMAT_PATTERN_DATE));
        registry.addConverter(new LocalDateToStringConverter(FORMAT_PATTERN_DATE));
    }

    static class StringToLocalDateConverter implements Converter<String, LocalDate> {

        private final DateTimeFormatter formatter;

        StringToLocalDateConverter(String pattern) {
            formatter = DateTimeFormatter.ofPattern(pattern);
        }

        @Override
        public LocalDate convert(String s) {
            if(s.isEmpty()) {
               return null;
            }

            return LocalDate.parse(s, formatter);
        }
    }

    static class LocalDateToStringConverter implements Converter<LocalDate, String> {

        private final DateTimeFormatter formatter;

        LocalDateToStringConverter(String pattern) {
            formatter = DateTimeFormatter.ofPattern(pattern);
        }

        @Override
        public String convert(LocalDate date) {

            if(date == null) {
                return "";
            }

            return date.format(formatter);
        }
    }
}
