package de.fraulyoner.timetracker;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BaseController.class)
class BaseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TimeEntryProvider timeEntryProvider;


    @Test
    void indexWithoutParameterShouldHaveDefault() throws Exception {
        mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Hello World")));
    }

    @Test
    void indexShouldContainName() throws Exception {
        mockMvc.perform(get("/?name=Aljona")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Hello Aljona")));
    }

    @Test
    void trackingShouldContainEntryInformation() throws Exception {

        LocalDate day = LocalDate.of(2020, 6, 23);
        List<TimeEntry> entries = Arrays.asList(new TimeEntry(day.atTime(8, 30), day.atTime(9, 0), "#123","Daily"));

        when(timeEntryProvider.getAllTimeEntriesForDay(any(LocalDate.class))).thenReturn(entries);

        mockMvc.perform(get("/tracking?date=2020-06-23")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("2020-06-23")))
                .andExpect(content().string(containsString("08:30")))
                .andExpect(content().string(containsString("09:00")))
                .andExpect(content().string(containsString("Daily")));

    }

}
