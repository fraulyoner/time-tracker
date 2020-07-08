package de.fraulyoner.timetracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Controller
class BaseController {

    private static final String FORMAT_PATTERN_DATE = "yyyy-MM-dd";

    @Autowired
    private TimeEntryProvider timeEntryProvider;

    @GetMapping("/")
    String index(Model model) {
        List<LocalDate> workDays = timeEntryProvider.getAllWorkDays();
        List<String> workDaysAsString = new ArrayList<>();

        workDays.forEach(date -> workDaysAsString.add(date.format(DateTimeFormatter.ofPattern(FORMAT_PATTERN_DATE))));

        model.addAttribute("workDays", workDaysAsString);
        return "index";
    }

    @GetMapping("/tracking")
    String sampleTimeEntry(@RequestParam("date") String date, Model model) {

        LocalDate day = LocalDate.parse(date, DateTimeFormatter.ofPattern(FORMAT_PATTERN_DATE));

        List<TimeEntry> entries = timeEntryProvider.getAllTimeEntriesForDay(day);

        model.addAttribute("workDay", new WorkDay(day, entries));

        return "tracking";
    }

}
