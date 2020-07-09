package de.fraulyoner.timetracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
class BaseController {

    @Autowired
    private TimeEntryProvider timeEntryProvider;

    @GetMapping("/")
    String index(Model model) {
        List<LocalDate> workDays = timeEntryProvider.getAllWorkDays();
        model.addAttribute("workDays", workDays);
        return "index";
    }

    @GetMapping("/tracking")
    String timeEntry(@RequestParam("date") LocalDate day, Model model) {

        List<TimeEntry> entries = timeEntryProvider.getAllTimeEntriesForDay(day);

        model.addAttribute("workDay", new WorkDay(day, entries));

        return "tracking";
    }

}
