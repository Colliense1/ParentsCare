package com.example.colliensepodder.parentscare;
/**
 * Created by colliensepodder on 8/30/2018.
 */
public class Contact {
    private String contactName;
    private String contactNumber;
    private String contactEmail;
    public Contact(String contactName, String contactNumber, String contactEmail) {
        this.contactName = contactName;
        this.contactNumber = contactNumber;
        this.contactEmail = contactEmail;

    }

    public String getContactName() {
        return contactName;
    }

    public String getContactNumber() {
        return contactNumber;
    }
    public String getContactEmail() {
        return contactEmail;
    }
}
