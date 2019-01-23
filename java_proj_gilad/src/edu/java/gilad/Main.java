package edu.java.gilad;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {


    public static final int DOLLAR_TO_SHEKEL = 1;
    public static final int SHEKEL_TO_DOLLAR = 2;


    public static void main (String[] args){
        // we run here the flow of the converter

        System.out.println("Welcome to currency concverters");
        boolean shouldCont = true;
        ArrayList<Double> results = new ArrayList<>();

        ILS ils = new ILS ();
        USD usd = new USD();

        while (shouldCont){

            Scanner scanner = new Scanner(System.in);
            int option = selectOption(scanner);
            double result = enterAmount(scanner, option, usd, ils);

            shouldCont = runAgain(scanner);

            results.add(result);
        }
        System.out.println("Thank you for using our currency converter!");
        for (Double result: results) {
            System.out.println(result);
        }
    }

    private static boolean runAgain(Scanner scanner){
        boolean shouldCont = false;
        System.out.println("Would like to start over again (y/n)?");
        String startAgain = scanner.next();
        System.out.println(startAgain);
        if (startAgain.equals("y")) {
            shouldCont = true;
        }else if (startAgain.equals("n")){
            shouldCont = false;
        }

        return shouldCont;
    }

    private static double enterAmount(Scanner scanner, int option, USD usd, ILS ils){
        boolean validInput = false;
        double result = 0;
        while (!validInput){
            System.out.println("Please enter an amount to convert:");
            try {
                int amount = scanner.nextInt();
                if (amount <= 0){
                    System.out.println("This is not a valid value! please enter only positive number :)");
                    continue;
                }

                if (option == DOLLAR_TO_SHEKEL){
                    result = usd.getValue() * amount;
                }else if (option == SHEKEL_TO_DOLLAR){
                    result = ils.getValue() * amount;
                }
                validInput = true;
                System.out.println(result);
            }
            catch(Exception ex){
                scanner.nextLine();
                System.out.println("This is not a valid value! please enter only numbers next time:)");
            }
        }

        return result;
    }

    private static int selectOption(Scanner scanner){
        int option = 0;
        boolean isInvalidChoice = true;
        while (isInvalidChoice){
            System.out.println("Please choose an option (1/2):");
            System.out.println("1. Dollars to Shekels");
            System.out.println("2. Shekels to Dollars");
            try{
                option = scanner.nextInt();
                if (option != DOLLAR_TO_SHEKEL &&  option != SHEKEL_TO_DOLLAR)
                    throw new Exception();
                else
                    isInvalidChoice = false;
            }catch (Exception e){
                scanner.nextLine();
                System.out.println("Invalid choice");
            }

        }
        return option;
    }
}
