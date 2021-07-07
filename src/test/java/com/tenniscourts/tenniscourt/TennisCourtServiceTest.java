package com.tenniscourts.tenniscourt;

import com.tenniscourts.exceptions.EntityNotFoundException;
import com.tenniscourts.tenniscourts.TennisCourtDTO;
import com.tenniscourts.tenniscourts.TennisCourtMapper;
import com.tenniscourts.tenniscourts.TennisCourtRepository;
import com.tenniscourts.tenniscourts.TennisCourtService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.Optional;

@SpringBootTest
@RunWith(MockitoJUnitRunner.Silent.class)
@ContextConfiguration(classes = TennisCourtService.class)
public class TennisCourtServiceTest {

    @InjectMocks
    TennisCourtService tennisCourtService;

    @Mock
    TennisCourtRepository tennisCourtRepository;

    @Mock
    TennisCourtMapper tennisCourtMapper;

    @Test(expected = EntityNotFoundException.class)
    public void findTennisCourtByIdThrowsEntityNotFoundExceptionWhenTennisCourtIsNotFound() {
        Mockito.when(tennisCourtRepository.findById(1L)).thenReturn(Optional.empty());

        tennisCourtService.findTennisCourtById(1L);
    }

    @Test
    public void createTennisCourtTest()
    {
        TennisCourtDTO tc = new TennisCourtDTO();
        tc.setId(1L);
        tc.setName("Tennis Club");

        Mockito.when(tennisCourtService.addTennisCourt(tc)).thenReturn(tc);
    }
}