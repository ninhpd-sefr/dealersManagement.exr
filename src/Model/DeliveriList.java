package Model;

import Controller.LogIn;
import entity.Deliveri;
import Model.Config;
import entity.Deliveri;
import java.io.IOException;
import java.util.AbstractList;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import tools.MyTool;

public class DeliveriList extends ArrayList<Deliveri> {

    Scanner sc = new Scanner(System.in);
    LogIn logInObj = null;
    private static final String PHONEPATTERN = "\\d{9}|\\d{11}";
    private String dataFile = "";
    boolean changed = false; // whether data in the list changed or not 

    public DeliveriList(LogIn loginObj) {
        super();
        this.logInObj = loginObj;
    }

    private void loadDeliveriFromFile() throws IOException {
        List<String> readDeliveri = MyTool.readLinesFromFile(dataFile);
        for (String element : readDeliveri) {
            Deliveri newDeliveri = new Deliveri(element);
            this.add(newDeliveri);
        }
    }

    public void iniWithFile() throws IOException {
        Config cR = new Config();
        dataFile = cR.getDeliveryFile();
        loadDeliveriFromFile();
    }

    public DeliveriList getContinuingList() {
        DeliveriList result = new DeliveriList(logInObj);
        for (Deliveri thi : this) {
            if (thi.isContinuing() == true) {
                result.add(thi);
            }
        }
        return result;
    }

    public DeliveriList getUnContinuingList() {
        DeliveriList result = new DeliveriList(logInObj);
        for (Deliveri thi : this) {
            if (thi.isContinuing() == false) {
                result.add(thi);
            }
        }
        return result;
    }

    private int searchDeliveri(String ID) {
        int count = this.size();
        for (int i = 0; i < count; i++) {
            if (this.get(i).getID().endsWith(ID)) {
                return i;
            }
        }
        return -1;
    }

    public void searchDeliveri() {
        System.out.println("Enter ID to find Deliveri");
        String IdDeliveri = sc.nextLine().toUpperCase();
        int pos = searchDeliveri(IdDeliveri);
        if (pos < 0) {
            System.out.println("Not Found");
        } else {
            System.out.println("pos is: " + pos);
        }
    }

    public void addDeliveri() {
        String ID;
        String name; // deliveri's name
        String addr; // deliveri's address
        String phone; // 9 or 11 digits
        boolean continuing;
        int pos;
        do {
            ID = MyTool.readpattern("ID of new deliveri", Deliveri.ID_FORMAT);
            ID = ID.toUpperCase();
            pos = searchDeliveri(ID);
            if (pos >= 0) {
                System.out.println("ID is duplicated");
            }
        } while (pos >= 0);
        name = MyTool.readNonBlank("Name of new deliveri: ");
        addr = MyTool.readNonBlank("Address of new deliveri: ");
        phone = MyTool.readpattern("Phone number: ", Deliveri.PHONE_FORMAT);
        continuing = true; // default value for new deliveri
        Deliveri d = new Deliveri(ID, name, addr, phone, continuing);
        this.add(d);
        System.out.println("New Deliveri has been added");
        changed = true;
    }

    public void removeDeliveri() {
        int pos;
        System.out.println("Enter ID to remove Deliveri");
        String IdDeliveri = sc.nextLine().toUpperCase();
        pos = searchDeliveri(IdDeliveri);
        if (pos < 0) {
            System.out.println("Not Found");
        } else {
            this.remove(pos);
            System.out.println("Removed");
            changed = true;
        }
    }

    public void updateDeliveri() {
        System.out.println("Deliveri's ID needs updating: ");
        String ID = MyTool.sc.nextLine().toUpperCase();
        int pos = searchDeliveri(ID);
        if (pos < 0) {
            System.out.println("Deliveri " + ID + " Not Found");
        } else {
            Deliveri d = this.get(pos);
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
        }

    }

    public void printAllDeliveris() {
        if (this.isEmpty()) {
            System.out.println("Empty List!");
        } else {
            for (Deliveri thi : this) {
                System.out.println(thi.toString());
            }
        }
    }

    public void printContinuingDeliveris() {
        this.getContinuingList().printAllDeliveris();
    }

    public void printUnContinuingDeliveris() {
        this.getUnContinuingList().printAllDeliveris();
    }

    public void writeDeliveriToFile() throws IOException {
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
