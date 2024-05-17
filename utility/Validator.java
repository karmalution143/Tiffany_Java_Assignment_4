package utility;

import java.util.Scanner;

public class Validator {
    
    public Validator() {
        // do nothing
    }

    public int selValidation(Ink ink, Scanner input, int min, int max) {
        boolean valid = false; // assume they make an incorrect choice
        int choice = 0;
        while(!valid) {
            ink.printMenu();
            try { // try is to protect your code from breaking
                choice = input.nextInt();
                if(choice >= min && choice <= max) {
                    valid = true;
                } // if
                else {
                    System.out.println("Selection out of range");
                } // else
            } // try
            catch (Exception e) {
            System.out.println("Please read the menu and make a valid selection");
            valid = false;
            }   
            finally {
            input.nextLine(); // clear the input
            }
        } // while
        return choice;
    } // selValidation

    public double fundValidation(Ink ink, Scanner input, double balance) {
        double amount = 0;
        boolean valid = false;
        while(!valid) {
            ink.printAddFunds(balance);
            try {
                amount = input.nextDouble(); // throw an exception
                if(amount > 0){
                    valid = true;
                } // if
                else {
                    System.out.println("Enter a positive number");
                } // else
            } // try
            catch (Exception e) {
                System.out.println("Please read the directions");
                valid = false; // just in case
            } // catch
            finally { // runs if there's an error or NOT
                input.nextLine(); // clear the input
            } // finally 
        } // while
        return amount;
    } // fundValidation

    public double depositValidation(Ink ink, Scanner input) {
        double amount = 0;
        boolean valid = false;
        while (!valid) {
            try {
                amount = input.nextDouble();
                if (amount > 0) {
                    valid = true;
                } else {
                    System.out.println("Enter a positive number.");
                }
            } 
            catch (Exception e) {
                System.out.println("Please enter a valid amount.");
                valid = false;
            } 
            finally {
                input.nextLine(); // clear the input
            }
        }
        return amount;
    }

    public double withdrawalValidation(Ink ink, Scanner input, double balance) {
        double amount = 0;
        boolean valid = false;
        while (!valid) {
            ink.printWithdrawalFunds(balance);
            try {
                amount = input.nextDouble();
                if (amount > 0 && amount <= balance) { // Validate that the amount is positive and within the available balance
                    valid = true;
                } 
                else {
                    if (amount <= 0) {
                        System.out.println("Withdrawal amount must be positive");
                    }
                    else {
                        System.out.println("Insufficient funds. Enter a smaller withdrawal amount");
                    }
                }
            } catch (Exception e) {
                System.out.println("Please enter a valid amount.");
                valid = false;
            } finally {
                input.nextLine(); // clear the input
            }
        }
        return amount;
    }
} // class
