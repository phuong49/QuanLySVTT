package model;

public class Clas {
    private int clasID;
    private String clasName;
    private int maxNumber;
    private int clasNumber;


    public Clas(int clasID, String clasName, int maxNumber, int clasNumber) {
        this.clasID = clasID;
        this.clasName = clasName;
        this.maxNumber = maxNumber;
        this.clasNumber = clasNumber;
    }

    public Clas() {

    }

    public Clas(String clasName, int maxNumber, int clasNumber) {
        super();
        this.clasName = clasName;
        this.maxNumber = maxNumber;
        this.clasNumber = clasNumber;
    }


    public int getClasID() {
        return clasID;
    }

    public void setClasID(int clasID) {
        this.clasID = clasID;
    }

    public String getClasName() {
        return clasName;
    }

    public void setClasName(String clasName) {
        this.clasName = clasName;
    }

    public int getMaxNumber() {
        return maxNumber;
    }

    public void setMaxNumber(int maxNumber) {
        this.maxNumber = maxNumber;
    }

    public int getClasNumber() {
        return clasNumber;
    }

    public void setClasNumber(int clasNumber) {
        this.clasNumber = clasNumber;
    }
}

