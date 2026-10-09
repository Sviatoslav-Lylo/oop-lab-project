package LabProject.model;

public class Doctor extends Person {
    private String specialization;

    public Doctor(String name, int id, String specialization) {
        super(name, id);
        this.specialization = specialization;
    }

    public Doctor() {
    }

    public void setSpecialization(String specialization) { this.specialization = specialization; }
    
    public String getSpecialization() {
        return specialization;
    }
    
    @Override
    public void showProfile() {
        System.out.println("-------------\n[Лікар] \nID: " + getId() + "\nІм'я: " + getName() + "\nФах: " + specialization + "\n------------\n");
    }
}