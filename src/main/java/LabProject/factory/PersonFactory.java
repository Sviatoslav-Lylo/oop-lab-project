package LabProject.factory;
import org.springframework.stereotype.Component;

import LabProject.model.Doctor;
import LabProject.model.Patient;
import LabProject.model.Person;

@Component
public class PersonFactory {
    public Person createPerson(PersonType type, String name, int id, String additionalInfo) {
        return switch (type) {
            case DOCTOR -> new Doctor(name, id, additionalInfo);
            case PATIENT -> new Patient(name, id, additionalInfo);
            default -> throw new IllegalArgumentException("Невідомий тип особи." + type);
        };
    }
}
