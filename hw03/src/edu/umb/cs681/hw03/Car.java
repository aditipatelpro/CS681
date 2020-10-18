package edu.umb.cs681.hw03;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.List;
import java.util.Comparator;


public class Car {

    private String make, model;
    private int mileage, year;
    private int price;
    private int dominationCount;

    public Car(String make, String model, int mileage, int year, int price) {
        this.make = make;
        this.model = model;
        this.mileage = mileage;
        this.year = year;
        this.price = price;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public int getMileage() {
        return mileage;
    }

    public int getYear() {
        return year;
    }

    public int getPrice() {
        return price;
    }

    public void setDominationCount(ArrayList<Car> cars) {
        for (Car car : cars) {
            if ((car.getPrice() >= this.getPrice()) && (car.getMileage() >= this.getMileage())
                    && (car.getYear() <= this.getYear())) {
                this.dominationCount++;
            }
        }
        this.dominationCount--;
    }

    public int getDominationCount() {
        return this.dominationCount;
    }

    @Override
    public String toString() {

        return this.make +" "+ this.model+" "+ this.mileage+" "+this.year+" "+this.price;
    }

    public static void main(String[] args) {

        List<Car> carList = new ArrayList<Car>();
        carList.add(new Car("Tesla", "X", 90, 2019, 80000));
        carList.add(new Car("Tesla", "Y", 100, 2018, 50000));
        carList.add(new Car("Ford", "Mustang", 90, 2020, 45000));
        carList.add(new Car("Audi", "A7", 25, 2020, 70000));

        Integer minPrice = carList.stream()
                .map((Car car) -> car.getPrice())
                .reduce(0,(result, carPrice) -> {
                    if (result == 0)
                        return carPrice;
                    else if (carPrice < result)
                        return carPrice;
                    else
                        return result;
                });
        System.out.println("Minimum price in the given cars list is " + minPrice);

        Integer maxPrice = carList.stream()
                .map((Car car) -> car.getPrice())
                .reduce(0,(result, carPrice) -> {
                    if (result == 0)
                        return carPrice;
                    else if (carPrice > result)
                        return carPrice;
                    else
                        return result;
                });
        System.out.println("Maximum price in the given cars list is " + maxPrice);

        Integer carCount = carList.stream()
                .map((Car car) -> car.getMake())
                .reduce(0, (result,carmake) -> ++result, (finalResult, intermediateResult) -> finalResult);

        System.out.println("Car Count =" + carCount);



    }
}