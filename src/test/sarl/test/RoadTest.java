package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.UUID;

import org.arakhne.afc.gis.road.primitive.RoadConnection;
import org.junit.Rule;
import org.junit.jupiter.api.*;
import org.junit.rules.ExpectedException;

import road_elements.Car;
import road_elements.Road;
import road_elements.RoadObject;
import road_elements.TrafficLight;

class RoadTest {

	static Road road;
	static RoadConnection entryRoadConnection;
	static Car car, car2;
	static TrafficLight tLight;
	
	@BeforeAll
	static void setUp() {
		road = new Road(10,20,30,40,50);
		tLight = new TrafficLight(4, null);
		car = new Car(3,null,null);
		car2 = new Car(4, null, null);
		road.addObject(car);
		road.addObject(car2);
	}
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Test
	void test_road_constructor() {
		if(road == null) fail("Road class doesn't exist");
		assertEquals("Should show BeginX loc", 10, road.getBeginX());
		assertEquals("Should show BeginY loc", 20, road.getBeginY());
		assertEquals("Should show EndX loc", 30, road.getEndX());
		assertEquals("Should show EndY loc", 40, road.getEndY());
		assertEquals("Should show SpeedLimit", 50, road.getSpeedLimitKMH());
		assertEquals("Should calculate Distance", (int)Math.sqrt(800), (int)road.getDistanceKilometers());
	}

	@Test
	void testAddObject() {
<<<<<<< HEAD
=======
		Car car = new Car(3, null,0,0);
		System.out.println("Hello");
		System.out.println(car.getUUID());
		System.out.println("OK");
		road.getObjects().add(car);
>>>>>>> 119d5ddeb38cd6d9fa22b10ba8d8436e7ad3ec53
		assertEquals("Should be the same object", car, road.getObjectsByUUID(car.getUUID()));		
	}

	@Test
<<<<<<< HEAD
	void remove_object_should_return_object() {
=======
	void testRemoveObject() {
		Car car = new Car(3,null,0,0);
		System.out.println(car);
		road.addObject(car);
>>>>>>> 119d5ddeb38cd6d9fa22b10ba8d8436e7ad3ec53
		assertTrue(car == road.removeObject(car.getUUID()));
	}

	@Test
	void get_object_by_car_type_should_only_return_cars() {
		ArrayList<Car> carList = road.listOfCars();
		assertEquals("Should has 2 object", 2, carList.size());
		assertEquals("Should be a Car object returned", car.getUUID(), carList.get(0).getUUID());
	}

	@Test
	void get_front_car_distance() {
		double expectedDistance = car2.getPos1D() - car.getPos1D();
		assertTrue(expectedDistance == road.getFrontCarDistance(car));
	}

	@Test
	void get_object_by_uuid_return_the_object() {
		assertEquals("Should be the same UUID", car, road.getObjectsByUUID(car.getUUID()));
	}
}
