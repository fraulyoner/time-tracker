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
    String timeEntry(@RequestParam(value = "date", required = false) LocalDate day, Model model) {

        if (day == null) {
            day = LocalDate.now();
        }

        List<TimeEntry> entries = timeEntryProvider.getAllTimeEntriesForDay(day);

        model.addAttribute("workDay", new WorkDay(day, entries));

        return "tracking";
    }

    @GetMapping("/tracking/new")
    String newTimeEntry(@RequestParam(value = "date", required = false) LocalDate day, Model model) {

        if (day == null) {
            day = LocalDate.now();
        }

        model.addAttribute("timeEntry", new TimeEntryDto(day));

        return "new-time-entry";
    }

    @PostMapping("/tracking/new")
    String addTimeEntry(@ModelAttribute("timeEntry") TimeEntryDto timeEntryDto) {

        // TODO: Validate timeEntryDto!

        TimeEntry timeEntry = timeEntryProvider.addNewTimeEntry(timeEntryDto.toTimeEntry());

        return "redirect:/tracking?date=" + timeEntry.getDay();
    }

}
