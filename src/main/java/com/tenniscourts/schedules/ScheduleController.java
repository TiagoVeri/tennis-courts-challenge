package com.tenniscourts.schedules;

import com.tenniscourts.config.BaseRestController;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@AllArgsConstructor
@RequestMapping(value = "/schedule")
@RestController
public class ScheduleController extends BaseRestController {

   private final ScheduleService scheduleService;

    @ApiOperation(value = "Add a new tennis court schedule")
    @PostMapping(value = "/newschedule")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Added a new tennis court schedule")})
    public ResponseEntity<Void> addScheduleTennisCourt(@RequestBody CreateScheduleRequestDTO createScheduleRequestDTO) {
        return ResponseEntity.created(locationByEntity(scheduleService.addSchedule(createScheduleRequestDTO.getTennisCourtId(), createScheduleRequestDTO).getId())).build();
    }

    @ApiOperation(value = "Find schedules by Dates")
    @GetMapping(value = "/searchscheduledates")
    //TODO test different DateTimeFormat avoiding error 400 - bad request
    public ResponseEntity<List<ScheduleDTO>> findSchedulesByDates(@RequestParam(name = "startDate")
                                                                  @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")LocalDate startDate,
                                                                  @RequestParam(name= "endDate")
                                                                  @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") LocalDate endDate) {
        return ResponseEntity.ok(scheduleService.findSchedulesByDates(LocalDateTime.of(startDate, LocalTime.of(0, 0)), LocalDateTime.of(endDate, LocalTime.of(23, 59))));
    }

    @ApiOperation(value = "Find schedules by ID")
    @GetMapping(value =  "/{scheduleId}")
    public ResponseEntity<ScheduleDTO> findByScheduleId(@PathVariable Long scheduleId) {
        return ResponseEntity.ok(scheduleService.findSchedule(scheduleId));
    }
}
