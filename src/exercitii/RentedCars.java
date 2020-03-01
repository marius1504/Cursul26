package exercitii;
import java.util.ArrayList;
import java.util.List;

public class RentedCars {
	
	private List <String> carsList;
	private String ownerName;

	public RentedCars(String ownerName) {
		this.ownerName = ownerName;
		this.carsList = new ArrayList<String>();
	}
	
	public int getCarsNo() {
		return carsList.size();
	}
	
	public List<String> getCarsList() {
		return carsList;
	}
	
	public void addCar(String plateNumber) {
		this.carsList.add(plateNumber);
	}
	
	public void remove(String plateNumber) {
		this.carsList.remove(plateNumber);
	}
	
}