package de.fraulyoner.timetracker;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
interface TimeEntryDao extends JpaRepository<TimeEntry, Integer> {

    List<TimeEntry> findByDayOrderByStartTime(LocalDate day);

    @Query("SELECT DISTINCT day FROM TimeEntry")
    List<LocalDate> findAllDays();
}
