/**
 * 
 */
package com.wt.ticket.model;

/**
 * Model class to store seats that were successfully held.
 * @author jjadhav
 *
 */
public class SeatHold {
  
  private int seatHoldId;
  private int level1Seat = 0;
  private int level2Seat = 0;
  private int level3Seat = 0;
  private int level4Seat = 0;
  
  //constructor
  public SeatHold(int seatHoldId, int level1Seat, int level2Seat,int level3Seat, int level4Seat) {
    this.seatHoldId = seatHoldId;
    this.level1Seat = level1Seat;
    this.level2Seat = level2Seat;
    this.level3Seat = level3Seat;
    this.level4Seat = level4Seat;
    
  }
  
  //setters and getters
  public int getSeatHoldId() {
    return seatHoldId;
  }
  public void setSeatHoldId(int seatHoldId) {
    this.seatHoldId = seatHoldId;
  }
  public int getLevel1Seat() {
    return level1Seat;
  }
  public void setLevel1Seat(int level1Seat) {
    this.level1Seat = level1Seat;
  }
  public int getLevel2Seat() {
    return level2Seat;
  }
  public void setLevel2Seat(int level2Seat) {
    this.level2Seat = level2Seat;
  }
  public int getLevel3Seat() {
    return level3Seat;
  }
  public void setLevel3Seat(int level3Seat) {
    this.level3Seat = level3Seat;
  }
  public int getLevel4Seat() {
    return level4Seat;
  }
  public void setLevel4Seat(int level4Seat) {
    this.level4Seat = level4Seat;
  }


}
