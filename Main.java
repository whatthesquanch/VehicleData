import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		List<Vehicle> vehicles = new LinkedList<>();
		try (Scanner scanner = new Scanner(System.in)) {
			while (true) {
				System.out.println("Enter vehicle data or type 'done' to finish:");
				System.out.print("Make: ");
				String make = scanner.nextLine();
				if (make.equalsIgnoreCase("done")) {
					break;
				}
				
				System.out.print("Model: ");
				String model = scanner.nextLine();
				
				double mpg;
				while(true) {
					System.out.print("Miles per gallon: ");
					String mpgStr = scanner.nextLine();
					try {
						mpg = Double.parseDouble(mpgStr);
						break;
					} catch (NumberFormatException e) {
						System.out.println("Invalid input. Please enter a valid number for miles per gallon.");
						
					}
				}
				
				vehicles.add(new Vehicle(make, model, mpg));
			}
		} 
		
		Collections.sort(vehicles);
		
		try (BufferedWriter writer = new BufferedWriter(new FileWriter("vehicles.txt"))) {
			for (Vehicle vehicle : vehicles) {
				writer.write(vehicle.toString());
				writer.newLine();
			}
		} catch (IOException e) {
			System.out.println("Error writing to file: " + e.getMessage());
		}
		System.out.println("Data saved to vehicles.txt");
	}

		}

class Vehicle implements Comparable<Vehicle> {
	private String make;
	private String model;
	private double mpg;
	
	public Vehicle(String make, String model, double mpg) {
		this.make = make;
		this.model = model;
		this.mpg = mpg;
		
	}
	
	public String getMake() {
		return make;
	}
	
	public String getModel() {
		return model;
	}
	
	public double getMpg() {
		return mpg;
	}
	
	@Override
	public int compareTo(Vehicle other) {
		return Double.compare(this.mpg, other.mpg);
		
	}
	
	@Override
	public String toString() {
		return make + "," + model + "," + mpg;
	}

}

