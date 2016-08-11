package com.wt.ticket.service;

import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.wt.ticket.dao.SeatAvailableDTO;
import com.wt.ticket.dao.TicketServiceDAO;
import com.wt.ticket.model.SeatHold;

public class TicketServiceTest {

  @Mock
  private TicketServiceDAO ticketServiceDAO;

  @InjectMocks
  private TicketServiceImpl ticketSvc;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  /*
   * Test -  Find all available seats.
   */
  @Test
  public void testNumSeatsAvailableShouldReturnAllAvailableSeats() {

    SeatAvailableDTO seats = new SeatAvailableDTO();
    seats.setLevel1AvailableSeats(25);
    Optional<Integer> venueLevel = Optional.ofNullable(null);
    when(ticketServiceDAO.findAll()).thenReturn(seats);

    Assert.assertEquals(25, ticketSvc.numSeatsAvailable(venueLevel));
  }

  /*
   * Test - Find available seats for a venue level
   */
  @Test
  public void testNumSeatsAvailableShouldReturnAllAvailableSeatsForVenueLevel() {

    Optional<Integer> venueLevel = Optional.ofNullable(1);
    when(ticketServiceDAO.findByLevel(venueLevel.get())).thenReturn(25);

    Assert.assertEquals(25, ticketSvc.numSeatsAvailable(venueLevel));
  }

  /*
   * Test - Find and Hold seats
   */
  @Test
  public void testFindAndHoldSeatsShouldReturnSeatDeatilsOfSuccesfullyHeldSeats() {
    SeatAvailableDTO seatAvailable = new SeatAvailableDTO();
    seatAvailable.setLevel1AvailableSeats(3);
    seatAvailable.setLevel2AvailableSeats(3);
    seatAvailable.setLevel3AvailableSeats(3);
    seatAvailable.setLevel4AvailableSeats(3);

    when(ticketServiceDAO.findByLevel(Matchers.anyInt(), Matchers.anyInt())).thenReturn(
        seatAvailable);
    when(
        ticketServiceDAO.holdSeats(Matchers.anyInt(), Matchers.anyInt(), Matchers.anyInt(),
            Matchers.anyInt(), Matchers.anyString())).thenReturn(12345);

    Optional<Integer> minLevel = Optional.ofNullable(null);
    Optional<Integer> maxLevel = Optional.ofNullable(null);
    SeatHold seatHold = ticketSvc.findAndHoldSeats(11, minLevel, maxLevel, "johndoe@gmail.com");

    Assert.assertTrue(seatHold.getLevel1Seat() == 3 && seatHold.getLevel2Seat() == 3
        && seatHold.getLevel3Seat() == 3 && seatHold.getLevel4Seat() == 2);

  }
  
  /*
   * Test - Reserve seats for a hold id
   */
  @Test
  public void testReserveSeatsShouldReturnReservationIdForSuccessfulSeatReservation() {
    int seatHoldId = 1234;
    String customerEmail = "jdoe@aaa.com";
    
    when(ticketServiceDAO.reserveSeats(seatHoldId, customerEmail)).thenReturn("jdoe1234");
    String reserveId = ticketSvc.reserveSeats(seatHoldId, customerEmail);
    Assert.assertTrue(reserveId.equalsIgnoreCase("jdoe1234"));
  }

}
