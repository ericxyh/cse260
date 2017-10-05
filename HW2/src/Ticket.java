//Yunhan (Eric) Xu
//111118171
//CSE260
//HW-2
public class Ticket {
	
	private Car car;
	private double cost;
	private String description;
	
	public Ticket(Car car, double cost, String description) {
		this.car = car;
		this.cost = cost;
		this.description = description;
	}
	
	public Car getCar() {
		return car;
	}
	
	public double getCost() {
		return cost;
	}
	
	public String getVin() {
		return car.getVin();
	}
	
	public String getMake() {
		return car.getMake();
	}
	
	public void setCost(double cost) {
		this.cost = cost;
	}
}
