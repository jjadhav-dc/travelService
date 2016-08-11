/**
 * 
 */
package com.wt.ticket.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wt.ticket.dao.SeatAvailableDTO;
import com.wt.ticket.dao.TicketServiceDAO;
import com.wt.ticket.model.SeatHold;

/**
 * @author jjadhav
 *
 */

@Service
public class TicketServiceImpl implements TicketService {

  private final int MIN_LEVEL = 1;
  private final int MAX_LEVEL = 4;


  @Autowired
  TicketServiceDAO ticketSvcDAO;

  /** 
  * The number of seats in the requested level that are neither held nor reserved 
  * @param venueLevel a numeric venue level identifier to limit the search 
  * @return the number of tickets available on the provided level 
  */
  @Override
  public int numSeatsAvailable(Optional<Integer> venueLevel) {
    int numSeatsAvailable = 0;

    if (venueLevel.isPresent()) {
      numSeatsAvailable = ticketSvcDAO.findByLevel(venueLevel.get());
    } else {
      numSeatsAvailable = ticketSvcDAO.findAll().getAllAvailableSeats();
    }

    return numSeatsAvailable;
  }

  /** 
  *  Find and hold the best available seats for a customer 
  *  @param numSeats the number of seats to find and hold 
  *  @param minLevel the minimum venue level  
  *  @param maxLevel the maximum venue level  
  *  @param customerEmail unique identifier for the customer 
  *  @return a SeatHold object identifying the specific seats and related information 
  */ 
  @Override
  public SeatHold findAndHoldSeats(int numSeats, Optional<Integer> minLevel,
      Optional<Integer> maxLevel, String customerEmail) {

    int minimumLevel = minLevel.isPresent() ? minLevel.get() : MIN_LEVEL;
    int maximumLevel = maxLevel.isPresent() ? maxLevel.get() : MAX_LEVEL;
    
    int level1HoldSeats = 0;
    int level2HoldSeats = 0;
    int level3HoldSeats = 0;
    int level4HoldSeats = 0;
    
    SeatHold seatHold = null;

    if (numSeats > 0) {
      SeatAvailableDTO seats = ticketSvcDAO.findByLevel(minimumLevel, maximumLevel);
      if (seats.getAvailableSeats(minimumLevel, maximumLevel) >= numSeats) {

        for (int i = minimumLevel; i <= maximumLevel; i++) {
          if (i == MIN_LEVEL) {
            if (seats.getAvailableSeats(i) >= numSeats && numSeats > 0) {
              level1HoldSeats = numSeats;
              numSeats = 0;
              break;
            } else {
              level1HoldSeats =  seats.getAvailableSeats(i);
              numSeats = numSeats - seats.getAvailableSeats(i);
              continue;
            }
          }
          if (i == MIN_LEVEL + 1) {
            if (seats.getAvailableSeats(i) >= numSeats && numSeats > 0) {
              level2HoldSeats = numSeats;
              numSeats = 0;
              break;
            } else {
              level2HoldSeats =  seats.getAvailableSeats(i);
              numSeats = numSeats - seats.getAvailableSeats(i);
            }
          }
          if (i == MIN_LEVEL + 2) {
            if (seats.getAvailableSeats(i) >= numSeats && numSeats > 0) {
              level3HoldSeats = numSeats;
              numSeats = 0;
            } else {
              level3HoldSeats =  seats.getAvailableSeats(i);
              numSeats = numSeats - seats.getAvailableSeats(i);
            }
          }
          if (i == MAX_LEVEL) {
            if (seats.getAvailableSeats(i) >= numSeats && numSeats > 0) {
              level4HoldSeats = numSeats;
              numSeats = 0;
            } else {
              level4HoldSeats =  seats.getAvailableSeats(i);
              numSeats = numSeats - seats.getAvailableSeats(i);
            }
          }
        }
        int id = ticketSvcDAO.holdSeats(level1HoldSeats,level2HoldSeats,level3HoldSeats,level4HoldSeats, customerEmail);
        seatHold = new SeatHold(id,level1HoldSeats,level2HoldSeats,level3HoldSeats,level4HoldSeats);
      }
    }
    return seatHold;
  }

  /** 
  * Commit seats held for a specific customer 
  * @param seatHoldId the seat hold identifier 
  * @param customerEmail the email address of the customer to which the seat hold is assigned 
  * @return a reservation confirmation code  
  */   
  @Override
  public String reserveSeats(int seatHoldId, String customerEmail) {
    return ticketSvcDAO.reserveSeats(seatHoldId, customerEmail);
  }

}
