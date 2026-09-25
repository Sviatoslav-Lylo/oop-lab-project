package LabProject.service;
import org.springframework.stereotype.Service;

import LabProject.collection.DoublyLinkedList;
import LabProject.model.Appointment;
import LabProject.model.Doctor;
import LabProject.model.Patient;
import LabProject.validation.AppointmentValidator;
import LabProject.validation.IDvalidator;
import jakarta.annotation.PostConstruct;

@Service
public class Hospital implements PeopleManager{
    
    private final IDvalidator iDvalidator;
    private final AppointmentValidator appointmentValidator;
    private final JsonStorageService storageService;

    public Hospital(IDvalidator iDvalidator, AppointmentValidator appointmentValidator, JsonStorageService storageService) {
        this.iDvalidator = iDvalidator;
        this.appointmentValidator = appointmentValidator;
        this.storageService = storageService;
    }

    //Database
    private DoublyLinkedList<Doctor> doctors = new DoublyLinkedList<>();
    private DoublyLinkedList<Patient> patients = new DoublyLinkedList<>();
    private DoublyLinkedList<Appointment> appointments = new DoublyLinkedList<>();

    @PostConstruct
    public void init() {
        storageService.loadData(this);
    }

    public void saveAllData() {
        storageService.saveAll(doctors, patients, appointments);
    }
    
    //Setters
    public boolean addAppointment(String date, Doctor doctor, Patient patient) {
        if(appointmentValidator.checkAppointmentAvailability(date, doctor.getName(), appointments)) {
            Appointment newAppointment = new Appointment(date, doctor, patient);
            appointments.add(newAppointment);
            return true;
        }
        return false;
    }

    //Getters
    public Doctor findDoctor(int id) {
        for(Doctor p : doctors) {
            if(p.getId() == id) return p;
        }
        return null;
    }

    public Patient findPatient(int id) {
        for(Patient p : patients) {
            if(p.getId() == id) return p;
        }
        return null;
    }

    public Doctor findDoctor(String name) {
        for(Doctor p : doctors) {
            if(p.getName().equals(name)) return p;
        }
        return null;
    }

    public Patient findPatient(String name) {
        for(Patient p : patients) {
            if(p.getName().equals(name)) return p;
        }
        return null;
    }

    public DoublyLinkedList<Patient> getPatientList() {
        return patients;
    }

    public DoublyLinkedList<Doctor> getDoctorList() {
        return doctors;
    }

    public DoublyLinkedList<Appointment> getAppointmentList() {
        return appointments;
    }

    public void displayAllDoctors() {
        System.out.println();
        for(Doctor p : doctors) {
            p.showProfile();
        }
    }

    public void displayAllPatients() {
        System.out.println();
        for(Patient p : patients) {
            p.showProfile();
        }
    }
    
    public void displayAppointments() {
            for(Appointment a : appointments) {
                System.out.println("Дата: " + a.getAppointmentDate() + "\nЛікар: " + a.getAppointmentDoctor().getName() + "\nПацієнт: " + a.getAppointmentPatient().getName());
                System.out.println("-------------");
            }
    }
    
    @Override
    public boolean registerPatient(Patient p) {
        if(iDvalidator.checkIdOriginality(p.getId(), doctors, patients)) {
            patients.add(p);
            System.out.println("Пацієнта " + p.getName() + " успішно зареєстровано.");
            return true;
        }
        else {
            System.out.println("Помилка: ID зайнятий.");
            return false;
        }
    }

    @Override
    public void dischargePatient(int id) {
        Patient p = findPatient(id);
        if(p != null) {
            patients.remove(p);
            System.out.println("Пацієнта виписано.");
        } else {
            System.out.println("Пацієнта не знайдено.");
        }
    }

    @Override
    public boolean registerDoctor(Doctor p) {
        if(iDvalidator.checkIdOriginality(p.getId(), doctors, patients)) {
            doctors.add(p);
            System.out.println("Лікаря " + p.getName() + " успішно зареєстровано.");
            return true;
        }
        else {
            System.out.println("Помилка: ID зайнятий.");
            return false;
        }
    }

    @Override
    public void dischargeDoctor(int id) {
        Doctor p = findDoctor(id);
        if(p != null) {
            doctors.remove(p);
            System.out.println("Лікаря виписано.");
        } else {
            System.out.println("Лікаря не знайдено.");
        }
    }
}
