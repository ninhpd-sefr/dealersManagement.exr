package tools;

import Model.DealerList;
import Controller.LogIn;
import View.Menu;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.text.ParseException;
import java.io.IOException;
import java.util.List;
import javax.xml.validation.Validator;

public class MyTool {

    public static final Scanner sc = new Scanner(System.in);

    public static boolean validStr(String str, String regEx) {
        return str.matches(regEx);
    }

    public static boolean validpassword(String str, int minLen) {
        if (str.length() < minLen) {
            return false;
        }
        return str.matches(".*[a-zA-Z]+.*")
                && // at least 1 character
                str.matches(".*[\\d]+.*")
                && // at least 1 digit 
                str.matches(".*[\\w]+.*");// at least 1 special char

    }

    public static Date parseDate(String dateStr, String dateFormat) {
        SimpleDateFormat dF = (SimpleDateFormat) SimpleDateFormat.getInstance();
        dF.applyPattern(dateFormat);
        try {
            long t = dF.parse(dateStr).getTime();
            return new Date(t);
        } catch (ParseException e) {
            System.out.println(e);
        }
        return null;
    }

    public static String dataTostr(Date date, String dateFormat) {
        SimpleDateFormat dF = (SimpleDateFormat) SimpleDateFormat.getInstance();
        dF.applyPattern(dateFormat);
        try {
            String str = String.valueOf(date);
            return new String(str);
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public static boolean parseBool(String boolStr) {
        char c = boolStr.trim().toUpperCase().charAt(0);
        return (c == '1' || c == 'Y' || c == 'T');
    }

    public static String readNonBlank(String message) {
        String input = "";
        do {
            System.out.println(message + ": ");
            input = sc.nextLine().trim();
        } while (input.isEmpty() || input.contains(" "));
        return input;
    }

    public static String readNonSpace(String message) {
        String input = "";
        do {
            System.out.println(message + ": ");
            input = sc.nextLine().trim();
        } while (input.contains(" "));
        return input;
    }

    public static String readpattern(String message, String pattern) {
        String input = "";
        boolean valid;
        do {
            System.out.println(message + ": ");
            input = sc.nextLine().trim();
            valid = validStr(input, pattern);
        } while (!valid);
        return input;
    }

    public static boolean readBool(String message) {
        String input;
        System.out.println(message + "[1/0-Y/N-T/F]: ");
        input = sc.nextLine().trim();
        if (input.isEmpty()) {
            return false;
        }
        char c = Character.toUpperCase(input.charAt(0));
        return (c == '1' || c == 'Y' || c == 'T');
    }

    public static List<String> readLinesFromFile(String filename) throws IOException {
        FileReader f = new FileReader(filename);
        BufferedReader bf = new BufferedReader(f);

        List<String> list = new ArrayList<>();
        while (bf.ready()) {
            String s = bf.readLine();
            list.add(s);
        }
        f.close();
        bf.close();
        return list;
//        get list acc from file
    }

    public static void writeFile(String filename, List list) throws IOException {
        FileWriter writer = new FileWriter(filename);
        String str = "";
        for (int i = 0; i < list.size(); i++) {
            str += list.get(i) + "\n";
        }
        writer.write(str);
        writer.close();
    }

    public static String inputRole(String welcome) {
        String str = "";
        boolean cont = true;
        Scanner sc = new Scanner(System.in);
        String acc1 = "Acc-1";
        String acc2 = "Acc-2";
        do {
            try {
                System.out.print(welcome);
                str = sc.nextLine();
                if (!str.isEmpty()) {
                    if (str.equalsIgnoreCase(acc1) || str.equalsIgnoreCase(acc2)) {
                        cont = false;
                    } else {
                        cont = true;
                    }
                }
            } catch (Exception e) {
                System.out.print(welcome);
            }
        } while (cont);
        return str;
    }

    public static String updateRole(String welcome) {
        String str = "";
        boolean cont = true;
        Scanner sc = new Scanner(System.in);
        String acc1 = "Acc-1";
        String acc2 = "Acc-2";
        do {
            try {
                System.out.println(welcome + ": ");
                str = sc.nextLine();
                if (!str.isEmpty()) {
                    if (str.equalsIgnoreCase(acc1) || str.equalsIgnoreCase(acc2)) {
                        cont = false;
                    } else {
                        cont = true;
                    }
                } else {
                    cont = false;
                }
            } catch (Exception e) {
                System.out.print(welcome);
            }
        } while (cont);
        return str;
    }

}
