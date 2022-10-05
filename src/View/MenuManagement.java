/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.AccountList;
import Controller.DealerList;
import Controller.DeliveriList;
import Model.LogIn;
import entity.Account;
import java.io.IOException;
import tools.MyTool;

/**
 *
 * @author ACER
 */
public class MenuManagement {
    public static void dealerManagement(LogIn logInObj) throws IOException{
          System.out.println("HELLO DEALER MANAGER");
            String[] options = {"Add new dealer", "Search a dealer", "remove a dealer", "Update a dealer", "Print all dealers", "Print Un-continuing dealers", "Print Continuing dealers", "Write to file"};
            Menu menu = new Menu(options);
            DealerList dList = new DealerList(logInObj);
            dList.iniWithFile();
            int choice = 0;
            do {
                choice = menu.getChoice("------------Manageing dealers----------");
                switch (choice) {
                    case 1:
                        dList.addDealer();
                        break;
                    case 2:
                        dList.searchDealer();
                        break;
                    case 3:
                        dList.removeDealer();
                        break;
                    case 4:
                        dList.updateDealer();
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
                    default:
                        if (dList.isChanged()) {
                            boolean res = MyTool.readBool("Data changed. Write to file?");
                            if (res == true) {
                                dList.writeDealerToFile();
                            }
                        }
                }
            } while (choice > 0 && choice <= menu.size());
            System.out.println("Finish");
    }
    public static void deliveriManagement(LogIn logInObj) throws IOException{
          System.out.println("HELLO DELIVERIES MANAGER");
            String[] options = {"Add new deliveri", "Search a deliveri", "remove a deliveri", "Update a deliveri", "Print all deliveri", "Print Un-continuing deliveri", "Print Continuing deliveri", "Write to file"};
            Menu menu = new Menu(options);
            DeliveriList dList = new DeliveriList(logInObj);
            dList.iniWithFile();
            int choice = 0;
            do {
                choice = menu.getChoice("------------Manageing deliveris----------");
                switch (choice) {
                    case 1:
                        dList.addDeliveri();
                        break;
                    case 2:
                        dList.searchDeliveri();
                        ;
                        break;
                    case 3:
                        dList.removeDeliveri();
                        break;
                    case 4:
                        dList.updateDeliveri();
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
                    default:
                        if (dList.isChanged()) {
                            boolean res = MyTool.readBool("Data changed. Write to file?");
                            if (res == true) {
                                dList.writeDeliveriToFile();
                            }
                        }
                }
            } while (choice > 0 && choice <= menu.size());
            System.out.println("Finish");
    }
    
    
    public static void accountManagement(LogIn logInObj) throws IOException{
          System.out.println("HELLO ACCOUNT MANAGER");
            String[] options = {"Add new Account", "Search a Account by accName", "Remove a Account by accName", "Update Account by accName", "Print all Account","Save file"};
            Menu menu = new Menu(options);
            AccountList aList = new AccountList(logInObj);
            aList.iniWithFile();
            int choice = 0;
            do {
                choice = menu.getChoice("------------Manageing Accounts----------");
                switch (choice) {
                    case 1:
                        aList.addAccount();
                        break;
                    case 2:
                        aList.searchDealer();
                        ;
                        break;
                    case 3:
                        aList.removeAcount();
                        break;
                    case 4:
                        aList.updateAccount();
                        break;
                    case 5:
                        aList.printAllAccount();
                        break;
                    case 6:
                        aList.writeAccountToFile();
                        break;
                    default:
                        if (aList.isChanged()) {
                            boolean res = MyTool.readBool("Data changed. Write to file?");
                            if (res == true) {
                                aList.writeAccountToFile();
                            }
                        }
                }
            } while (choice > 0 && choice < menu.size());
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
