package LabProject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import LabProject.collection.DoublyLinkedList;
import LabProject.model.Doctor;
import LabProject.model.Patient;
import LabProject.service.Hospital;
import LabProject.service.JsonStorageService;
import LabProject.validation.AppointmentValidator;
import LabProject.validation.IDvalidator;

import static org.junit.jupiter.api.Assertions.*;

public class HospitalTest {
    private Hospital hospital;

    @BeforeEach // Before each test
    void setup() {
        IDvalidator idValidator = new IDvalidator();
        AppointmentValidator appValidator = new AppointmentValidator();
        JsonStorageService storageService = new JsonStorageService();

        hospital = new Hospital(idValidator, appValidator, storageService);
    }

    // Positive scenarios
    @Test
    void testRegisterPatientPositive() {
        Patient p = new Patient("Ivan", 1, "Influenza");
        boolean result = hospital.registerPatient(p);
        assertTrue(result, "Registration with a unique ID should return true.");
        assertEquals(1, hospital.getPatientList().getSize(), "The list size should be 1.");
    }

    @Test
    void testAddAppointmentPositive() {
        Doctor doc = new Doctor("Stepan", 10, "Diagnostician");
        Patient pat = new Patient("Gregory", 11, "Common cold");

        boolean result = hospital.addAppointment("10.05.2026", doc, pat);

        assertTrue(result, "Booking an available date should succeed.");
        assertEquals(1, hospital.getAppointmentList().getSize(), "The appointment should be added to the list.");
    }

    // Negative scenarios
    @Test
    void testRegisterPatientNegativeDuplicated() {
        Patient p1 = new Patient("Ivan", 1, "Influenza");
        hospital.registerPatient(p1);

        Patient p2 = new Patient("Oleh", 1, "Cough");
        boolean result = hospital.registerPatient(p2);

        assertFalse(result, "Registration with a duplicate ID should return false.");
        assertEquals(1, hospital.getPatientList().getSize(), "The list size should not change.");
    }

    @Test
    void testAddAppointmentNegativeConflict() {
        Doctor doc = new Doctor("Stepan", 10, "Diagnostician");
        Patient pat1 = new Patient("Gregory", 11, "Common cold");
        Patient pat2 = new Patient("Hryts", 12, "Influenza");

        hospital.addAppointment("10.10.2026", doc, pat1);

        boolean result = hospital.addAppointment("10.10.2026", doc, pat2);

        assertFalse(result, "Booking an occupied date should return false.");
        assertEquals(1, hospital.getAppointmentList().getSize(), "The second appointment should not be added to the list.");
    }

    @Test
    void testDoublyLinkedListNegativeOutOfBounds() {
        DoublyLinkedList<Doctor> list = new DoublyLinkedList<>();
        list.add(new Doctor("Doc", 1, "Surgeon"));

        assertThrows(IndexOutOfBoundsException.class, () -> {
            list.get(5);
        }, "An IndexOutOfBoundsException should be thrown.");
    }
}
