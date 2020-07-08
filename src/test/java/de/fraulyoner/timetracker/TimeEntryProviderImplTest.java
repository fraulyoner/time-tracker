package de.fraulyoner.timetracker;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class TimeEntryProviderImplTest {

    private final TimeEntryDao timeEntryDao = Mockito.mock(TimeEntryDao.class);
    private final TimeEntryProvider timeEntryProvider = new TimeEntryProviderImpl(timeEntryDao);

    @Test
    void ensureCanFindTimeEntriesForDayByUsingDao() {

        LocalDate today = LocalDate.now();
        List<TimeEntry> timeEntries = Collections.singletonList(
                new TimeEntry(
                        today.atTime(9, 0),
                        today.atTime(10, 30),
                        "issue123",
                        "very important work")
                );

        Mockito.when(timeEntryDao.findByDay(Mockito.any(LocalDate.class))).thenReturn(timeEntries);

        List<TimeEntry> allTimeEntriesForDay = timeEntryProvider.getAllTimeEntriesForDay(today);

        Mockito.verify(timeEntryDao).findByDay(today);

        Assertions.assertEquals(timeEntries, allTimeEntriesForDay, "Wrong list of time entries");

    }

    @Test
    void ensureCanFindAllWorkDaysByUsingDao() {

        LocalDate today = LocalDate.now();

        Mockito.when(timeEntryDao.findAllDays()).thenReturn(Collections.singletonList(today));

        List<LocalDate> allWorkDays = timeEntryProvider.getAllWorkDays();

        Mockito.verify(timeEntryDao).findAllDays();

        Assertions.assertEquals(allWorkDays.size(), 1, "Wrong number of work days");

    }

}