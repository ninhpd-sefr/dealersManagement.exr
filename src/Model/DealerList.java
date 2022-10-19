package Model;

import Controller.LogIn;
import entity.Dealer;
import Model.Config;
import java.io.IOException;
import java.util.AbstractList;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import tools.MyTool;

public class DealerList extends ArrayList<Dealer> {

    Scanner sc = new Scanner(System.in);
    LogIn logInObj = null;
    private static final String PHONEPATTERN = "\\d{9}|\\d{11}";
    private String dataFile = "";
    boolean changed = false; // whether data in the list changed or not 

    public DealerList(LogIn loginObj) {
        super();
        this.logInObj = loginObj;
    }

    private void loadDealerFromFile() throws IOException {
        List<String> readDealer = MyTool.readLinesFromFile(dataFile);
        for (String element : readDealer) {
            Dealer newDealer = new Dealer(element);
            this.add(newDealer);
        }
    }

    public void iniWithFile() throws IOException {
        Config cR = new Config();
        dataFile = cR.getDealerFile();
        loadDealerFromFile();
    }

    public DealerList getContinuingList() {
        DealerList result = new DealerList(logInObj);
        for (Dealer thi : this) {
            if (thi.isContinuing() == true) {
                result.add(thi);
            }
        }
        return result;
    }

    public DealerList getUnContinuingList() {
        DealerList result = new DealerList(logInObj);
        for (Dealer thi : this) {
            if (thi.isContinuing() == false) {
                result.add(thi);
            }
        }
        return result;
    }

    private int searchDealer(String ID) {
        int count = this.size();
        for (int i = 0; i < count; i++) {
            if (this.get(i).getID().endsWith(ID)) {
                return i;
            }
        }
        return -1;
    }

    public void searchDealer() {
        System.out.println("Enter ID to find Dealer");
        String IdDealer = sc.nextLine().toUpperCase();
        int pos = searchDealer(IdDealer);
        if (pos < 0) {
            System.out.println("Not Found");
        } else {
            System.out.println("pos is: " + pos);
        }
    }

    public void addDealer() {
        String ID;
        String name; // dealer's name
        String addr; // dealer's address
        String phone; // 9 or 11 digits
        boolean continuing;
        int pos;
        do {
            ID = MyTool.readpattern("ID of new dealer", Dealer.ID_FORMAT);
            ID = ID.toUpperCase();
            pos = searchDealer(ID);
            if (pos > 0) {
                System.out.println("ID is duplicated");
            }
        } while (pos >= 0);
        name = MyTool.readNonBlank("Name of new dealer: ").toUpperCase();
        addr = MyTool.readNonBlank("Address of new dealer: ");
        phone = MyTool.readpattern("Phone number: ", Dealer.PHONE_FORMAT);
        continuing = true; // default value for new dealer
        Dealer d = new Dealer(ID, name, addr, phone, continuing);
        this.add(d);
        System.out.println("New dealer has been added");
        changed = true;
    }

    public boolean removeDealer() {
        boolean success = false;
        int pos;
        System.out.println("Dealer's ID needs updating: ");
        String ID = MyTool.sc.nextLine().toUpperCase();
        pos = searchDealer(ID);
        if (pos < 0) {
            System.out.println("Not Found");
        } else {
            this.remove(pos);
            System.out.println("Removed");
            changed = true;
            success = true;
        }
        
        return success;
    }

    public boolean updateDealer() {
        boolean success = false;
        System.out.println("Dealer's ID needs updating: ");
        String ID = MyTool.sc.nextLine().toUpperCase();
        int pos = searchDealer(ID);
        if (pos < 0) {
            System.out.println("Dealer " + ID + " Not Found");
        } else {
            Dealer d = this.get(pos);
            String newName = ""; // update name
            System.out.println("New name, enter for omitting");
            newName = MyTool.sc.nextLine().trim().toUpperCase();
            if (!newName.isEmpty()) {
                d.setName(newName);
                changed = true;
            }
            String addr = ""; // update address
            System.out.println("New address, enter for omitting");
            addr = MyTool.sc.nextLine().trim().toUpperCase();
            if (!addr.isEmpty()) {
                d.setAddr(addr);
                changed = true;
            }
            String phone = ""; // update phone
            System.out.println("New PHone, enter for omitting");
            phone = MyTool.sc.nextLine().trim().toUpperCase();
            if (!phone.isEmpty()) {
                d.setPhone(phone);
                changed = true;
            }
            d.setContinuing(true);
            success = true;
            System.out.println("updated");
        }
        return success;

    }

    public void printAllDealers() {
        if (this.isEmpty()) {
            System.out.println("Empty List!");
        } else {
            for (Dealer thi : this) {
                System.out.println(thi.toString());
            }
        }
    }

    public void printContinuingDealers() {
        this.getContinuingList().printAllDealers();
    }

    public void printUnContinuingDealers() {
        this.getUnContinuingList().printAllDealers();
    }

    public void writeDealerToFile() throws IOException {
        System.out.println("Saved");
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
