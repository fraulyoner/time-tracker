package de.fraulyoner.timetracker;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

interface TimeEntryProvider {

    List<TimeEntry> getAllTimeEntriesForDay(LocalDate day);

    List<LocalDate> getAllWorkDays();

    void addOrUpdateTimeEntry(TimeEntry timeEntry);

    Optional<TimeEntry> getById(Integer id);
}
