package de.fraulyoner.timetracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
class TimeEntryProviderImpl implements TimeEntryProvider {

    private final TimeEntryDao timeEntryDao;

    @Autowired
    TimeEntryProviderImpl(TimeEntryDao timeEntryDao) {
        this.timeEntryDao = timeEntryDao;
    }

    @Override
    public List<TimeEntry> getAllTimeEntriesForDay(LocalDate day) {

        return timeEntryDao.findByDay(day);
    }

    @Override
    public List<LocalDate> getAllWorkDays() {
        return timeEntryDao.findAllDays();
    }

}
