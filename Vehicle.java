import java.util.ArrayList;

// Abstract Class
abstract class Vehicle {

    protected String number;

    // Constructor
    public Vehicle(String number) {
        this.number = number;
    }

    // Abstract Method
    public abstract int calculateFare(int hours);

    // Getter Method
    public String getNumber() {
        return number;
    }
}

// Car Class
class Car extends Vehicle {

    public Car(String number) {
        super(number);
    }

    @Override
    public int calculateFare(int hours) {
        return hours * 50;
    }
}

// Bike Class
class Bike extends Vehicle {

    public Bike(String number) {
        super(number);
    }

    @Override
    public int calculateFare(int hours) {
        return hours * 20;
    }
}

// Parking Slot Class
class ParkingSlot {

    private int slotNumber;
    private Vehicle vehicle;

    public ParkingSlot(int slotNumber) {
        this.slotNumber = slotNumber;
    }

    public boolean isEmpty() {
        return vehicle == null;
    }

    public void parkVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public int getSlotNumber() {
        return slotNumber;
    }
}

// Parking Lot Class
class ParkingLot {

    private ArrayList<ParkingSlot> slots;

    public ParkingLot(int size) {

        slots = new ArrayList<>();

        for (int i = 1; i <= size; i++) {
            slots.add(new ParkingSlot(i));
        }
    }

    // Park Vehicle
    public void parkVehicle(Vehicle vehicle) {

        for (ParkingSlot slot : slots) {

            if (slot.isEmpty()) {

                slot.parkVehicle(vehicle);

                System.out.println(
                        vehicle.getNumber()
                                + " parked at Slot "
                                + slot.getSlotNumber());

                return;
            }
        }

        System.out.println("Parking Lot is Full!");
    }

    // Display Parking Status
    public void displayParkingStatus() {

        System.out.println("\nParking Status:");

        for (ParkingSlot slot : slots) {

            if (slot.isEmpty()) {

                System.out.println(
                        "Slot " + slot.getSlotNumber() + " -> Empty");

            } else {

                System.out.println(
                        "Slot "
                                + slot.getSlotNumber()
                                + " -> "
                                + slot.getVehicle().getNumber());
            }
        }
    }
}

// Main Class
class VehicleMain {

    public static void main(String[] args) {

        // Create Parking Lot
        ParkingLot parkingLot = new ParkingLot(3);

        // Create Vehicles
        Vehicle car = new Car("MH12AB1234");
        Vehicle bike = new Bike("MH14XY5678");

        // Park Vehicles
        parkingLot.parkVehicle(car);
        parkingLot.parkVehicle(bike);

        // Display Parking Status
        parkingLot.displayParkingStatus();

        // Calculate Fare
        int carHours = 5;
        int bikeHours = 3;

        System.out.println("\nFare Details:");

        System.out.println(
                "Car Fare for "
                        + carHours
                        + " hours = ₹"
                        + car.calculateFare(carHours));

        System.out.println(
                "Bike Fare for "
                        + bikeHours
                        + " hours = ₹"
                        + bike.calculateFare(bikeHours));
    }
}