package de.fraulyoner.timetracker.timeentry;

import de.fraulyoner.timetracker.timeentry.TimeEntry;
import de.fraulyoner.timetracker.timeentry.TimeEntryController;
import de.fraulyoner.timetracker.timeentry.TimeEntryProvider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TimeEntryController.class)
class TimeEntryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TimeEntryProvider timeEntryProvider;

    @Test
    void indexShouldContainWorkDays() throws Exception {

        List<LocalDate> days = Arrays.asList(LocalDate.of(2020, 6, 20), LocalDate.of(2020, 7, 1));
        when(timeEntryProvider.getAllWorkDays()).thenReturn(days);

        mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("2020-06-20")))
                .andExpect(content().string(containsString("2020-07-01")));

        verify(timeEntryProvider).getAllWorkDays();
    }

    @Test
    void trackingShouldContainEntryInformation() throws Exception {

        LocalDate day = LocalDate.of(2020, 6, 23);
        List<TimeEntry> entries = Collections.singletonList(new TimeEntry(day, LocalTime.of(8, 30), LocalTime.of(9, 0), "Daily"));

        when(timeEntryProvider.getAllTimeEntriesForDay(any(LocalDate.class))).thenReturn(entries);

        mockMvc.perform(get("/entries").param("date", "2020-06-23")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("2020-06-23")))
                .andExpect(content().string(containsString("08:30")))
                .andExpect(content().string(containsString("09:00")))
                .andExpect(content().string(containsString("Daily")));

    }

    @Test
    void trackingWithoutExplicitDateShouldFetchEntriesForToday() throws Exception {

        LocalDate today = LocalDate.now();
        List<TimeEntry> entries = Collections.singletonList(new TimeEntry(today, LocalTime.of(8, 30), LocalTime.of(9, 0), "Daily"));

        when(timeEntryProvider.getAllTimeEntriesForDay(any(LocalDate.class))).thenReturn(entries);

        mockMvc.perform(get("/entries")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("08:30")))
                .andExpect(content().string(containsString("09:00")))
                .andExpect(content().string(containsString("Daily")));

    }

    @Test
    void addNewEntryShouldFillModel() throws Exception {

        mockMvc.perform(get("/entries/new").param("date", "2020-06-23")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("timeEntry"))
                .andExpect(content().string(containsString("name=\"day\" value=\"2020-06-23\"")));

    }

    @Test
    void addNewEntryWithoutExplicitDateShouldWork() throws Exception {

        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        mockMvc.perform(get("/entries/new")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("timeEntry"))
                .andExpect(content().string(containsString("name=\"day\" value=\"" + date + "\"")));

    }

}
