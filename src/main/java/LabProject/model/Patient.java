package LabProject.model;

public class Patient extends Person {
    private String diagnosis;

    public Patient(String name, int id, String diagnosis) {
        super(name, id);
        this.diagnosis = diagnosis;
    }

    public Patient() {
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) { this.diagnosis = diagnosis; }

    @Override
    public void showProfile() {
        System.out.println("------------\n[Patient] \nID: " + getId() + "\nName: " + getName() + "\nDiagnosis: " + diagnosis + "\n------------\n");
    }
}