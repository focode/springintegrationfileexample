package com.adp.org.activators;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.adp.org.data.Characteristics;
import com.adp.org.data.VehicleReport;
import com.adp.org.data.Wheels;
import com.adp.org.jaxb.Vehicles;
import com.adp.org.jaxb.Vehicles.Vehicle;

import com.adp.org.factory.RuleBook;

public class ReadIncommingFileActivator implements InitializingBean{
	
	@Autowired
	private RuleBook ruleBook;
	
	private Map<String,Characteristics> characteristicsMap;
	
	private Logger logger = Logger.getLogger(ReadIncommingFileActivator.class);

	public File process(File file) throws Exception{	

		JAXBContext jaxbContext = JAXBContext.newInstance(Vehicles.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		logger.info("Processing File: " + file);
		logger.info("file.getName::"+file.getName());
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
		    String line;
		    StringBuilder tempBuilder = new StringBuilder();
		    while ((line = br.readLine()) != null) {
		    	tempBuilder.append(line);
		    }
		    logger.info("Content of file::"+tempBuilder.toString());
		}
		
		Vehicles vehicles = (Vehicles) jaxbUnmarshaller.unmarshal(file);
		List<Vehicle> bigwheel = new ArrayList<Vehicle>();
		List<Vehicle> bicycle = new ArrayList<Vehicle>();
		List<Vehicle> car = new ArrayList<Vehicle>();
		List<Vehicle> hangGlider = new ArrayList<Vehicle>();
		List<Vehicle> motorcycle = new ArrayList<Vehicle>();
		List<VehicleReport> vehicleReportList = new ArrayList<VehicleReport>();
		 for(Vehicle vehicle : vehicles.getVehicle())
		    {
			 logger.info("Vehicle id from xml::"+vehicle.getId());
		        // check if Vehicle is of type big wheel
			 if(ruleBook.CheckForBigVehicle(characteristicsMap, vehicle)){
				 logger.info("Vehicle from xml is ::"+"big wheel");
				 bigwheel.add(vehicle);
				 VehicleReport vehicleReport = new VehicleReport();
				 vehicleReport.setId(vehicle.getId());
				 vehicleReport.setVehicleType("Bigwheel");
				 vehicleReportList.add(vehicleReport);
			 }
			// check if Vehicle is of type Bicycle
			 if(ruleBook.CheckForBicycle(characteristicsMap, vehicle)){
				 logger.info("Vehicle from xml is ::"+"Bicycle");
				 bicycle.add(vehicle);
				 VehicleReport vehicleReport = new VehicleReport();
				 vehicleReport.setId(vehicle.getId());
				 vehicleReport.setVehicleType("Bicycle");
				 vehicleReportList.add(vehicleReport);
			 }
			// check if Vehicle is of type Car
			 if(ruleBook.CheckForCar(characteristicsMap, vehicle)){
				 logger.info("Vehicle from xml is ::"+"Car");
				 car.add(vehicle);
				 VehicleReport vehicleReport = new VehicleReport();
				 vehicleReport.setId(vehicle.getId());
				 vehicleReport.setVehicleType("Car");
				 vehicleReportList.add(vehicleReport);
			 }
			 
			// check if Vehicle is of type Motorcycle
			 if(ruleBook.CheckForMotorcycle(characteristicsMap, vehicle)){
				 logger.info("Vehicle from xml is ::"+"Motorcycle");
				 motorcycle.add(vehicle);
				 VehicleReport vehicleReport = new VehicleReport();
				 vehicleReport.setId(vehicle.getId());
				 vehicleReport.setVehicleType("Motorcycle");
				 vehicleReportList.add(vehicleReport);
			 }
			 
			// check if Vehicle is of type Motorcycle
			 if(ruleBook.CheckForHangGlider(characteristicsMap, vehicle)){
				 logger.info("Vehicle from xml is ::"+"HangGlider");
				 hangGlider.add(vehicle);
				 VehicleReport vehicleReport = new VehicleReport();
				 vehicleReport.setId(vehicle.getId());
				 vehicleReport.setVehicleType("HangGlider");
				 vehicleReportList.add(vehicleReport);
			 }
		    }
		 logger.info("\n\n \t\t\tGenerating Report for vehicles");
		 logger.info("\n\n \t\t\t Their are total: "+vehicles.getVehicle().size()+"\n\n \t\t\t No of Big wheel are ::"+bigwheel.size()+"\n\n \t\t\t No of Hang Glider are ::"+hangGlider.size()+"\n\n \t\t\t No of Motorcycle are ::"+motorcycle.size()+"\n\n \t\t\t No of car are ::"+car.size()+"\n\n \t\t\t No of Bicycle are ::"+bicycle.size());
		 
		 for(VehicleReport vehicleReport:vehicleReportList){
			 logger.info("\n\n \t\t\t Vehicle id "+vehicleReport.getId()+" is "+vehicleReport.getVehicleType());
		 }
	
		return file;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
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

}
