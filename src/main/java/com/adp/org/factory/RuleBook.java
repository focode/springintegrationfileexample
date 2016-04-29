package com.adp.org.factory;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.adp.org.data.Characteristics;
import com.adp.org.jaxb.Vehicles;
import com.adp.org.jaxb.Vehicles.Vehicle;
import com.adp.org.jaxb.Vehicles.Vehicle.Wheels.Wheel;

@Component
public class RuleBook {

	public boolean CheckForBigVehicle(Map<String, Characteristics> rules, Vehicle vehicle) {
		if (CheckForBigVehicleFrame(rules, vehicle)
				&& (CheckForBigVehicleWheels(rules, vehicle) && (CheckForBigVehiclePowertrain(rules, vehicle)))) {
			return true;
		}
		return false;
	}

	private boolean CheckForBigVehicleFrame(Map<String, Characteristics> rules, Vehicle vehicle) {
		if ((vehicle.getFrame().getMaterial().equalsIgnoreCase(rules.get("BigWheel").getFrame()))) {
			return true;
		}

		return false;
	}

	private boolean CheckForBigVehicleWheels(Map<String, Characteristics> rules, Vehicle vehicle) {
		List<Vehicles.Vehicle.Wheels.Wheel> wheels = vehicle.getWheels().getWheel();

		if ((wheels.size() == 3)
				&& (wheels.get(0).getMaterial().equalsIgnoreCase(rules.get("BigWheel").getWheels().getMaterial()))) {
			int i = 0;
			for (Wheel wheel : wheels) {
				if (wheel.getPosition().equalsIgnoreCase(rules.get("BigWheel").getWheels().getPosition().get(i))) {
					i++;
				} else {
					return false;
				}
			}

		} else {
			return false;
		}

		return true;

	}

	private boolean CheckForBigVehiclePowertrain(Map<String, Characteristics> rules, Vehicle vehicle) {
		if (vehicle.getPowertrain().getType().equalsIgnoreCase(rules.get("BigWheel").getPowerTrain())) {
			return true;
		}
		return false;

	}

	// check for Bicycle

	public boolean CheckForBicycle(Map<String, Characteristics> rules, Vehicle vehicle) {

		if (CheckForBicycleFrame(rules, vehicle)
				&& (CheckForBicycleWheels(rules, vehicle) && (CheckForBicyclePowertrain(rules, vehicle)))) {
			return true;
		}

		return false;

	}

	private boolean CheckForBicyclePowertrain(Map<String, Characteristics> rules, Vehicle vehicle) {
		if (vehicle.getPowertrain().getType().equalsIgnoreCase(rules.get("Bicycle").getPowerTrain())) {
			return true;
		}
		return false;
	}

	private boolean CheckForBicycleWheels(Map<String, Characteristics> rules, Vehicle vehicle) {
		List<Vehicles.Vehicle.Wheels.Wheel> wheels = vehicle.getWheels().getWheel();

		if ((wheels.size() == 2)
				&& (wheels.get(0).getMaterial().equalsIgnoreCase(rules.get("Bicycle").getWheels().getMaterial()))) {
			int i = 0;
			for (Wheel wheel : wheels) {
				if (wheel.getPosition().equalsIgnoreCase(rules.get("Bicycle").getWheels().getPosition().get(i))) {
					i++;
				} else {
					return false;
				}
			}

		} else {
			return false;
		}

		return true;
	}

	private boolean CheckForBicycleFrame(Map<String, Characteristics> rules, Vehicle vehicle) {
		if ((vehicle.getFrame().getMaterial().equalsIgnoreCase(rules.get("Bicycle").getFrame()))) {
			return true;
		}
		return false;
	}

	// check for Motorcycle
	public boolean CheckForMotorcycle(Map<String, Characteristics> rules, Vehicle vehicle) {

		if (CheckForMotorcycleFrame(rules, vehicle)
				&& (CheckForMotorcycleWheels(rules, vehicle) && (CheckForMotorcyclePowertrain(rules, vehicle)))) {
			return true;
		}

		return false;

	}

	private boolean CheckForMotorcyclePowertrain(Map<String, Characteristics> rules, Vehicle vehicle) {
		if (vehicle.getPowertrain().getType().equalsIgnoreCase(rules.get("Motorcycle").getPowerTrain())) {
			return true;
		}
		return false;
	}

	private boolean CheckForMotorcycleWheels(Map<String, Characteristics> rules, Vehicle vehicle) {
		List<Vehicles.Vehicle.Wheels.Wheel> wheels = vehicle.getWheels().getWheel();

		if ((wheels.size() == 2)
				&& (wheels.get(0).getMaterial().equalsIgnoreCase(rules.get("Motorcycle").getWheels().getMaterial()))) {
			int i = 0;
			for (Wheel wheel : wheels) {
				if (wheel.getPosition().equalsIgnoreCase(rules.get("Motorcycle").getWheels().getPosition().get(i))) {
					i++;
				} else {
					return false;
				}
			}

		} else {
			return false;
		}

		return true;
	}

	private boolean CheckForMotorcycleFrame(Map<String, Characteristics> rules, Vehicle vehicle) {
		if ((vehicle.getFrame().getMaterial().equalsIgnoreCase(rules.get("Motorcycle").getFrame()))) {
			return true;
		}
		return false;
	}

	// check for hang glider

	public boolean CheckForHangGlider(Map<String, Characteristics> rules, Vehicle vehicle) {

		if (CheckForHangGliderFrame(rules, vehicle)
				&& (CheckForHangGliderWheels(rules, vehicle) && (CheckForHangGliderPowertrain(rules, vehicle)))) {
			return true;
		}
		return false;
	}

	private boolean CheckForHangGliderPowertrain(Map<String, Characteristics> rules, Vehicle vehicle) {
		if (vehicle.getPowertrain().getType().equalsIgnoreCase(rules.get("Hang Glider").getPowerTrain())) {
			return true;
		}
		return false;
	}

	private boolean CheckForHangGliderWheels(Map<String, Characteristics> rules, Vehicle vehicle) {
		return true;
	}

	private boolean CheckForHangGliderFrame(Map<String, Characteristics> rules, Vehicle vehicle) {
		if ((vehicle.getFrame().getMaterial().equalsIgnoreCase(rules.get("Hang Glider").getFrame()))) {
			return true;
		}
		return false;
	}

	// check for car
	public boolean CheckForCar(Map<String, Characteristics> rules, Vehicle vehicle) {

		if (CheckForCarFrame(rules, vehicle)
				&& (CheckForCarWheels(rules, vehicle) && (CheckForCarPowertrain(rules, vehicle)))) {
			return true;
		}
		return false;
	}

	private boolean CheckForCarPowertrain(Map<String, Characteristics> rules, Vehicle vehicle) {
		if (vehicle.getPowertrain().getType().equalsIgnoreCase(rules.get("Car").getPowerTrain())) {
			return true;
		}
		return false;
	}

	private boolean CheckForCarWheels(Map<String, Characteristics> rules, Vehicle vehicle) {
		List<Vehicles.Vehicle.Wheels.Wheel> wheels = vehicle.getWheels().getWheel();

		if ((wheels.size() == 4)
				&& (wheels.get(0).getMaterial().equalsIgnoreCase(rules.get("Car").getWheels().getMaterial()))) {
			int i = 0;
			for (Wheel wheel : wheels) {
				if (wheel.getPosition().equalsIgnoreCase(rules.get("Car").getWheels().getPosition().get(i))) {
					i++;
				} else {
					return false;
				}
			}

		} else {
			return false;
		}

		return true;
	}

	private boolean CheckForCarFrame(Map<String, Characteristics> rules, Vehicle vehicle) {
		if ((vehicle.getFrame().getMaterial().equalsIgnoreCase(rules.get("Car").getFrame()))) {
			return true;
		}
		return false;
	}

}
