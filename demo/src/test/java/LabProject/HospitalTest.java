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

    @BeforeEach // перед кожним тестом
    void setup() {
        IDvalidator idValidator = new IDvalidator();
        AppointmentValidator appValidator = new AppointmentValidator();
        JsonStorageService storageService = new JsonStorageService();

        hospital = new Hospital(idValidator, appValidator, storageService);
    }

    // Позитивні сценарії
    @Test
    void testRegisterPatientPositive() {
        Patient p = new Patient("Іван", 1, "Грип");
        boolean result = hospital.registerPatient(p);
        assertTrue(result, "Реєстрація з унікальним ID має повернути True.");
        assertEquals(1, hospital.getPatientList().getSize(), "Розмір списку має стати 1.");
    }

    @Test
    void testAddAppointmentPositive() {
        Doctor doc = new Doctor("Степан", 10, "Діагност");
        Patient pat = new Patient("Gregory", 11, "Застуда");

        boolean result = hospital.addAppointment("10.05.2026", doc, pat);

        assertTrue(result, "Запис на вільну дату має бути успішним.");
        assertEquals(1, hospital.getAppointmentList().getSize(), "Запис має додатися до списку.");
    }

    // Негативні сценарії
    @Test
    void testRegisterPatientNegativeDuplicated() {
        Patient p1 = new Patient("Іван", 1, "Грип");
        hospital.registerPatient(p1);

        Patient p2 = new Patient("Олег", 1, "Кашель");
        boolean result = hospital.registerPatient(p2);

        assertFalse(result, "Реєстрація з однаковим ID має повернути false.");
        assertEquals(1, hospital.getPatientList().getSize(), "Розмір списку не має змінитися");
    }

    @Test
    void testAddAppointmentNegativeConflict() {
        Doctor doc = new Doctor("Степан", 10, "Діагност");
        Patient pat1 = new Patient("Gregory", 11, "Застуда");
        Patient pat2 = new Patient("Гриць", 12, "Грип");

        hospital.addAppointment("10.10.2026", doc, pat1);

        boolean result = hospital.addAppointment("10.10.2026", doc, pat2);

        assertFalse(result, "Запис на зайняту дату має повернути false.");
        assertEquals(1, hospital.getAppointmentList().getSize(), "Другий запис не має додатися до списку.");
    }

    @Test
    void testDoublyLinkedListNegativeOutOfBounds() {
        DoublyLinkedList<Doctor> list = new DoublyLinkedList<>();
        list.add(new Doctor("Doc", 1, "Хірург"));

        assertThrows(IndexOutOfBoundsException.class, () -> {
            list.get(5);
        }, "Має викидатися виняток IndexOutOfBoundsException");
    }
}
