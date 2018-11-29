package com.example.colliensepodder.parentscare;

public class AppointmentInfo {

    private String doctorappointmentTitle;
    private String doctorappointmentName;
    private String doctorappointmentLocation;
    //public String Speciality;
    public AppointmentInfo(String doctorappointmentTitle, String doctorappointmentName,String doctorappointmentLocation) {
        this.doctorappointmentTitle = doctorappointmentTitle;
        this.doctorappointmentName = doctorappointmentName;
        this.doctorappointmentLocation = doctorappointmentLocation;

    }

    public String getDoctorAppointmentTitle() {
        return doctorappointmentTitle;
    }

    public String getDoctorAppointmentName() {
        return doctorappointmentTitle;
    }
    public String getDoctorAppointmentLocation(){
        return doctorappointmentLocation;
    }

//    public String getAvatar(){
//        return avatar;
//    }


}