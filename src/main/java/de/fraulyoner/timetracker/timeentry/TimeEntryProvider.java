package de.fraulyoner.timetracker.timeentry;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

interface TimeEntryProvider {

    List<TimeEntry> getAllTimeEntriesForDay(LocalDate day);

    void addOrUpdateTimeEntry(TimeEntry timeEntry);

    Optional<TimeEntry> getById(Integer id);
}
