package in.gov.hp.aadhaar.hpsrdh;

import java.io.Serializable;

/**
 * Created by KD on 7/26/2015.
 */
public class UserPojo implements Serializable {

    private String Aadhaar;
    private String Resident_Name_user;
    private String Address_Pincode_User;

    private String Address_Landmark_User;
    private String Address_Locality_User;

    public String getDOB_user() {
        return DOB_user;
    }

    public void setDOB_user(String DOB_user) {
        this.DOB_user = DOB_user;
    }

    private String DOB_user;

    public String getAddress_Street_User() {
        return Address_Street_User;
    }

    public void setAddress_Street_User(String address_Street_User) {
        Address_Street_User = address_Street_User;
    }

    public String getAddress_Locality_User() {
        return Address_Locality_User;
    }

    public void setAddress_Locality_User(String address_Locality_User) {
        Address_Locality_User = address_Locality_User;
    }

    public String getAddress_Landmark_User() {
        return Address_Landmark_User;
    }

    public void setAddress_Landmark_User(String address_Landmark_User) {
        Address_Landmark_User = address_Landmark_User;
    }

    private String Address_Street_User;

    public String getAddress_Building_User() {
        return Address_Building_User;
    }

    public void setAddress_Building_User(String address_Building_User) {
        Address_Building_User = address_Building_User;
    }

    public String getCare_OFF_User() {
        return Care_OFF_User;
    }

    public void setCare_OFF_User(String care_OFF_User) {
        Care_OFF_User = care_OFF_User;
    }

    private String Address_Building_User;
    private String Care_OFF_User;

    public String getState_Name_User() {
        return State_Name_User;
    }

    public void setState_Name_User(String state_Name_User) {
        State_Name_User = state_Name_User;
    }

    public String getAddress_Pincode_User() {
        return Address_Pincode_User;
    }

    public void setAddress_Pincode_User(String address_Pincode_User) {
        Address_Pincode_User = address_Pincode_User;
    }

    private String State_Name_User;

    public String getGender_User() {
        return Gender_User;
    }

    public void setGender_User(String gender_User) {
        Gender_User = gender_User;
    }

    private String Gender_User;

    public String getDistrict_User() {
        return District_User;
    }

    public void setDistrict_User(String district_User) {
        District_User = district_User;
    }

    private String District_User;

    public String getEnrollID_User() {
        return EnrollID_User;
    }

    public void setEnrollID_User(String enrollID_User) {
        EnrollID_User = enrollID_User;
    }

    private String EnrollID_User;

    public String getResident_Name_user() {
        return Resident_Name_user;
    }

    public void setResident_Name_user(String resident_Name_user) {
        Resident_Name_user = resident_Name_user;
    }


    public String getAadhaar() {
        return Aadhaar;
    }

    public void setAadhaar(String aadhaar) {
        Aadhaar = aadhaar;
    }



}
