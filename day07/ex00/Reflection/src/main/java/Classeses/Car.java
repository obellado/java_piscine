package Classeses;

public class Car {
    private String model;
    private Integer number;
    private Boolean readyToRide;

    public Car() {
        this.model = "defaultModel";
        this.number = 0;
        this.readyToRide = false;
    }
    public Car(String model, Integer n, Boolean b) {
        this.model = model;
        this.number = n;
        this.readyToRide = b;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public void setReadyToRide(Boolean readyToRide) {
        this.readyToRide = readyToRide;
    }

    @Override
    public String toString() {
        return "Car{" +
                "model='" + model + '\'' +
                ", number=" + number +
                ", readyToRide=" + readyToRide +
                '}';
    }
}
