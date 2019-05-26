package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.jupiter.api.*;

import road_elements.Car;
import road_elements.Road;

class RoadTest {

	static Road road;
	
	@BeforeAll
	static void setUp() {
		road = new Road(10,20,30,40,50);
	}
	
	@Test
	void testRoad() {
		if(road == null) fail("Road class doesn't exist");
		assertEquals("Should show BeginX loc", 10, road.getBeginX());
		assertEquals("Should show BeginY loc", 20, road.getBeginY());
		assertEquals("Should show EndX loc", 30, road.getEndX());
		assertEquals("Should show EndY loc", 40, road.getEndY());
		assertEquals("Should show SpeedLimit", 50, road.getSpeedLimitKMH());
		assertEquals("Should calculate Distance", (int)Math.sqrt(800), (int)road.getDistanceKilometers());
		assertEquals("Should be empty", 0, road.getObjectsSize());
	}

	@Test
	void testAddObject() {
		Car car = new Car(3, null,0,0);
		System.out.println("Hello");
		System.out.println(car.getUUID());
		System.out.println("OK");
		road.getObjects().add(car);
		assertEquals("Should be the same object", car, road.getObjectsByUUID(car.getUUID()));		
	}

	@Test
	void testRemoveObject() {
		Car car = new Car(3,null,0,0);
		System.out.println(car);
		road.addObject(car);
		assertTrue(car == road.removeObject(car.getUUID()));
	}

	@Test
	void testListOfCars() {
		fail("Not yet implemented");
	}

	@Test
	void testGetDistanceKilometers() {
		fail("Not yet implemented");
	}

	@Test
	void testGetFrontCarDistance() {
		fail("Not yet implemented");
	}

	@Test
	void testGetByUUID() {
		fail("Not yet implemented");
	}

	@Test
	void testEqualsObject() {
		fail("Not yet implemented");
	}

	@Test
	void testClone() {
		fail("Not yet implemented");
	}
}
