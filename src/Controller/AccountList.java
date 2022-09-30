/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Config;
import Model.LogIn;
import entity.Account;
import entity.Dealer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import tools.MyTool;
import static tools.MyTool.inputRole;

/**
 *
 * @author ACER
 */
public class AccountList extends ArrayList<Account> {

    Scanner sc = new Scanner(System.in);
    LogIn logInObj = null;
    private static final String PHONEPATTERN = "\\d{9}|\\d{11}";
    private String dataFile = "";
    boolean changed = false;

    public AccountList(LogIn loginObj) {
        super();
        this.logInObj = loginObj;
    }

    private void loadAccountFromFile() throws IOException {
        List<String> readAccount = MyTool.readLinesFromFile(dataFile);
        for (String element : readAccount) {
            Account newAccount = new Account(element);
            this.add(newAccount);
        }
    }

    public void iniWithFile() throws IOException {
        Config cR = new Config();
        dataFile = cR.getAccountFile();
        loadAccountFromFile();
    }

    private int searchAccount(String name) {
        int count = this.size();
        for (int i = 0; i < count; i++) {
            if (this.get(i).getAccName().toLowerCase().contains(name.toLowerCase())) {
                return i;
            }
        }
        return -1;
    }

    public void searchDealer() {
        System.out.println("Enter Name to find Account");
        String name = sc.nextLine();
        int pos = searchAccount(name);
        if (pos < 0) {
            System.out.println("Not Found");
        } else {
            System.out.println("pos is: " + pos);
        }
    }

    public void addAccount() {
        String accName = "";
        String pwd = "";
        String role = "";
        int pos;
        do {
            accName = MyTool.readNonBlank("Enter your account name: ");
            pos = searchAccount(accName);
            if (pos >= 0) {
                System.out.println("AccName is duplicated");
            }
        } while (pos >= 0);

        pwd = MyTool.readNonBlank("Enter your passWord");
        role = inputRole("Enter your role");
        Account acc = new Account(accName, pwd, role);
        this.add(acc);
        System.out.println("New account has been added");
        changed = true;
    }

    public void removeAcount() {
        int pos;
        String accName;
        accName = MyTool.readNonBlank("Enter your account name: ");
        pos = searchAccount(accName);
        if (pos < 0) {
            System.out.println("Not Found");
        } else {
            this.remove(pos);
            System.out.println("Removed");
            changed = true;
        }
    }

    public void updateAccount() {
        int pos;
        String accName;
        accName = MyTool.readNonBlank("Enter your account name: ");
        pos = searchAccount(accName);
        if (pos < 0) {
            System.out.println("Account: " + accName + "not found");
        } else {
            Account acc = this.get(pos);
            String newPwd = "";
            String newRole = "";
            newPwd = MyTool.readNonBlank("Enter your passWord");
            if (!newPwd.isEmpty()) {
                acc.setPwd(newPwd);
                changed = true;
            }
            newRole = inputRole("Enter your role");
            if (!newRole.isEmpty()) {
                acc.setRole(newRole);
                changed = true;
            }
            System.out.println("updated");
        }

    }
    
     public void printAllAccount() {
        if (this.isEmpty()) {
            System.out.println("Empty List!");
        } else {
            for (Account thi : this) {
                System.out.println(thi.toString());
            }
        }
    }
    
     public void writeAccountToFile() throws IOException {
        if (changed) {
            MyTool.writeFile(dataFile, this);
            changed = false;
        }
    }

    public boolean isChanged() {
        return changed;
    }

    public void setChanged(boolean changed) {
        this.changed = changed;
    }

}
