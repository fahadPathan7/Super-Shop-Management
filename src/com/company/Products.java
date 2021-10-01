/*
Main use of this class:
1. to show all product types
2. to do action according to user's choice
*/

package com.company;

import java.util.Scanner;

public class Products {
    //! change here for adding or removing products.
    // name of product types
    String[] items = {"Electronics", "Cosmetic", "Grocery", "Vegetables", "Meat"};

    Scanner sc = new Scanner(System.in); // to take input of the user
    String choice; // to take the choice of user

    // constructor
    Products() {
        itemsType();
    }

    // to show items type and to take user's choice
    void itemsType() {
        System.out.println("\nWelcome to Fahad's Super Shop.");
        System.out.println("Items Types:");

        for (int i = 1; i <= items.length; i++) {
            System.out.println(i + ". " + items[i - 1]);
        }

        // to show user what to input
        System.out.println("\nNotice:\ntype 1 to " + items.length + " for buying products." +
                "\ntype -1 to exit.\n\r");

        System.out.print("Enter your choice: ");
        choice = sc.nextLine();

        processChoice();
    }

    //! Change here for adding or removing products
    // for taking action according to user's choice
    void processChoice() {
        if (choice.equals("1")) {
            new Electronics();
        }
        else if (choice.equals("2")) {
            new Cosmetic();
        }
        else if (choice.equals("3")) {
            new Grocery();
        }
        else if (choice.equals("4")) {
            new Vegetables();
        }
        else if (choice.equals("5")) {
            new Meat();
        }
        else if (choice.equals("-1")) {
            UserControl.showTotalCost();
        }
        else {
            System.out.println(UserControl.RED + "\nPlease enter valid input." + UserControl.RESET);

            itemsType();
        }
    }
}
