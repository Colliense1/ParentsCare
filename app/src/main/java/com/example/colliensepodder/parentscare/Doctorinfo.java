
package com.example.colliensepodder.parentscare;

public class Doctorinfo {

    private String doctorName;
    private String doctorNumber;
    private String doctorEmail;
    public String Speciality;


    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public void setDoctorNumber(String doctorNumber) {
        this.doctorNumber = doctorNumber;
    }

    public void setDoctorEmail(String doctorEmail) {
        this.doctorEmail = doctorEmail;
    }

    public void setSpeciality(String speciality) {
        Speciality = speciality;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    private String avatar;


    public Doctorinfo(String doctorName, String doctorNumber,String doctorEmail,String Speciality ) {
        this.doctorName = doctorName;
        this.doctorNumber = doctorNumber;
        this.doctorEmail = doctorEmail;
        this.Speciality = Speciality;
        //this.avatar = avatar;
    }
    public Doctorinfo(String doctorName, String doctorNumber,String doctorEmail,String Speciality,String avatar ) {
        this.doctorName = doctorName;
        this.doctorNumber = doctorNumber;
        this.doctorEmail = doctorEmail;
        this.Speciality = Speciality;
        this.avatar = avatar;
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
