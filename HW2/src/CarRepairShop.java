//Yunhan (Eric) Xu
//111118171
//CSE260
//HW-2
public class CarRepairShop {	
	
	private Car[] carDatabase = new Car[100];
	private Ticket[] tickets = new Ticket[100];
	private int carNum, ticketNum;
	
	public CarRepairShop() {
		carNum = 0;
		ticketNum = 0;
	}

	public int addNewCar(String vin, String make, int year) {
		for (int i = 0; i < carNum; i++) {
			if (carDatabase[i] != null && carDatabase[i].getVin().equals(vin)) {
				return -1;
			}
		}
		carDatabase[carNum] = new Car(vin, make, year);
		carNum += 1;
		return carNum;
	}
	
	public int addRepairTicket(String vin, double cost, String description) {
		for (int i = 0; i < carNum; i++) {
			if (carDatabase[i] != null && carDatabase[i].getVin().equals(vin)) {
				tickets[i] = new Ticket(carDatabase[i], cost, description);
				ticketNum += 1;
				return ticketNum;
			}
		}
		return -1;
	}
	
	public double getRepairCost(int ticketNum) {
		if (tickets[ticketNum] != null) {
			return tickets[ticketNum].getCost();
		}
		return -1.0;
	}
	
	public double getTotalRepairCosts(String vin) {
		double total = -1.0;
		for (int i = 0; i < carNum; i++) {
			if (carDatabase[i].getVin().equals(vin)) {
				total = 0.0;
			}
		}
		if (total < 0) {
			return total;
		}
		else {
			for (int i = 0; i < ticketNum; i++) {
				if (tickets[i] != null && tickets[i].getVin().equals(vin)) {
					total += tickets[i].getCost();
				}
			}
			return total;
		}	
	}

	public String getWorstCarMake() {
		if (carNum == 0 || ticketNum == 0) {
			return null;
		}
		String[] allMake = new String[carNum];
		int makeNum = 0;
		int[] makeCount = new int[carNum];
		for (int i = 0; i < ticketNum; i++) {
			while (tickets[i] == null) {
				i += 1;
			}
			String nowMake = tickets[i].getMake();
			boolean existingMake = false;
			for (int j = 0; j < makeNum; j++) {
				if (allMake[j].equals(nowMake)) {
					makeCount[j] += 1;
					existingMake = true;
				}
			}
			if (! existingMake) {
				allMake[makeNum] = nowMake;
				makeCount[makeNum] = 1;
				makeNum++;
			}
		}
		int max = 0;
		for (int i = 0; i < makeNum; i++) {
			if (Math.max(makeCount[max], makeCount[i]) == makeCount[i])
				max = i;
		}
		return allMake[max];
	}
	
	public boolean updateRepairCost(int ticketNum, double newCost) {
		if (tickets[ticketNum] == null) {
			return false;
		}
		tickets[ticketNum].setCost(newCost);
		return true;
	}
	
	public boolean deleteRepair(int ticketNum) {
		if (tickets[ticketNum] == null) {
			return false;
		}
		tickets[ticketNum] = null;
		return true;
	}
	
	public boolean deleteAllRepairsForCar(String VIN) {
		boolean result = false;
		for (int i = 0; i < ticketNum; i++) {
			if (tickets[i] != null && tickets[i].getVin().equals(VIN)) {
				tickets[i] = null;
				result = true;
			}
		}
		return result;
	}
	
	public boolean deleteCarAndRepairs(String VIN) {
		boolean result = false;
		for (int i = 0; i < carNum; i++) {
			if (carDatabase[i] != null && carDatabase[i].getVin().equals(VIN)) {
				carDatabase[i] = null;
				result = true;
			}
		}
		if (result) {
			deleteAllRepairsForCar(VIN);
		}
		return result;
	}
}
