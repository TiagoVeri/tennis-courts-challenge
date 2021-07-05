package com.tenniscourts.schedules;

import com.tenniscourts.exceptions.EntityNotFoundException;
import com.tenniscourts.tenniscourts.TennisCourtDTO;
import com.tenniscourts.tenniscourts.TennisCourtMapper;
import com.tenniscourts.tenniscourts.TennisCourtService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;


@Service
public class ScheduleService {

    //TODO verify why lombok constructor is breaking the app
    @Autowired
    private  ScheduleRepository scheduleRepository;
    @Autowired
    private  ScheduleMapper scheduleMapper;
    @Autowired
    private  TennisCourtService tennisCourtService;
    @Autowired
    private  TennisCourtMapper tennisCourtMapper;


    public ScheduleDTO addSchedule(Long tennisCourtId, CreateScheduleRequestDTO createScheduleRequestDTO) {
        ScheduleDTO obj = new ScheduleDTO();
        TennisCourtDTO tennisDTO = tennisCourtService.findTennisCourtById(tennisCourtId);
        obj.setTennisCourt(tennisDTO);
        obj.setTennisCourtId(tennisCourtId);
        //TODO Verify games occurring at same time
        obj.setStartDateTime(createScheduleRequestDTO.getStartDateTime());
        obj.setEndDateTime(createScheduleRequestDTO.getStartDateTime().plusHours(1));

        return scheduleMapper.map(scheduleRepository.saveAndFlush(scheduleMapper.map(obj)));
    }

    public List<ScheduleDTO> findSchedulesByDates(LocalDateTime startDate, LocalDateTime endDate) {
        return scheduleMapper.map(scheduleRepository
                                .findAllByStartDateTimeAfterAndEndDateTimeBefore(startDate,endDate));
    }

    public ScheduleDTO findSchedule(Long scheduleId) {
        return scheduleMapper.map(scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new EntityNotFoundException("Schedule not Found")));
    }

    public List<ScheduleDTO> findSchedulesByTennisCourtId(Long tennisCourtId) {
        return scheduleMapper.map(scheduleRepository.findByTennisCourt_IdOrderByStartDateTime(tennisCourtId));
    }


}
