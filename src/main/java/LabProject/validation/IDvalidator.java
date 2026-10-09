package LabProject.validation;
import org.springframework.stereotype.Component;

import LabProject.collection.DoublyLinkedList;
import LabProject.model.Doctor;
import LabProject.model.Patient;

@Component
public class IDvalidator {
    public boolean checkIdOriginality(int id, DoublyLinkedList<Doctor> doctors, DoublyLinkedList<Patient> patients) {
        for(Doctor d : doctors) {
            if(d.getId() == id) return false;
        }
        for(Patient p : patients) {
            if(p.getId() == id) return false;
        }
        return true;
    }
}