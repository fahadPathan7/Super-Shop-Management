/*
Main use of this class:
1. to show all product types
2. to do action according to user's choice
3. to show error message where fraction amount is not possible
*/

package com.company;

import java.util.Scanner;

public class Products {
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

        //* change here for updating products
        System.out.println("1. Electronics");
        System.out.println("2. Cosmetic");
        System.out.println("3. Grocery");
        System.out.println("4. Vegetables");
        System.out.println("5. Meat");

        //* change here for updating products
        System.out.println("\nNotice:\ntype 1 to 5 for buying products.\ntype -1 to exit.\n");

        System.out.print("Enter your choice: ");
        choice = sc.nextLine();

        processChoice();
    }

    //* change here for updating products
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

    // show error message where fraction amount is not possible.
    static void fractionErrorMessage() {
        System.out.println(UserControl.YELLOW + "\nFraction amount is not possible for this item.");
        System.out.println("Taking floor value as input" + UserControl.RESET);
    }
}
