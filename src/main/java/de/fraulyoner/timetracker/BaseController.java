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
    String index(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model) {
        model.addAttribute("name", name);
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
