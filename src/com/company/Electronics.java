/*
Main use of this class:
1. to show electronics product types
2. to do action according to user's choice
3. to calculate and store costs on electronics products
*/

package com.company;

import java.util.Scanner;

public class Electronics {
    Scanner sc = new Scanner(System.in); // to take user input
    String  choice; // to input user's choice
    static double costs = 0.0; // to store user's cost on Electronics products

    // constructor
    Electronics() {
        itemsType();
    }

    // to show items and taking user's choice
    void itemsType() {
        System.out.println("\nElectronics Items:");

        //* change here for updating products
        System.out.println("1. Light (Piece)  - 3$");
        System.out.println("2. Fan (Piece)    - 50$");
        System.out.println("3. TV (Piece)     - 450$");
        System.out.println("4. AC (Piece)     - 1500$");
        System.out.println("5. Laptop (Piece) - 1200$");

        //* change here for updating products
        System.out.println("\nNotice:\ntype 1 to 5 for buying products.\ntype 0 to go to Home page.\ntype -1 to exit.\n\r");

        System.out.print("Enter your choice: ");
        choice = sc.nextLine();
        //System.out.println(choice); //! test

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
                UserControl.memo("Light>(Piece)", 3, Math.floor(count));
            }
            else if (choice.equals("2")) {
                if (x == 0) {
                    System.out.println(UserControl.RED + "\nPlease enter valid amount (amount >= 1)." + UserControl.RESET);
                    itemsType();
                    return;
                }
                if (count > (double)x) Products.fractionErrorMessage();
                costs += (50 * Math.floor(count));
                UserControl.memo("Fan>(Piece)", 50, Math.floor(count));
            }
            else if (choice.equals("3")) {
                if (x == 0) {
                    System.out.println(UserControl.RED + "\nPlease enter valid amount (amount >= 1)." + UserControl.RESET);
                    itemsType();
                    return;
                }
                if (count > (double)x) Products.fractionErrorMessage();
                costs += (450 * Math.floor(count));
                UserControl.memo("TV>(Piece)", 450, Math.floor(count));
            }
            else if (choice.equals("4")) {
                if (x == 0) {
                    System.out.println(UserControl.RED + "\nPlease enter valid amount (amount >= 1)." + UserControl.RESET);
                    itemsType();
                    return;
                }
                if (count > (double)x) Products.fractionErrorMessage();
                costs += (1500 * Math.floor(count));
                UserControl.memo("AC>(Piece)", 1500, Math.floor(count));
            }
            else if (choice.equals("5")) {
                if (x == 0) {
                    System.out.println(UserControl.RED + "\nPlease enter valid amount (amount >= 1)." + UserControl.RESET);
                    itemsType();
                    return;
                }
                if (count > (double)x) Products.fractionErrorMessage();
                costs += (1200 * Math.floor(count));
                UserControl.memo("Laptop>(Piece)", 1200, Math.floor(count));
            }

            // asking the user if he wants more
            System.out.println("\nWant more electronics?");
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
