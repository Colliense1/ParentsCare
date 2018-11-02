
package com.example.colliensepodder.parentscare;

public class Doctorinfo {
    private String doctorName;
    private String doctorNumber;
    private String doctorEmail;

    public Doctorinfo(String doctorName, String doctorNumber,String doctorEmail ) {
        this.doctorName = doctorName;
        this.doctorNumber = doctorNumber;
        this.doctorEmail = doctorEmail;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public String getDoctorNumber() {
        return doctorNumber;
    }
    public String getDoctorEmail(){
        return doctorEmail;
    }


}
