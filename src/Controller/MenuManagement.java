/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.AccountList;
import Model.DealerList;
import Model.DeliveriList;
import View.Menu;
import entity.Account;
import java.io.IOException;
import tools.MyTool;

/**
 *
 * @author ACER
 */
public class MenuManagement {

    public static void dealerManagement(LogIn logInObj) throws IOException {
        LogIn lgIn = new LogIn();
        System.out.println("HELLO DEALER MANAGER");
        String[] options = {"Add new dealer", "Search a dealer", "remove a dealer", "Update a dealer", "Print all dealers", "Print Un-continuing dealers", "Print Continuing dealers", "Write to file", "Logout", "Exit Program"};
        Menu menu = new Menu(options);
        DealerList dList = new DealerList(logInObj);
        dList.iniWithFile();
        boolean res = false;
        boolean success = false;
        int choice = 0;
        do {
            choice = menu.getChoice("------------Manageing dealers----------");
            switch (choice) {
                case 1:
                    dList.addDealer();
                    res = MyTool.readBool("Data changed. Write to file?");
                    if (res == true) {
                        dList.writeDealerToFile();
                    }
                    break;
                case 2:
                    dList.searchDealer();
                    break;
                case 3:
                    success = dList.removeDealer();
                    if (success) {
                        res = MyTool.readBool("Data changed. Write to file?");
                        if (res == true) {
                            dList.writeDealerToFile();
                        }
                    }
                    break;
                case 4:
                    success = dList.updateDealer();
                    if (success) {
                        res = MyTool.readBool("Data changed. Write to file?");
                        if (res == true) {
                            dList.writeDealerToFile();
                        }
                    }
                    break;
                case 5:
                    dList.printAllDealers();
                    break;
                case 6:
                    dList.printUnContinuingDealers();
                    break;
                case 7:
                    dList.printContinuingDealers();
                    break;
                case 8:
                    dList.writeDealerToFile();
                    break;
                case 9:
                    System.out.println("========================================");
                    System.out.println("Log out successfull");
                    lgIn.start();
                    break;
                case 10:
                    res = MyTool.readBool("Do you want to exit ?");
                    if (res == true) {
                        System.exit(0);
                    }
                    break;
                default:
                    if (dList.isChanged()) {
                        res = MyTool.readBool("Data changed. Write to file?");
                        if (res == true) {
                            dList.writeDealerToFile();
                        }
                    }
            }
        } while (choice > 0 && choice < menu.size());
        System.out.println("Finish");
    }

    public static void deliveriManagement(LogIn logInObj) throws IOException {
        LogIn lgIn = new LogIn();
        System.out.println("HELLO DELIVERIES MANAGER");
        String[] options = {"Add new deliveri", "Search a deliveri", "remove a deliveri", "Update a deliveri", "Print all deliveri", "Print Un-continuing deliveri", "Print Continuing deliveri", "Write to file", "Logout", "Exit Program"};
        Menu menu = new Menu(options);
        DeliveriList dList = new DeliveriList(logInObj);
        dList.iniWithFile();
        boolean res = false;
        boolean success = false;
        int choice = 0;
        do {
            choice = menu.getChoice("------------Manageing deliveris----------");
            switch (choice) {
                case 1:
                    dList.addDeliveri();
                    res = MyTool.readBool("Data changed. Write to file?");
                    if (res == true) {
                        dList.writeDeliveriToFile();
                    }
                    break;
                case 2:
                    dList.searchDeliveri();

                    break;
                case 3:
                    success = dList.removeDeliveri();
                    if (success) {
                        res = MyTool.readBool("Data changed. Write to file?");
                        if (res == true) {
                            dList.writeDeliveriToFile();
                        }
                    }
                    break;
                case 4:
                    success = dList.updateDeliveri();
                    if (success) {
                        res = MyTool.readBool("Data changed. Write to file?");
                        if (res == true) {
                            dList.writeDeliveriToFile();
                        }
                    }
                    break;
                case 5:
                    dList.printAllDeliveris();
                    break;
                case 6:
                    dList.printUnContinuingDeliveris();
                    break;
                case 7:
                    dList.printContinuingDeliveris();
                    break;
                case 8:
                    dList.writeDeliveriToFile();
                    break;
                case 9:
                    System.out.println("========================================");
                    System.out.println("Log out successfull");
                    lgIn.start();
                    break;
                case 10:
                    res = MyTool.readBool("Do you want to exit ?");
                    if (res == true) {
                        System.exit(0);
                    }
                    break;
                default:
                    if (dList.isChanged()) {
                        res = MyTool.readBool("Data changed. Write to file?");
                        if (res == true) {
                            dList.writeDeliveriToFile();
                        }
                    }
            }
        } while (choice > 0 && choice <= menu.size());
        System.out.println("Finish");
    }

    public static void accountManagement(LogIn logInObj) throws IOException {
        LogIn lgIn = new LogIn();
        System.out.println("HELLO ACCOUNT MANAGER");
        String[] options = {"Add new Account", "Search a Account by accName", "Remove a Account by accName", "Update Account by accName", "Print all Account", "Save file", "Logout", "Exit Program"};
        Menu menu = new Menu(options);
        AccountList aList = new AccountList(logInObj);
        aList.iniWithFile();
        boolean res = false;
        boolean success = false;
        int choice = 0;
        do {
            choice = menu.getChoice("------------Manageing Accounts----------");
            switch (choice) {
                case 1:
                    aList.addAccount();
                    res = MyTool.readBool("Data changed. Write to file?");
                    if (res == true) {
                        aList.writeAccountToFile();
                    }
                    break;
                case 2:
                    aList.searchAccount();
                    ;
                    break;
                case 3:
                    success = aList.removeAcount();
                    if (success) {
                        res = MyTool.readBool("Data changed. Write to file?");
                        if (res == true) {
                            aList.writeAccountToFile();
                        }
                    }
                    break;
                case 4:
                    success = aList.updateAccount();
                    if (success) {
                        res = MyTool.readBool("Data changed. Write to file?");
                        if (res == true) {
                            aList.writeAccountToFile();
                        }
                    }
                    break;
                case 5:
                    aList.printAllAccount();
                    break;
                case 6:
                    aList.writeAccountToFile();
                    break;
                case 7:
                    System.out.println("========================================");
                    System.out.println("Log out successfull");
                    lgIn.start();
                    break;
                case 8:
                    res = MyTool.readBool("Do you want to exit ?");
                    if (res == true) {
                        System.exit(0);
                    }
                    break;
                default:
                    if (aList.isChanged()) {
                        res = MyTool.readBool("Data changed. Write to file?");
                        if (res == true) {
                            aList.writeAccountToFile();
                        }
                    }
            }
        } while (choice > 0 && choice <= menu.size());
        System.out.println("Finish");
    }

    public static Account inputAccount() {
        System.out.println("--------------Login------------");
        String accName = MyTool.readNonBlank("Enter your account name: ");
        String pwd = MyTool.readNonBlank("Enter your passWord");
        String role = MyTool.readNonBlank("Enter your role");
        Account LoginAccount = new Account(accName, pwd, role);
        return LoginAccount;
    }

}
