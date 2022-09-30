/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import static entity.Dealer.SEPARATOR;
import tools.MyTool;

/**
 *
 * @author ACER
 */
public class Deliveri  implements Comparable<Deliveri> {
      public static final char SEPARATOR = ',';
    public static final String ID_FORMAT = "D\\d{3}";
    public static final String PHONE_FORMAT = "\\d{9}|\\d{11}";
    private String ID;// template D000
    private String name;// dealers's name
    private String addr;// dealer's address
    private String phone;// 9 or 11 digits
    private boolean continuing;// whether this dealer still cooperates or not

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isContinuing() {
        return continuing;
    }

    public void setContinuing(boolean continuing) {
        this.continuing = continuing;
    }

    @Override
    public String toString() {
        return ID + SEPARATOR + name + SEPARATOR + addr + SEPARATOR + phone + SEPARATOR + continuing;
    }
    
     public Deliveri(String ID, String name, String addr, String phone, boolean continuing) {
        this.ID = ID;
        this.name = name;
        this.addr = addr;
        this.phone = phone;
        this.continuing = continuing;
    }
    public Deliveri(String line){
        String [] parts = line.split("" + this.SEPARATOR);
        ID = parts[0].trim();// dealer ID
        name = parts[1].trim();//dealers's name
        addr = parts[2].trim();//dealer's address 
        phone = parts[3].trim();// 9 or 11 digits
        continuing = MyTool.parseBool(parts[4]);
    }
    
     @Override
    public int compareTo(Deliveri o) {
       return o.ID.compareTo(ID);
    } 
}
