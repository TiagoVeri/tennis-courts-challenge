package com.tenniscourts.guests;

import com.tenniscourts.config.BaseRestController;
import com.tenniscourts.exceptions.EntityNotFoundException;
import com.tenniscourts.tenniscourts.TennisCourtDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/guest")
public class GuestController extends BaseRestController {

    private GuestService guestService;

    @PostMapping
    public ResponseEntity<GuestDTO> addGuest(@Valid @RequestBody GuestDTO guest) {
        return ResponseEntity.created(locationByEntity(guestService.addGuest(guest).getId())).build();
    }

    //TODO: implement swagger
    @GetMapping(value = "/{guestId}")
    public ResponseEntity<GuestDTO> findGuestById(@PathVariable Long guestId) {
        return ResponseEntity.ok(guestService.findGuestById(guestId));
    }

    @GetMapping
    public ResponseEntity<List<GuestDTO>> findAllGuests(){
       List<GuestDTO> guests = guestService.findAllGuests();
        return ResponseEntity.ok().body(guests);
    }

    @DeleteMapping(value = "/{guestId}")
    public ResponseEntity<Void> deleteGuest(@PathVariable Long guestId){
        guestService.deleteGuest(guestId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{guestId}")
    public ResponseEntity<Void> updateGuest(@PathVariable Long guestId, @RequestBody GuestDTO guest ){
        guest.setId(guestId);
        guestService.updateGuest(guest);
        return ResponseEntity.noContent().build();
    }

}
