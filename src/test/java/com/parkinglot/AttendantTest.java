package com.parkinglot;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class AttendantTest {

  private Attendant attendant;
  ParkingLot parkingLot1;
  ParkingLot parkingLot2;
  private Vehicle car;

  public class TestCar implements Vehicle {}

  @Before
  public void setUp() {
    attendant = new Attendant ();
    parkingLot1 = new ParkingLot ( 1 );
    parkingLot2 = new ParkingLot ( 2 );
    attendant.add(parkingLot1);
    attendant.add(parkingLot2);
    car = new TestCar();
  }

  @Test
  public void shouldBeAbleToParkCar() throws CarCannotBeParkedException {
    Object token = attendant.park(car);
    assertNotNull(token);
  }

  @Test
  public void shouldBeAbleToParkCarEvenIfOneParkingLotIsFull() throws CarCannotBeParkedException {
    Vehicle anotherCar = new TestCar ();
    attendant.park(car);
    Object token2 = attendant.park(anotherCar);
    assertNotNull(token2);
  }

  @Test
  public void shouldBeAbleToUnParkCar() throws CarCannotBeParkedException, CarNotFoundException {
    Object token = attendant.park(car);
    Vehicle anotherCar = attendant.unPark(token);
    assertEquals(car,anotherCar);
  }

  @Test
  public void shouldBeAbleToCallTheAssistantToUpdateDisplayWhenLotIsFull() throws CarCannotBeParkedException {
    Object token = attendant.park(car);
    assertNotNull(token);
  }

  @Test(expected = CarCannotBeParkedException.class)
  public void shouldNotAllowSameCarToBeParkedTwice() throws CarCannotBeParkedException {
    attendant.park(car);
    attendant.park(car);
  }

  @Test
  public void name() throws CarCannotBeParkedException {
    Object token = attendant.park(car);
    assertTrue(parkingLot2.hasCar(token));
  }

  @Test
  public void name1() throws CarCannotBeParkedException {
    ParkingLot parkingLot3 = new ParkingLot(4);
    ParkingLot parkingLot4 = new ParkingLot(3);
    attendant.add(parkingLot3);
    Object token1 = attendant.park(car);
    assertTrue(parkingLot3.hasCar(token1));
    attendant.park(new TestCar());
    attendant.park(new TestCar());
    attendant.park(new TestCar());
    attendant.add(parkingLot4);
    Object token5 = attendant.park(new TestCar());
    assertTrue(parkingLot4.hasCar(token5));

  }

  @Test(expected = CarCannotBeParkedException.class)
  public void name3() throws CarCannotBeParkedException {
    Attendant attendant = new Attendant();
    ParkingLot parkingLot3 = new ParkingLot(0);
    ParkingLot parkingLot4 = new ParkingLot(1);
    attendant.add(parkingLot3);
    attendant.add(parkingLot4);
    attendant.park(car);
    attendant.park(new TestCar());
  }

  @Test
  public void name4() throws CarCannotBeParkedException {
    Attendant attendant = new Attendant();
    ParkingLot parkingLot3 = new ParkingLot(2);
    ParkingLot parkingLot4 = new ParkingLot(1);
    attendant.add(parkingLot3);
    attendant.add(parkingLot4);
    attendant.park(car);
    attendant.park(new TestCar());
    attendant.park(new TestCar());
  }
}
