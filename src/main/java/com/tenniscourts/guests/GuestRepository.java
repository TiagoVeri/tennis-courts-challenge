package com.tenniscourts.guests;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface GuestRepository extends JpaRepository<Guest, Long> {

//    @Query("SELECT g.name FROM Guest g WHERE g.name LIKE CONCAT('%',:guestName,'%')")
//    Guest findByName(@Param("guestName") String guestName);
      Guest findByName(String guestName);
}
