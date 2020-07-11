package de.fraulyoner.timetracker.timeentry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
class TimeEntryProviderImpl implements TimeEntryProvider {

    private final TimeEntryDao timeEntryDao;

    @Autowired
    TimeEntryProviderImpl(TimeEntryDao timeEntryDao) {
        this.timeEntryDao = timeEntryDao;
    }

    @Override
    public List<TimeEntry> getAllTimeEntriesForDay(LocalDate day) {

        return timeEntryDao.findByDayOrderByStartTime(day);
    }

    @Override
    public List<LocalDate> getAllWorkDays() {
        return timeEntryDao.findAllDays();
    }

    @Override
    public void addOrUpdateTimeEntry(TimeEntry timeEntry) {
        timeEntryDao.save(timeEntry);
    }

    @Override
    public Optional<TimeEntry> getById(Integer id) {
        return timeEntryDao.findById(id);
    }

}
