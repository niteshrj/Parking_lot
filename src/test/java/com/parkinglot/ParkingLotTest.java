package com.parkinglot;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ParkingLotTest{
  public class TestCar implements Vehicle {}
  ParkingLot parkingLot;
  Vehicle car;

  @Before
  public void setUp() {
    parkingLot = new ParkingLot (5);
    car = new TestCar ();
  }

  @Test
  public void shouldParkCar() throws CarCannotBeParkedException {
    Object token = parkingLot.park (car);
    assertNotNull(token);
  }

  @Test(expected = CarCannotBeParkedException.class)
  public void shouldThrowExceptionIfCarIsAlreadyParked() throws CarCannotBeParkedException {
    parkingLot.park (car);
    parkingLot.park (car);
  }

  @Test
  public void shouldUnParkCar() throws CarNotFoundException, CarCannotBeParkedException {
    Object token = parkingLot.park(car);
    Vehicle myCar = parkingLot.unParkCar(token);
    assertFalse(parkingLot.hasCar(this.car));
    assertEquals(car,myCar);
  }

  @Test
  public void shouldGiveFalseIfCarIsNotPresent() {
    assertFalse(parkingLot.hasCar(new Object()));
  }

  @Test
  public void shouldGiveTrueIfCarIsPresent() throws CarCannotBeParkedException {
    Object token = parkingLot.park(car);
    assertTrue(parkingLot.hasCar(token));
  }

  @Test(expected = CarNotFoundException.class)
  public void shouldThrowExceptionIfCarIsNotInLot() throws CarNotFoundException {
    parkingLot.unParkCar(car);
  }

  @Test
  public void shouldGiveTrueIfParkingLotIsFull() throws CarCannotBeParkedException {
    ParkingLot parkingLot = new ParkingLot(1);
    parkingLot.park(car);
    assertTrue(parkingLot.isFull());
  }

  @Test
  public void shouldGiveFalseIfParkingLotIsNotFull() {
    ParkingLot parkingLot = new ParkingLot(1);
    assertFalse(parkingLot.isFull());
  }

  @Test(expected = CarCannotBeParkedException.class)
  public void shouldNotParkCarIfParkingLotIsFull() throws CarCannotBeParkedException {
    ParkingLot parkingLot = new ParkingLot(1);
    parkingLot.park(car);
    Vehicle anotherCar = new TestCar ();
    parkingLot.park(anotherCar);
  }

  @Test
  public void shouldParkCarIfAnyCarIsUnParked() throws CarCannotBeParkedException, CarNotFoundException {
    ParkingLot parkingLot = new ParkingLot(2);
    Vehicle anotherCar = new TestCar();
    Object token = parkingLot.park(car);
    parkingLot.park(anotherCar);
    parkingLot.unParkCar(token);
    assertFalse(parkingLot.hasCar(token));
    Object anotherToken = parkingLot.park (car);
    assertTrue(parkingLot.hasCar(anotherToken));
  }

}
