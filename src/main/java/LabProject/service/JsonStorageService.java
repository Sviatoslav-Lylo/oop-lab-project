package LabProject.service;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import LabProject.collection.DoublyLinkedList;
import LabProject.model.Doctor;
import LabProject.model.Patient;
import LabProject.model.Appointment;

import org.springframework.stereotype.Component;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class JsonStorageService {
    private final ObjectMapper objectMapper = new ObjectMapper();
    
    private static final String DOCTORS_FILE = "doctors.json";
    private static final String PATIENTS_FILE = "patients.json";
    private static final String APPOINTMENTS_FILE = "appointments.json";

    private <T extends Comparable<? super T>> void saveToFile(String fileName, DoublyLinkedList<T> list) {
        try {
            List<T> standardList = new ArrayList<>();

            for(T item : list) {
                standardList.add(item);
            }

            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(fileName), standardList);
        } catch (IOException e) {
            System.err.println("Error saving to " + fileName + ": " + e.getMessage());
        }
    }

    public void saveAll(DoublyLinkedList<Doctor> d, DoublyLinkedList<Patient> p, DoublyLinkedList<Appointment> a) {
        saveToFile(DOCTORS_FILE, d);
        saveToFile(PATIENTS_FILE, p);
        saveToFile(APPOINTMENTS_FILE, a);
        System.out.println("All data saved to JSON successfully.");
    }

    public void loadData(Hospital hospital) {
        try {
            File dFile = new File(DOCTORS_FILE);
            if(dFile.exists()) {
                List<Doctor> doctors = objectMapper.readValue(dFile, new TypeReference<List<Doctor>>(){});
                doctors.forEach(d -> hospital.getDoctorList().add(d));
            }

            File pFile = new File(PATIENTS_FILE);
            if(pFile.exists()) {
                List<Patient> patients = objectMapper.readValue(pFile, new TypeReference<List<Patient>>(){});
                patients.forEach(p -> hospital.getPatientList().add(p));
            }

            File aFile = new File(APPOINTMENTS_FILE);
            if(aFile.exists()) {
                List<Appointment> appointments = objectMapper.readValue(aFile, new TypeReference<List<Appointment>>(){});
                appointments.forEach(a -> hospital.getAppointmentList().add(a));
            }

            System.out.println("Data restored successfully.");
        } catch (IOException e) {
            System.err.println("Error loading data: " + e.getMessage());
        }
    }
}
