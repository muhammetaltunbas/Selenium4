package Selenium4Tests;


import java.util.ArrayList;
import java.util.List;

public class TestList {

    public static void main(String[] args) {

        List<String> cars = new ArrayList<String>();
        cars.add("Volvo");
        cars.add("BMW");
        cars.add("Ford");
        cars.add("Mazda");

        for(String printCars:cars)
        {
            System.out.println(printCars);
        }

        for (int i=0;i<cars.size();i++)
        {
            System.out.println(cars.get(i));
        }

    }

}
