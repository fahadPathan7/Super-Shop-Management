/*
Main use of this class:
1. to show vegetables product types
2. to do action according to user's choice
3. to calculate and store costs on vegetables products
*/

package com.company;

import java.util.Scanner;

public class Vegetables {
    Scanner sc = new Scanner(System.in); // to take user input
    String choice; // to input user's choice
    static double costs = 0.0; // to store user's cost on Vegetables products

    // constructor
    Vegetables() {
        itemsType();
    }

    // to show items and taking user's choice
    void itemsType() {
        System.out.println("\nVegetables Items:");

        //* change here for updating products
        System.out.println("1. Broccoli (Piece) - 0.7$");
        System.out.println("2. Potato (kg)      - 0.4$");
        System.out.println("3. Cabbage (Piece)  - 0.5$");
        System.out.println("4. Green Bean (Kg)  - 1$");
        System.out.println("5. Capsicum (Kg)    - 2$");

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
                if (x == 0) {
                    System.out.println(UserControl.RED + "\nPlease enter valid amount (amount >= 1)." + UserControl.RESET);
                    itemsType();
                    return;
                }
                if (count > (double)x) Products.fractionErrorMessage();
                costs += (0.7 * Math.floor(count));
                UserControl.memo("Broccoli>(Piece)", 0.7, Math.floor(count));
            }
            else if (choice.equals("2")) {
                costs += (0.4 * count);
                UserControl.memo("Potato>(kg)", 0.4, count);
            }
            else if (choice.equals("3")) {
                if (x == 0) {
                    System.out.println(UserControl.RED + "\nPlease enter valid amount (amount >= 1)." + UserControl.RESET);
                    itemsType();
                    return;
                }
                if (count > (double)x) Products.fractionErrorMessage();
                costs += (0.5 * Math.floor(count));
                UserControl.memo("Cabbage>(Piece)", 0.5, Math.floor(count));
            }
            else if (choice.equals("4")) {
                costs += (1 * count);
                UserControl.memo("Green Bean>(Kg)", 1, count);
            }
            else if (choice.equals("5")) {
                costs += (2 * count);
                UserControl.memo("Capsicum>(Kg)", 2, count);
            }

            // asking the user if he wants more
            System.out.println("\nWant more vegetables?");
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
