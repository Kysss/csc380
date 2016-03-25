/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.Scanner;

/**
 *
 * @author KimBrian
 */
public abstract class Profile {

    private String firstName;
    private String lastName;
    private String userName;
    private String password;

    public Profile(String fn, String ln, String u) {
        firstName = fn;
        lastName = ln;
        userName = u;
    }
    //Making profile info

    public void setFirstname(String fn) {
        this.firstName = fn;
    }

    public void setLastname(String ln) {
        this.lastName = ln;
    }

    public void setUsername(String u) {
        this.userName = u;
    }

    public void setPassword(String p) {
        this.password = p;
    }
    //Viewing profile Informations

    public String getFirstname() {
        return firstName;
    }

    public String getLastname() {
        return lastName;
    }

    public String getUsername() {
        return userName;
    }

    public abstract String getAccountType();

    public String getPassword() {
        return password;
    }

    public boolean comparesPassword() {
        Scanner scan = new Scanner(System.in);
        String p = scan.nextLine();
        if (p.equals(password)) {
            System.out.println("You May Enter");
            return true;
        } else {
            System.out.println("Wrong Password. Closing.");
            return false;
        }
    }

}
