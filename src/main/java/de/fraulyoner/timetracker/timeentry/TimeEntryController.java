package de.fraulyoner.timetracker.timeentry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
class TimeEntryController {

    @Autowired
    private TimeEntryProvider timeEntryProvider;

    @GetMapping("/")
    String index(Model model) {
        List<LocalDate> workDays = timeEntryProvider.getAllWorkDays();
        model.addAttribute("workDays", workDays);
        return "index";
    }

    @GetMapping("/entries")
    String timeEntries(@RequestParam(value = "date", required = false) LocalDate day, Model model) {

        if (day == null) {
            day = LocalDate.now();
        }

        List<TimeEntry> entries = timeEntryProvider.getAllTimeEntriesForDay(day);

        model.addAttribute("workDay", new WorkDay(day, entries));

        return "entries";
    }

    @GetMapping("/entries/new")
    String newTimeEntry(@RequestParam(value = "date", required = false) LocalDate day, Model model) {

        if (day == null) {
            day = LocalDate.now();
        }

        TimeEntry timeEntry = new TimeEntry();
        timeEntry.setDay(day);

        model.addAttribute("timeEntry", timeEntry);

        return "entry";
    }

    @PostMapping("/entries/new")
    String addTimeEntry(@Valid TimeEntry timeEntry, Errors errors, Model model) {

        if(errors.hasErrors()) {
            model.addAttribute("error", true);
            return "entry";
        }

        timeEntryProvider.addOrUpdateTimeEntry(timeEntry);

        return "redirect:/entries?date=" + timeEntry.getDay();
    }

    @GetMapping("/entries/{id}")
    String editTimeEntry(@PathVariable("id") Integer id, Model model) {

        Optional<TimeEntry> timeEntryOptional = timeEntryProvider.getById(id);

        if(timeEntryOptional.isPresent()) {
            model.addAttribute("timeEntry", timeEntryOptional.get());
            return "entry";
        }

        return "redirect:/";
    }

    @PostMapping("/entries/{id}")
    String updateTimeEntry(@Valid TimeEntry timeEntry, Errors errors, Model model) {

        if(errors.hasErrors()) {
            model.addAttribute("error", true);
            return "entry";
        }

        timeEntryProvider.addOrUpdateTimeEntry(timeEntry);

        return "redirect:/entries?date=" + timeEntry.getDay();
    }

}
