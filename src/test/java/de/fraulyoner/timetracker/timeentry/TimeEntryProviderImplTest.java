package de.fraulyoner.timetracker.timeentry;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.time.LocalTime;
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
                        today,
                        LocalTime.of(9, 0),
                        LocalTime.of(10, 30),
                        "very important work")
                );

        Mockito.when(timeEntryDao.findByDayOrderByStartTime(Mockito.any(LocalDate.class))).thenReturn(timeEntries);

        List<TimeEntry> allTimeEntriesForDay = timeEntryProvider.getAllTimeEntriesForDay(today);

        Mockito.verify(timeEntryDao).findByDayOrderByStartTime(today);

        Assertions.assertEquals(timeEntries, allTimeEntriesForDay, "Wrong list of time entries");

    }

    @Test
    void ensureCanAddNewTimeEntry() {

        TimeEntry timeEntryMock = Mockito.mock(TimeEntry.class);

        timeEntryProvider.addOrUpdateTimeEntry(timeEntryMock);

        Mockito.verify(timeEntryDao).save(timeEntryMock);
    }
}
