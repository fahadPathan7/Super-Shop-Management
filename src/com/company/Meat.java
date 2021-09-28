/*
Main use of this class:
1. to show meat product types
2. to do action according to user's choice
3. to calculate and store costs on meat products
*/

package com.company;

import java.util.Scanner;

public class Meat {
    Scanner sc = new Scanner(System.in); // to take user input
    String choice; // to input user's choice
    static double costs = 0.0; // to store user's cost on Meat products

    // constructor
    Meat() {
        itemsType();
    }

    // to show items and taking user's choice
    void itemsType() {
        System.out.println("\nMeat Items:");

        //* change here for updating products
        System.out.println("1. Chicken (Piece) - 3$");
        System.out.println("2. Duck (Piece)    - 3.5$");
        System.out.println("3. Cow meat (Kg)   - 8$");
        System.out.println("4. Goat meat (Kg)  - 7$");

        //* change here for updating products
        System.out.println("\nNotice:\ntype 1 to 4 for buying products.\ntype 0 to go to Home page.\ntype -1 to exit.\n");

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
            if (x >= 1 && x <= 4) check = true;
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
            double count = 0.0;
            System.out.print("Enter how much/ how many you want: ");
            try {
                String str = sc.nextLine(); // to avoid unnecessary input
                count = Double.parseDouble(str);

                if (count <= 0.0) {
                    System.out.println(UserControl.RED + "\nPlease enter a positive value." + UserControl.RESET);
                    itemsType();
                }
            } catch (Exception e) {
                System.out.println(UserControl.RED + "\nInvalid input! Please try again." + UserControl.RESET);

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
                costs += (3 * Math.floor(count));
                UserControl.memo("Chicken>(Piece)", 3, Math.floor(count));
            }
            else if (choice.equals("2")) {
                if (x == 0) {
                    System.out.println(UserControl.RED + "\nPlease enter valid amount (amount >= 1)." + UserControl.RESET);
                    itemsType();
                    return;
                }
                if (count > (double)x) Products.fractionErrorMessage();
                costs += (3.5 * Math.floor(count));
                UserControl.memo("Duck>(Piece)", 3.5, Math.floor(count));
            }
            else if (choice.equals("3")) {
                costs += (8 * count);
                UserControl.memo("Cow meat>(Kg)", 8, count);
            }
            else if (choice.equals("4")) {
                costs += (7 * count);
                UserControl.memo("Goat meat>(Kg)", 7, count);
            }

            // asking the user if he wants more
            System.out.println("\nWant more meat?");
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
