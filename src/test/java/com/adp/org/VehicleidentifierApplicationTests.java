package com.adp.org;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.adp.org.data.Characteristics;
import com.adp.org.data.Wheels;
import com.adp.org.factory.RuleBook;
import com.adp.org.jaxb.Vehicles;
import com.adp.org.jaxb.Vehicles.Vehicle;

import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = VehicleidentifierApplication.class)
@WebAppConfiguration
public class VehicleidentifierApplicationTests {
	
	@Autowired
	private RuleBook ruleBook;
	
	private  Map<String,Characteristics> characteristicsMap;
	
	@Before
    public void setUp() {
		System.out.println("@Before");
    	characteristicsMap = new HashMap<String,Characteristics>();
		// Rules for bigwheel
		Characteristics bigWheelCharacteristics = new Characteristics();
		bigWheelCharacteristics.setVehicleType("BigWheel");
		bigWheelCharacteristics.setFrame("plastic");
		bigWheelCharacteristics.setPowerTrain("human");
		Wheels bigWheels = new Wheels();
		bigWheels.setCount(3);
		bigWheels.setMaterial("plastic");
		List<String> bwposition = new ArrayList<String>();
		bwposition.add("left rear");
		bwposition.add("right rear");
		bwposition.add("front");
		bigWheels.setPosition(bwposition);
		bigWheelCharacteristics.setWheels(bigWheels);
		characteristicsMap.put("BigWheel", bigWheelCharacteristics);
		
		// rules for bicycle
		Characteristics bicycleCharacteristics = new Characteristics();
		bicycleCharacteristics.setVehicleType("Bicycle");
		bicycleCharacteristics.setFrame("metal");
		bicycleCharacteristics.setPowerTrain("human");
		Wheels bicycleWheels = new Wheels();
		bicycleWheels.setCount(2);
		bicycleWheels.setMaterial("metal");
		List<String> bposition = new ArrayList<String>();
		bposition.add("rear");
		bposition.add("front");
		bicycleWheels.setPosition(bposition);
		bicycleCharacteristics.setWheels(bicycleWheels);
		characteristicsMap.put("Bicycle", bicycleCharacteristics);
		
		// rules for motorcycle
		Characteristics motorcycleCharacteristics = new Characteristics();
		motorcycleCharacteristics.setVehicleType("Motorcycle");
		motorcycleCharacteristics.setFrame("metal");
		motorcycleCharacteristics.setPowerTrain("Internal Combustion");
		Wheels motoWheels = new Wheels();
		motoWheels.setCount(2);
		motoWheels.setMaterial("metal");
		List<String> mposition = new ArrayList<String>();
		mposition.add("front");
		mposition.add("rear");
		motoWheels.setPosition(mposition);
		motorcycleCharacteristics.setWheels(motoWheels);
		characteristicsMap.put("Motorcycle", motorcycleCharacteristics);
		
		// rules for hang glider
		Characteristics hangGliderCharacteristics = new Characteristics();
		hangGliderCharacteristics.setVehicleType("Hang Glider");
		hangGliderCharacteristics.setFrame("plastic");
		hangGliderCharacteristics.setPowerTrain("Bernoulli");
		hangGliderCharacteristics.setWheels(null);
		characteristicsMap.put("Hang Glider", hangGliderCharacteristics);
		
		
		// rules for car
		Characteristics carCharacteristics = new Characteristics();
		carCharacteristics.setVehicleType("Car");
		carCharacteristics.setFrame("metal");
		carCharacteristics.setPowerTrain("Internal Combustion");
		
		Wheels carWheels = new Wheels();
		carWheels.setCount(4);
		carWheels.setMaterial(null);
		List<String> carWheelPostion = new ArrayList<String>();
		carWheelPostion.add("front right");
		carWheelPostion.add("front left");
		carWheelPostion.add("rear right");
		carWheelPostion.add("rear left");
		carWheels.setPosition(carWheelPostion);
		carCharacteristics.setWheels(carWheels);
		characteristicsMap.put("Car", carCharacteristics);
	}

	@Test
	public void contextLoads() {
		assert(true);
	}
	
	@Test
	public void testBicycle(){
		    Vehicle vehicle = new Vehicle ();
			Vehicles.Vehicle.Frame frameMaterial = new Vehicles.Vehicle.Frame();
			frameMaterial.setMaterial("metal");
			vehicle.setFrame(frameMaterial);
			vehicle.setId("vehicle2");
			Vehicles.Vehicle.Powertrain powerTrain = new Vehicles.Vehicle.Powertrain();
			powerTrain.setType("human");
			vehicle.setPowertrain(powerTrain);
			Vehicles.Vehicle.Wheels wheels = new Vehicles.Vehicle.Wheels();
			List<Vehicles.Vehicle.Wheels.Wheel> wheelList = new ArrayList<Vehicles.Vehicle.Wheels.Wheel>();
			
			Vehicles.Vehicle.Wheels.Wheel wheel1 = new Vehicles.Vehicle.Wheels.Wheel();
			wheel1.setMaterial("metal");
			wheel1.setPosition("rear");
			wheelList.add(wheel1);
			
			Vehicles.Vehicle.Wheels.Wheel wheel2 = new Vehicles.Vehicle.Wheels.Wheel();
			wheel2.setMaterial("metal");
			wheel2.setPosition("front");
			wheelList.add(wheel2);
			wheels.getWheel().add(wheel1);
			wheels.getWheel().add(wheel2);
			vehicle.setWheels(wheels);
		
		assertTrue(ruleBook.CheckForBicycle(characteristicsMap, vehicle));
		
	}
	
	@Test
	public void testBigWheels(){
		Vehicle vehicle = new Vehicle ();
		Vehicles.Vehicle.Frame frameMaterial = new Vehicles.Vehicle.Frame();
		frameMaterial.setMaterial("plastic");
		vehicle.setFrame(frameMaterial);
		vehicle.setId("vehicle1");
		Vehicles.Vehicle.Powertrain powerTrain = new Vehicles.Vehicle.Powertrain();
		powerTrain.setType("human");
		vehicle.setPowertrain(powerTrain);
		Vehicles.Vehicle.Wheels wheels = new Vehicles.Vehicle.Wheels();
		List<Vehicles.Vehicle.Wheels.Wheel> wheelList = new ArrayList<Vehicles.Vehicle.Wheels.Wheel>();
		
		Vehicles.Vehicle.Wheels.Wheel wheel1 = new Vehicles.Vehicle.Wheels.Wheel();
		wheel1.setMaterial("plastic");
		wheel1.setPosition("left rear");
		wheelList.add(wheel1);
		
		Vehicles.Vehicle.Wheels.Wheel wheel2 = new Vehicles.Vehicle.Wheels.Wheel();
		wheel2.setMaterial("plastic");
		wheel2.setPosition("right rear");
		wheelList.add(wheel2);
		
		Vehicles.Vehicle.Wheels.Wheel wheel3 = new Vehicles.Vehicle.Wheels.Wheel();
		wheel3.setMaterial("plastic");
		wheel3.setPosition("front");
		wheelList.add(wheel3);
		
		wheels.getWheel().add(wheel1);
		wheels.getWheel().add(wheel2);
		wheels.getWheel().add(wheel3);
		vehicle.setWheels(wheels);
		assertTrue(ruleBook.CheckForBigVehicle(characteristicsMap, vehicle));
	}

}
