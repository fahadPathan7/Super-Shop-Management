/*
Main use of this class:
1. to show grocery product types
2. to do action according to user's choice
3. to calculate and store costs on grocery products
*/

package com.company;

import java.util.Scanner;

public class Grocery {
    Scanner sc = new Scanner(System.in); // to take user input
    String choice; // to input user's choice
    static double costs = 0.0; // to store user's cost on Grocery products

    // constructor
    Grocery() {
        itemsType();
    }

    // to show items and taking user's choice
    void itemsType() {
        System.out.println("\nGrocery Items:");

        //* change here for updating products
        System.out.println("1. Rice (Kg)    - 0.75$");
        System.out.println("2. Oil (Liter)  - 1.5$");
        System.out.println("3. Salt (Kg)    - 0.25$");
        System.out.println("4. Milk (Liter) - 1$");
        System.out.println("5. Egg (Dozen)  - 1.4$");

        //* change here for updating products
        System.out.println("\nNotice:\ntype 1 to 5 for buying products.\ntype 0 to go to Home page.\ntype -1 to exit.\n");

        System.out.print("Enter your choice: ");
        choice = sc.next();

        processChoice();
    }

    // checking if the choice is valid for buying products
    public boolean isWithinRange(String str) {
        boolean check = true;
        try {
            Integer x = Integer.parseInt(str);

            //* change here for updating products
            if (x >= 1 && x <= 5) check = true;
            else check = false;
        } catch(Exception e) {
            check = false;
        }
        return check;
    }

    // to take action according to user's choice
    void processChoice() {
        if (isWithinRange(choice)) {
            // to take item's count
            double count = 0.0; // as all the items are counted as piece
            System.out.print("Enter how much/ how many you want: ");
            try {
                count = sc.nextDouble();
                if (count <= 0.0) {
                    System.out.println(UserControl.RED + "\nPlease enter a positive value." + UserControl.RESET);
                    itemsType();
                }
            } catch (Exception e) {
                System.out.println(UserControl.RED + "\nInvalid input! Please try again." + UserControl.RESET);

                sc.next(); // to avoid unnecessary input
                itemsType();
            }

            long x = (long)count; // to check for fraction value

            //* change here for updating products
            // matching choice of items
            if (choice.equals("1")) {
                costs += (0.75 * count);
                UserControl.memo("Rice>(Kg)", 0.75, count);
            }
            else if (choice.equals("2")) {
                costs += (1.5 * count);
                UserControl.memo("Oil>(Liter)", 1.5, count);
            }
            else if (choice.equals("3")) {
                costs += (0.25 * count);
                UserControl.memo("Salt>(Kg)", 0.25, count);
            }
            else if (choice.equals("4")) {
                costs += (1 * count);
                UserControl.memo("Milk>(Liter)", 1, count);
            }
            else if (choice.equals("5")) {
                if (x == 0) {
                    System.out.println(UserControl.RED + "\nPlease enter valid amount (amount >= 1)." + UserControl.RESET);
                    itemsType();
                    return;
                }
                if (count > (double)x) Products.fractionErrorMessage();
                costs += (1.4 * Math.floor(count));
                UserControl.memo("Egg>(Dozen)", 1.4, Math.floor(count));
            }

            // asking the user if he wants more
            System.out.println("\nWant more grocery?");
            itemsType();
        }
        // going to Home page
        else if (choice.equals("0")) {
            new Products();
        }
        // exiting the program
        else if (choice.equals("-1")) {
            UserControl.showTotalCost();
        }
        else {
            System.out.println(UserControl.RED + "\nInvalid input. Please try again." + UserControl.RESET);
            itemsType();
        }
    }
}
