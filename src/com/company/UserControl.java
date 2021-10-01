/*
Main use of this class:
1. to show welcome message
2. to verify id and pass for already existing user
3. to create new id and pass for new user
4. to create user memo
5. to show total cost before exiting the program

Extra use:
1. to create directory in E drive
2. to control spacing on output
*/

package com.company;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.DecimalFormat;
import java.util.*;

public class UserControl {
    Scanner sc = new Scanner(System.in); // to take user input
    static String id, pass; // to take and store user id and pass
    static double totalCost = 0.0; // for calculating total cost of user
    static int productCount = 0; // storing bought product types count of the user
    static int space = 18, nameSpace = 18, unitSpace = 8, costSpace = 14,
            countSpace = 13, productCountSpace = 5; // to control spacing on output
    static DecimalFormat df = new DecimalFormat("0.000"); // to format cost

    //colors
    static final String RED = "\u001B[31m";
    static final String RESET = "\u001B[0m";
    static final String GREEN = "\u001B[32m";
    static final String YELLOW = "\u001B[33m";

    // constructor
    UserControl() {
        createDir();
        welcomeMessage();
        createMemo();
        writeDateAndTime();
    }

    // to show welcome message
    void welcomeMessage() {
        sc = new Scanner(System.in);

        // showing welcome message
        System.out.println("Welcome to Fahad's Super Shop");
        System.out.println("1. Login");
        System.out.println("2. Registration");
        System.out.print("Please Enter your choice: ");

        String lr = sc.nextLine(); // using nextLine() to avoid any kind of exception
        System.out.println();

        // matching user input and doing action
        if (lr.equals("1")) {
            loginForm();
        } else if (lr.equals("2")) {
            regForm();
        } else {
            System.out.println(RED + "Invalid input. Please try again.\n" + RESET);
            welcomeMessage();
        }
    }

    // creating directory and login file
    void createDir() {
        File dir = new File("E:/Super Shop Management");
        dir.mkdir();

        File dir1 = new File("E:/Super Shop Management/User info");
        dir1.mkdir();

        try {
            File login = new File("E:/Super Shop Management/User info/login.txt");
            login.createNewFile();
        } catch (Exception e) {
            System.out.println("File creation error!");
        }
    }

    // checking for valid login information
    void loginForm() {
        boolean idFound = false, passFound = false; // to check if the id and pass match
        sc = new Scanner(System.in);

        // taking user id
        System.out.println("Login Form:");
        System.out.print("Please enter your id: ");
        id = sc.nextLine();

        try {
            // opening file and reading previously saved information
            File read = new File("E:/Super Shop Management/User info/login.txt");

            sc = new Scanner(read);
            while (sc.hasNextLine()) {
                String sTemp = sc.nextLine();
                String[] sTemp1 = sTemp.split(">");

                if (id.equals(sTemp1[0])) {
                    idFound = true;

                    // taking password
                    System.out.print("Please enter your pass: ");
                    Scanner sc1 = new Scanner(System.in); // temporary scanner
                    pass = sc1.nextLine();

                    if (pass.equals(sTemp1[1])) {
                        passFound = true;
                        System.out.println(GREEN + "\nLogin Successful." + RESET);
                    }
                    pass = sTemp1[1];
                    break;
                }
            }
            sc.close();
        } catch (Exception e) {
            System.out.println(RED + "\nSomething went wrong. Login failed!" + RESET);
        }

        // if the id and pass do not match
        if (!idFound) {
            System.out.println(RED + "\nId does not exist! Please register.\n" + RESET);
            welcomeMessage();
        } else if (!passFound) {
            System.out.println(RED + "\nWrong Password!\n" + RESET);
            //welcomeMessage();
            //todo: create a section to reset password. (using email)
            try {
                Mail.email();
            } catch (Exception e) {
            }

            welcomeMessage();
        }
    }

    // to verify if the id already exists or not
    boolean verify() {
        boolean found = false;
        try {
            // opening file and checking if id already exists or not
            File read = new File("E:/Super Shop Management/User info/login.txt");

            sc = new Scanner(read);
            while (sc.hasNextLine()) {
                String sTemp = sc.nextLine();
                String[] sTemp1 = sTemp.split(">");

                if (id.equals(sTemp1[0])) {
                    found = true;
                    break;
                }
            }
            sc.close();
        } catch (Exception e) {
            System.out.println(RED + "Something went wrong while verifying Id!" + RESET);
        }

        //System.out.println(found); //! test
        return !found;
    }

    // doing registration for new user
    void regForm() {
        sc = new Scanner(System.in);

        // taking new id and pass
        System.out.println("Registration Form:");
        System.out.print("Please enter your email id: ");
        id = sc.nextLine();

        // verifying id
        if (!verify()) {
            System.out.println(RED + "\nId already exists! Please try again.\n" + RESET);
            welcomeMessage();
        }

        // creating and sending new password
        System.out.println("\nPlease wait...\nYour password will be sent to your email.\n");
        Random rand = new Random();
        long temp = rand.nextLong(1000000) + 1000; // creating a random password
        setPass(temp);
        Mail.emailRecipient = id;
        Mail.sendEmail(); // sending password via email

        if (!Mail.sent) {
            welcomeMessage();
        }

        // writing on file for further use of that id and pass
        try {
            FileWriter write = new FileWriter("E:/Super Shop Management/User info/login.txt", true);
            BufferedWriter b = new BufferedWriter(write);
            b.write(id + ">" + pass);
            b.newLine();

            System.out.println(GREEN + "Registration Successful.\n" +
                    "Please remember Password." + RESET);

            b.close();
            write.close();
        } catch (Exception e) {
            System.out.println(RED + "\nSomething went wrong. Registration failed! Try again.\n" + RESET);
            regForm();
        }
    }

    // to set new password
    static void setPass(long temp) {
        pass = Long.toString(temp);
    }

    // creating directory and file for memo
    void createMemo() {
        File memoDir = new File("E:/Super Shop Management/Memo");
        memoDir.mkdir();

        try {
            File personMemo = new File("E:/Super Shop Management/Memo/" + id + ".txt");
            personMemo.createNewFile();
        } catch (Exception e) {
            System.out.println(RED + "\nSomething went wrong while creating memo file!\n" + RESET);
        }
    }

    // writing date and time on user memo
    public void writeDateAndTime() {
        try {
            FileWriter writeMemo = new FileWriter("E:/Super Shop Management/Memo/" + id + ".txt", true);
            BufferedWriter b = new BufferedWriter(writeMemo);

            Date date = new Date();
            b.write("Login time: " + String.valueOf(date) + "\n\n");
            b.write("Bought items:\n");

            b.close();
            writeMemo.close();
        } catch (Exception e) {
            System.out.println(RED + "\nSomething went wrong while Writing on memo file!\n" + RESET);
        }
    }

    // writing on user memo
    public static void memo(String name, String unit, double cost, double count) {
        productCount++;

        try {
            FileWriter writeMemo = new FileWriter("E:/Super Shop Management/Memo/" + id + ".txt", true);
            BufferedWriter b = new BufferedWriter(writeMemo);

            b.write(productCount + ".");
            for (int i = 0; i < productCountSpace - Integer.toString(productCount).length(); i++) b.write(' ');
            b.write("Item name: " + name);
            for (int i = 0; i < nameSpace - name.length(); i++) b.write(' ');
            b.write("Cost (per " + unit + ")");
            for (int i = 0; i < unitSpace - unit.length(); i++) b.write(' ');
            b.write(" : " + cost + "$");
            for (int i = 0; i < costSpace - Double.toString(cost).length(); i++) b.write(' ');
            b.write("Amount/ Count: " + count);
            for (int i = 0; i < countSpace - Double.toString(count).length(); i++) b.write(' ');
            b.write("Total: " + df.format(cost * count) + "$");
            b.newLine();

            b.close();
            writeMemo.close();

            // showing user what he has bought.
            System.out.println(GREEN + "\nSuccessfully bought:\n" + "Item name: " + name + ", Amount/ Count: " + count +
                    " (" + unit + ")" + RESET);
        } catch (Exception e) {
            System.out.println(RED + "\nSomething went wrong while Writing on memo file!\n" + RESET);
        }
    }

    // for showing total cost of user before exiting program.
    static void showTotalCost() {
        totalCost += (Electronics.costs + Cosmetic.costs + Grocery.costs + Vegetables.costs + Meat.costs);
        System.out.print("\n\nTotal cost is: " + df.format(totalCost));
        System.out.println("$\nThank you for visiting us.\n");

        // writing total cost on memo file
        try {
            FileWriter writeMemo = new FileWriter("E:/Super Shop Management/memo/" +
                    UserControl.id + ".txt", true);
            BufferedWriter b = new BufferedWriter(writeMemo);

            b.write("\nTotal cost: " + df.format(totalCost) + "$\n\n\n\n");

            b.close();
            writeMemo.close();
        } catch (Exception e) {
            System.out.println(RED + "\nSomething went wrong while Writing total cost on memo file!\n" + RESET);
        }

        System.exit(0); // to exit program
    }
}