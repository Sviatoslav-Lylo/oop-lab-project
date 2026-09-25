package LabProject.service;

import LabProject.model.Doctor;
import LabProject.model.Patient;

public interface PeopleManager {
    public boolean registerPatient(Patient p);
    public void dischargePatient(int id);
    public boolean registerDoctor(Doctor p);
    public void dischargeDoctor(int id);
}