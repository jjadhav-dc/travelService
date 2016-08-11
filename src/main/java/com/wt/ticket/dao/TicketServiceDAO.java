package com.wt.ticket.dao;


public interface TicketServiceDAO {


  /**
   * Finds all available seats
   * 
   * @return details on available seats by levels
   */
  public SeatAvailableDTO findAll();

  
  /**
   * Finds by levels
   * 
   * @param venueLevel level number of the venue
   * @return number of seats available
   */
  public int findByLevel(int venueLevel);
  
  /**
   * Finds by levels
   * 
   * @param minimum level
   * @param maximum level
   * @return details on available seats by levels
   */
  public SeatAvailableDTO findByLevel(int minLevel, int maxLevel);

  /**
   * Hold seats for requested levels
   * 
   * @param numLevel1Seat the totals number of level 1 seats
   * @param numLevel2Seat the totals number of level 2 seats
   * @param numLevel3Seat the totals number of level 3 seats
   * @param numLevel4Seat the totals number of level 4 seats
   * @param customerEmail the email address of the customer to which the seat hold is assigned
   * @return a hold id
   */
  public int holdSeats(int numLevel1Seat, int numLevel2Seat, int numLevel3Seat, int numLevel4Seat,
      String cutsomerEmail);

  /**
   * reserves seats held for a specific customer
   * 
   * @param seatHoldId the seat hold identifier
   * @param customerEmail the email address of the customer to which the seat hold is assigned
   * @return a reservation confirmation code
   */
  public String reserveSeats(int seatHoldId, String customerEmail);


}
