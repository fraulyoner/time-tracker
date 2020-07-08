package de.fraulyoner.timetracker;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
interface TimeEntryDao extends JpaRepository<TimeEntry, Integer> {

    List<TimeEntry> findByDay(LocalDate day);

}
