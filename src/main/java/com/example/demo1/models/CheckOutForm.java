package com.example.demo1.models;


import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class CheckOutForm {

    public static final String FORNAMN_PATTERN = "^[a-zA-ZôíèÔÍÈåäöÅÄÖ\\- ]*$";
    public static final String EFTERNAMN_PATTERN = "^[a-zA-ZôíèÔÍÈåäöÅÄÖ\\- ]*$";
    public static final String PHONENUMBER_PATTERN= "^[\\d ]+$|^\\d+-[\\d ]+$";
    public static final String EMAIL_PATTERN= "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-]+$";
    public static final String ADRESS_PATTERN= "^([A-Za-z0-9ôíèÔÍÈåäöÅÄÖ.\\- ,])+$";
    public static final String POSTNUMMER_PATTERN="^\\d{5}$|^\\d{3} \\d{2}$";
    public static final String ORT_PATTERN="^[a-zA-ZôíèÔÍÈåäöÅÄÖ\\- ]*$";
    public static final String STAD_PATTERN="^[a-zA-ZôíèÔÍÈåäöÅÄÖ\\- ]*$";
    public static final int MAXLENGTH_SIZE=30;
    public static final int MAXLENGTHPHONE_SIZE=11;
    public static final int MINLENGTHPHONE_SIZE=10;
    public static final int MINLENGTHORT_SIZE=3;


    @NotNull
    @Size(max= MAXLENGTH_SIZE , message = "You are wrong")
    @Pattern(regexp = FORNAMN_PATTERN)
    private String Fornamn;

    @NotNull
    @Size(max= MAXLENGTH_SIZE , message = "wrong wrong...")
    @Pattern(regexp = EFTERNAMN_PATTERN)
    private String Efternamn;

    @NotNull
    @Size(min=MINLENGTHPHONE_SIZE, max=MAXLENGTHPHONE_SIZE)
    @Pattern(regexp = PHONENUMBER_PATTERN)
    private String phoneNumber;

    @NotNull
    @Pattern(regexp = EMAIL_PATTERN)
    private String email;

    @NotNull
    @Pattern(regexp = ADRESS_PATTERN)
    private String adress;

    @NotNull
    @Pattern(regexp = POSTNUMMER_PATTERN)
    private String postnummer;

    @NotNull
    @Size(max= MAXLENGTH_SIZE , min=MINLENGTHORT_SIZE, message = "wrong wrong...")
    @Pattern(regexp = ORT_PATTERN)
    private String ort;

    @NotNull
    @Pattern(regexp = STAD_PATTERN)
    private String stad;

    public String getStad() {
        return stad;
    }

    public void setStad(String stad) {
        this.stad = stad;
    }



    public String getOrt() {
        return ort;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }

    public String getPostnummer() {
        return postnummer;
    }

    public void setPostnummer(String postnummer) {
        this.postnummer = postnummer;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFornamn() {
        return Fornamn;
    }

    public void setFornamn(String fornamn) {
        Fornamn = fornamn;
    }

    public String getEfternamn() {
        return Efternamn;
    }

    public void setEfternamn(String efternamn) {
        Efternamn = efternamn;
    }

    @Override
    public String toString() {
        return "CheckOutForm{" +
                "Fornamn='" + Fornamn + '\'' +
                ", Efternamn='" + Efternamn + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", adress='" + adress + '\'' +
                ", postnummer='" + postnummer + '\'' +
                ", ort='" + ort + '\'' +
                ", stad='" + stad + '\'' +
                '}';
    }
}
