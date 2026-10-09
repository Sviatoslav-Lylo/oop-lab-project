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
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Component
public class JsonStorageService {
    private final ObjectMapper objectMapper = new ObjectMapper();
    
    private static final Path DATA_DIRECTORY = Path.of("data");
    private static final String DOCTORS_FILE = DATA_DIRECTORY.resolve("doctors.json").toString();
    private static final String PATIENTS_FILE = DATA_DIRECTORY.resolve("patients.json").toString();
    private static final String APPOINTMENTS_FILE = DATA_DIRECTORY.resolve("appointments.json").toString();

    private <T extends Comparable<? super T>> boolean saveToFile(String fileName, DoublyLinkedList<T> list) {
        try {
            List<T> standardList = new ArrayList<>();

            for(T item : list) {
                standardList.add(item);
            }

            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(fileName), standardList);
            return true;
        } catch (IOException e) {
            System.err.println("Error saving to " + fileName + ": " + e.getMessage());
            return false;
        }
    }

    public void saveAll(DoublyLinkedList<Doctor> d, DoublyLinkedList<Patient> p, DoublyLinkedList<Appointment> a) {
        try {
            Files.createDirectories(DATA_DIRECTORY);
        } catch (IOException e) {
            System.err.println("Error creating data directory: " + e.getMessage());
            return;
        }

        boolean doctorsSaved = saveToFile(DOCTORS_FILE, d);
        boolean patientsSaved = saveToFile(PATIENTS_FILE, p);
        boolean appointmentsSaved = saveToFile(APPOINTMENTS_FILE, a);
        if(doctorsSaved && patientsSaved && appointmentsSaved) {
            System.out.println("All data saved to JSON successfully.");
        }
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
