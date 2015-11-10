package in.gov.hp.aadhaar.hpsrdh;

/**
 * Created by kuush on 8/26/2015.
 */
public  class EConstants {

    public static final String url_Generic = "http://hp.gov.in/uidreport/AWW.svc" ;
   public static final String url_Generic_Search = "http://hp.gov.in/uidreport/AWW.svc/Search" ;
    public static final String methord_Login = "signin";
    public static final String methord_Logout = "signout";
    public static final String methord_SearchEID = "searbyEID";
    public static final String methord_SearchAadaar = "searbyAadhaar";
    public static final String methord_fourParams = "searchfour";
    public static final String methord_fiveParams= "searchfive";
    public static final String url_Delemetre = "/";
    public static final String ProgressDialog_Message = "Please wait....";
    public static final String ErrorMessageUnknow = "Something Went Wrong. Please check your network connectivity";
    public static final String ErrorUserEmpty = "Username cannot be empty";
    public static final String ErrorPasswordEmpty = "Password cannot be empty";
    public static final String UserLogoutResult = "LogOutUserResult";
    public static final String UserLoginResult = "CheckUserResult";
    public static final String UserResultFiveP = "GetAllUsers5Result";
    public static final String UserResultFourP ="GetAllUsers4Result";
    public static final String UserResultAadhaarSearch = "GetWithAadhaarResult";
    public static final String UserResultEIDSearch ="GetWithEIDResult";
    public static final String IMEINumber = "0";
    public static final String ErrorMessageLoginScreen = "Either the username/password is not valid or you are already logged in";
    public static final String DateOFBirthDialog ="Please Select Your Date Of Birth";
    public static final String SelectDistricError = "Please Select District";
    public static final String DateOfBirthError = "Invalid Date OF Birth";
    public static final String NameError ="Name cannot be empty";
    public static final String Father_HusbandError = "Please enter Your Father's / Husband's Name";
   //Five Parameters
    public static final String D = "District";
    public static final String N = "Name";
    public static final String FH ="FHName";
    public static final String DOB = "Dob";
    public static final String P = "Pincode";
    //Four Parameters
    public static final String D4 = "District";
    public static final String N4 = "Name";
    public static final String FH4 ="FHName";
    public static final String DOB4 = "Dob";
    public static final String ErrorAadhaarnEId ="Please enter your Aadhaar no. or Enroll Id no.";
    public static final String AadhaarInvalid = "Aadhar ID is not valid";
    public static final String EIDInvalid ="Please enter a valid Enroll ID";
    public static final String UID = "UID";
    public static final String EID = "EID";
    public static final String NetworkError ="Network isn't available";
    public static final String EnycType="MD5";
    public static final String EnycError = "String to encript cannot be null or zero length";
    public static final int SPLASH_DISPLAY_LENGTH = 2500;
    public static final String ListEmpty = "No match found";

}
