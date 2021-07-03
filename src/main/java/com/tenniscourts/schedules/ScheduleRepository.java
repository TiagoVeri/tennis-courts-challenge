package com.tenniscourts.schedules;

import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    List<Schedule> findByTennisCourt_IdOrderByStartDateTime(Long id);
    List<Schedule> findAllByEndDateTimeLessThanEqualAndStartDateTimeGreaterThanEqual(LocalDateTime startDateTime, LocalDateTime endDateTime);

}