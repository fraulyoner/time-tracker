package de.fraulyoner.timetracker;

import java.time.LocalDate;
import java.util.List;

interface TimeEntryProvider {

    List<TimeEntry> getAllTimeEntriesForDay(LocalDate day);
}
