package de.fraulyoner.timetracker;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
class DummyTimeEntryProvider implements TimeEntryProvider {

    @Override
    public List<TimeEntry> getAllTimeEntriesForDay(LocalDate day) {

        List<TimeEntry> dummyEntries= new ArrayList<>();

        dummyEntries.add(new TimeEntry(day.atTime(8, 30), day.atTime(9, 0), "Daily"));
        dummyEntries.add(new TimeEntry(day.atTime(9, 0), day.atTime(10, 0), "Organisation und Co."));

        return dummyEntries;
    }

}
