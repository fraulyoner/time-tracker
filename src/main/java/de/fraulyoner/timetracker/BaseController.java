package de.fraulyoner.timetracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
class BaseController {

    @Autowired
    private TimeEntryProvider timeEntryProvider;

    @GetMapping("/")
    String index(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model) {
        model.addAttribute("name", name);
        return "index";
    }

    @GetMapping("/tracking")
    String sampleTimeEntry(Model model) {

        LocalDate today = LocalDate.now();
        List<TimeEntry> entries = timeEntryProvider.getAllTimeEntriesForDay(today);

        model.addAttribute("timeEntries", entries);

        return "tracking";
    }

}
