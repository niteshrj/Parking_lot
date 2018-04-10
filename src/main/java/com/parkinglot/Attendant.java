package com.parkinglot;

import java.util.ArrayList;

public class Attendant {

  private ArrayList<ParkingLot> parkingLots;

  Attendant() {
    this.parkingLots = new ArrayList<>();
  }

  public Object park(Vehicle car) throws CarCannotBeParkedException {
    ParkingLot maxCapacityParkingLot = this.parkingLots.get(0);
    for (ParkingLot parkingLot : parkingLots) {
      if(!maxCapacityParkingLot.hasGreaterCapacity(parkingLot) && !parkingLot.isFull()){
        maxCapacityParkingLot = parkingLot;
      }
      if(parkingLot.hasCarParked(car)){
        throw new CarCannotBeParkedException();
      }
    }
    return maxCapacityParkingLot.park(car);
  }

  public Vehicle unPark(Object token) throws CarNotFoundException {
    for (ParkingLot parkingLot : parkingLots) {
      if(parkingLot.hasCar(token)){
        return parkingLot.unParkCar(token);
      }
    }
    return null;
  }

  public void add(ParkingLot parkingLot) {
    parkingLots.add(parkingLot);
  }
  static void sum(int ...a){
    for (int i : a) {
      System.out.println(i);
    }
  }
  public static void main(String[] args) {
    sum(1,2,3);
  }

}
