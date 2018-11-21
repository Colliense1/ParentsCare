
package com.example.colliensepodder.parentscare;

public class Doctorinfo {

    private String doctorName;
    private String doctorNumber;
    private String doctorEmail;
    public String Speciality;


    private String avatar;


    public Doctorinfo(String doctorName, String doctorNumber,String doctorEmail,String Speciality ) {
        this.doctorName = doctorName;
        this.doctorNumber = doctorNumber;
        this.doctorEmail = doctorEmail;
        this.Speciality = Speciality;
        //this.avatar = avatar;
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
    public String getSpeciality(){
        return Speciality;
    }
//    public String getAvatar(){
//        return avatar;
//    }


}
