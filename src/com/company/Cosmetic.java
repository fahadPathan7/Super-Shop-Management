/*
Main use of this class:
1. to show cosmetic product types
2. to do action according to user's choice
3. to calculate and store costs on cosmetic products
*/

package com.company;

import java.util.Scanner;

public class Cosmetic {
    //! change here for adding or removing products. (only here)
    // item_name>unit>price_per_unit>is_fraction_amount_allowed
    // example: Rice per kg is 0.8$ and fraction amount is possible. so it will be, Rice>Kg>0.8>Y
    String[] items = {"Face Cream>Piece>6>N", "Screen Cream>Piece>4>N", "Hair Oil>Piece>3>N", "Hair Shampoo>Piece>8>N",
            "Perfume>Piece>12>N"};

    Scanner sc = new Scanner(System.in);
    String choice; // to take user's choice
    static double costs = 0.0; // to store user's expenses

    Cosmetic() {
        itemsType();
    }

    // to show items to user
    void itemsType() {
        System.out.println("\nCosmetic Items:");

        // to show name, unit and price for each product
        for (int i = 1; i <= items.length; i++) {
            String[] temp = items[i - 1].split(">");

            System.out.print(i + ". " + temp[0] + " (" + temp[1] + ") ");
            for (int j = 0; j < UserControl.space - temp[0].length() - temp[1].length(); j++) {
                System.out.print(' ');
            }
            System.out.println("- " + temp[2] + "$");
        }

        // to show user what to input
        System.out.println("\nNotice:\ntype 1 to " + items.length + " for buying products." +
                "\ntype 0 to go to Home page.\ntype -1 to exit.\n\r");

        // taking user's choice
        System.out.print("Enter your choice: ");
        choice = sc.nextLine();

        // doing action according to user's choice
        processChoice();
    }

    // to check if the choice is ok to buy products
    public boolean isWithinRange(String str) {
        boolean check = true;
        try {
            Integer x = Integer.parseInt(str);

            if (x >= 1 && x <= items.length) check = true;
            else check = false;
        } catch(Exception e) {
            check = false;
        }

        return check;
    }

    // processing user's choice
    void processChoice() {
        if (isWithinRange(choice)) {
            String[] temp = items[Integer.parseInt(choice) - 1].split(">");

            // taking input of amount/ count
            double count = 0.0;
            System.out.print("Enter how much/ how many you want: ");
            try {
                String str = sc.nextLine(); // nextLine is used to avoid unnecessary input
                count = Double.parseDouble(str);

                if (count <= 0.0) {
                    System.out.println(UserControl.RED + "\nPlease enter a positive value." + UserControl.RESET);

                    itemsType();
                }
                else if (temp[3].equalsIgnoreCase("N") && count > (long)count) {
                    System.out.println(UserControl.RED + "\nFraction amount is not possible for this item!\nPlease try again."
                            + UserControl.RESET);

                    itemsType();
                }
            } catch (Exception e) {
                System.out.println(UserControl.RED + "\nInvalid input! Please try again." + UserControl.RESET);

                itemsType();
            }

            // calculating cost according to choice and amount/ count
            costs += (Double.parseDouble(temp[2]) * count);
            UserControl.memo(temp[0], temp[1], Double.parseDouble(temp[2]), count);

            // asking the user if he/she wants more
            System.out.println("\nWant more cosmetic?");
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
