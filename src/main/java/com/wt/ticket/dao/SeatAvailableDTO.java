package com.wt.ticket.dao;

public class SeatAvailableDTO {
  
  private int level1AvailableSeats = 0;
  private int level2AvailableSeats = 0;
  private int level3AvailableSeats = 0;
  private int level4AvailableSeats = 0;
  
  public int getLevel1AvailableSeats() {
    return level1AvailableSeats;
  }
  public void setLevel1AvailableSeats(int level1AvailableSeats) {
    this.level1AvailableSeats = level1AvailableSeats;
  }
  public int getLevel2AvailableSeats() {
    return level2AvailableSeats;
  }
  public void setLevel2AvailableSeats(int level2AvailableSeats) {
    this.level2AvailableSeats = level2AvailableSeats;
  }
  public int getLevel3AvailableSeats() {
    return level3AvailableSeats;
  }
  public void setLevel3AvailableSeats(int level3AvailableSeats) {
    this.level3AvailableSeats = level3AvailableSeats;
  }
  public int getLevel4AvailableSeats() {
    return level4AvailableSeats;
  }
  public void setLevel4AvailableSeats(int level4AvailableSeats) {
    this.level4AvailableSeats = level4AvailableSeats;
  }

  public int getAllAvailableSeats() {
    return getLevel1AvailableSeats() + getLevel2AvailableSeats() + getLevel3AvailableSeats() + getLevel4AvailableSeats();
  }
  
  public int getAvailableSeats(int level) {
    int numSeats = 0;
    switch(level) {
      case 1:
        numSeats = getLevel1AvailableSeats();
        break;
      case 2:
        numSeats = getLevel2AvailableSeats();
        break;
      case 3:
        numSeats = getLevel3AvailableSeats();
        break;
      case 4:
        numSeats = getLevel4AvailableSeats();
        break;
    }
    return numSeats;    
  }
  
  public int getAvailableSeats(int level1, int level2) {
    int numSeats = 0;
    for(int i=level1;i<=level2;i++) {
      numSeats = numSeats + getAvailableSeats(i);
    }
    return numSeats;
  }

}
