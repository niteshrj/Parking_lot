package com.parkinglot;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Objects;

public class ParkingLot {
  private final HashMap <Object,Vehicle> vehicles;
  private int capacity;


  public ParkingLot(int capacity) {
    this.capacity = capacity;
    this.vehicles = new HashMap<> ( );
  }

  public Object park(Vehicle car) throws CarCannotBeParkedException {
    if (isFull())
      throw new CarCannotBeParkedException ();
    if (hasCarParked(car))
      throw new CarCannotBeParkedException ();
    Object token = new Object ();
    vehicles.put(token,car);

    return token;
  }

  boolean hasCarParked(Vehicle car) {
    return vehicles.containsValue ( car );
  }

  public Vehicle unParkCar(Object token) throws CarNotFoundException {
    if (!hasCar (token))
      throw new CarNotFoundException ();
    Vehicle car = vehicles.remove (token);
    return car;
  }

  public boolean hasCar(Object token) {
    return vehicles.containsKey ( token );
  }

  @Override
  public String toString() {
    return "ParkingLot{" +
            "vehicles=" + vehicles +
            '}';
  }

  @Override
  public int hashCode() {
    return Objects.hash ( vehicles , capacity );
  }

  public boolean isFull() {
    return capacity == vehicles.size ();
  }

  public boolean hasGreaterCapacity(ParkingLot parkingLot) {
    return parkingLot.capacity == this.capacity;
  }
}
