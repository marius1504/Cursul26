package exercitii;
import java.util.HashMap;
import java.util.Collection;
import java.util.Scanner;
import java.util.List;
public class CarRentalSystem {
 
	private static Scanner sc = new Scanner(System.in);
	private HashMap<String, String> rentedCars = new HashMap<String, String>(100, 0.5f);
	private HashMap<String, RentedCars> carsList = new HashMap<String, RentedCars>(100, 0.5f);
 
	private static String getPlateNo() {
		System.out.println("Introduceti numarul de inmatriculare:");
		return sc.nextLine();
	}
 
	private static String getOwnerName() {
		System.out.println("Introduceti numele proprietarului:");
		return sc.nextLine();
	}
 
	private RentedCars getCarsList(String ownerName){
		return carsList.get(ownerName);
	}
  
	private int getCarsNo(String ownerName) {
	  return carsList.get(ownerName).getCarsNo();
	}
  // search for a key in hashtable
	private boolean isCarRent(String plateNo) {
		return rentedCars.containsKey(plateNo);
	}
 
  // get the value associated to a key
	private String getCarRent(String plateNo) {
		if(!isCarRent(plateNo)) {
			return "Masina cu acest numar de inmatriculare nu exista";
		}
		return rentedCars.get(plateNo); 
	}

  // add a new (key, value) pair
	private void rentCar(String plateNo, String ownerName) {
		if(isCarRent(plateNo)) {
			System.out.println("Masina este existenta, nu poti adauga 2 de ori aceasi cheie.");
		}else {
			rentedCars.put(plateNo, ownerName);
			if(carsList.containsKey(ownerName)){
				carsList.get(ownerName).addCar(plateNo);
			}else {
				carsList.put(ownerName, new RentedCars(ownerName));
				carsList.get(ownerName).addCar(plateNo);
			}
		}
	}
 
  // remove an existing (key, value) pair
	private void returnCar(String plateNo) {
		if(isCarRent(plateNo)) {
			String name2 = getCarRent(plateNo);
			rentedCars.remove(plateNo);
			carsList.get(name2).remove(plateNo);
			System.out.println("Masina cu numarul "+ plateNo +" a fost stearsa din sistem.");
		}else {
	  System.out.println("Masina cu acest numar de inmatriculare nu exista in sistem.");
		}
	}
  
  //intoarce numarul de masini inchiriate la acel moment
	private int totalRented() {
		return rentedCars.size();
  }
 
	private static void printCommandsList() {
		System.out.println("help         - Afiseaza aceasta lista de comenzi");
		System.out.println("add          - Adauga o noua pereche (masina, sofer)");
		System.out.println("check        - Verifica daca o masina este deja luata");
		System.out.println("remove       - Sterge o masina existenta din hashtable");
		System.out.println("getOwner     - Afiseaza proprietarul curent al masinii");
		System.out.println("quit         - Inchide aplicatia");
	}
 
	public void run() {
		boolean quit = false;
		while(!quit) {
			System.out.println("Asteapta comanda: (help - Afiseaza lista de comenzi)");
			String command = sc.nextLine();
			switch(command) {
			case "help":
				printCommandsList();
				break;
			case "add":
				rentCar(getPlateNo(), getOwnerName());
				break;
			case "check":
				System.out.println(isCarRent(getPlateNo()));
				break;
			case "remove":
				returnCar(getPlateNo());
				break;
			case "getOwner":
				System.out.println(getCarRent(getPlateNo()));
				break;
			case "totalRented":
				System.out.println(totalRented());
				break;
			case "getCarList":
				System.out.println(getCarsList(getOwnerName()));
				break;
			case "getCarsNo":
				System.out.println(getCarsNo(getOwnerName()));
				break;
			case "quit":
				System.out.println("Aplicatia se inchide...");
				quit = true;
				break;
			default:
				System.out.println("Unknown command. Choose from:");
				printCommandsList();
			}
		}
	}
 
	public static void main(String[] args) {
 
    // create and run an instance (for test purpose)
		new CarRentalSystem().run();
 
	}
}