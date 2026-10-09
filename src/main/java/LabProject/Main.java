package LabProject;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import LabProject.decorator.InsuranceDecorator;
import LabProject.factory.PersonFactory;
import LabProject.factory.PersonType;
import LabProject.model.Doctor;
import LabProject.model.Patient;
import LabProject.model.Person;
import LabProject.service.Hospital;
import LabProject.strategy.NameSortStrategy;
import LabProject.strategy.SortContext;
import LabProject.strategy.IDSortStrategy;

import java.util.Scanner;

@SpringBootApplication
public class Main implements CommandLineRunner {

    private final Hospital myHospital;
    private final PersonFactory personFactory;

    public Main(Hospital myHospital, PersonFactory personFactory) {
        this.myHospital = myHospital;
        this.personFactory = personFactory;
    }
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner sc = new Scanner(System.in);
        SortContext<Patient> patientSortContext = new SortContext<>();
        SortContext<Doctor> doctorSortContext = new SortContext<>();

        // Use an anonymous one-off class
        Person director = new Person("Roman", 0) {
            String phoneNumber = "+38 067 033 7777";
            @Override
            public void showProfile() {
                System.out.println("=====================");
                System.out.println("System administrator");
                System.out.println(getName());
                System.out.println("Phone number: " + phoneNumber);
                System.out.println("=====================");
            }
        };

        // Menu
        while(true) {
            System.out.println("---------- HOSPITAL ----------");
            System.out.println("1. Register a patient");
            System.out.println("2. Register a doctor");
            System.out.println("3. Discharge a patient");
            System.out.println("4. Discharge a doctor");
            System.out.println("5. Show all patients");
            System.out.println("6. Show all doctors");
            System.out.println("7. Show the director's profile");
            System.out.println("Person search:");
            System.out.println("8. Find a doctor by name");
            System.out.println("9. Find a doctor by ID");
            System.out.println("10. Find a patient by name");
            System.out.println("11. Find a patient by ID");
            System.out.println("Appointments:");
            System.out.println("12. Add an appointment");
            System.out.println("13. View all appointments\n-----------------------------");
            System.out.println("14. Sort the patient list.");
            System.out.println("15. Sort the doctor list.");
            System.out.println("0. Exit.");
            System.out.println("-----------------------------");
            System.out.print("\nChoose an action: ");
            int choice  = sc.nextInt();
            
            switch (choice) {
                case 1:
                    boolean free_patient_ID;
                    do {
                        sc.nextLine();
                        System.out.print("Enter name: ");
                        String registerPatientName = sc.nextLine();

                        System.out.print("Enter ID: ");
                        int registerPatientID = sc.nextInt();
                        sc.nextLine();

                        System.out.print("Enter diagnosis: ");
                        String registerPatientDiagnosis = sc.nextLine();
                            
                        Patient newPatient = (Patient) personFactory.createPerson(
                            PersonType.PATIENT, 
                            registerPatientName, 
                            registerPatientID, 
                            registerPatientDiagnosis
                        );
                        free_patient_ID = myHospital.registerPatient(newPatient);
                    } while(!free_patient_ID);
                    break;
                case 2:
                    boolean free_doctor_ID;
                    do {
                        sc.nextLine();
                        System.out.print("Enter name: ");
                        String registerDoctortName = sc.nextLine();

                        System.out.print("Enter ID: ");
                        int registerDoctorID = sc.nextInt();
                        sc.nextLine();

                        System.out.print("Enter specialization: ");
                        String registerDoctorSpecialization = sc.nextLine();
                            
                        Doctor newDoctor = (Doctor) personFactory.createPerson(
                            PersonType.DOCTOR, 
                            registerDoctortName, 
                            registerDoctorID, 
                            registerDoctorSpecialization
                        );
                        free_doctor_ID = myHospital.registerDoctor(newDoctor);
                    } while(!free_doctor_ID);
                    break;
                case 3:
                    int dischargePatientID;
                    System.out.print("Enter ID: ");
                    dischargePatientID = sc.nextInt();
                    sc.nextLine();
                    myHospital.dischargePatient(dischargePatientID);
                    break;
                case 4:
                    int dischargeDoctorID;
                    System.out.print("Enter ID: ");
                    dischargeDoctorID = sc.nextInt();
                    sc.nextLine();
                    myHospital.dischargeDoctor(dischargeDoctorID);
                    break;
                case 5:
                    myHospital.displayAllPatients();
                    break;
                case 6:
                    myHospital.displayAllDoctors();
                    break;
                case 7:
                    Person dicoratedDirector = new InsuranceDecorator(director, "Premium package #777");
                    dicoratedDirector.showProfile();
                    break;
                case 8:
                    sc.nextLine();
                    System.out.print("Enter name: ");
                    String name3 = sc.nextLine();

                    Person p3 = myHospital.findDoctor(name3);
                    if(p3 != null) p3.showProfile();
                    else System.out.println("Not found.\n");
                    break;
                case 9:
                    System.out.print("Enter ID: ");
                    int id4  = sc.nextInt();

                    Person p4 = myHospital.findDoctor(id4);
                    if(p4 != null) p4.showProfile();
                    else System.out.println("Not found.\n");
                    break;
                case 10:
                    sc.nextLine();
                    System.out.print("Enter name: ");
                    String name5 = sc.nextLine();

                    Person p5 = myHospital.findPatient(name5);
                    if(p5 != null) p5.showProfile();
                    else System.out.println("Not found.\n");
                    break;
                case 11:
                    System.out.print("Enter ID: ");
                    int id6  = sc.nextInt();

                    Person p6 = myHospital.findPatient(id6);
                    if(p6 != null) p6.showProfile();
                    else System.out.println("Not found.\n");
                    break;
                case 12:
                    System.out.print("Enter doctor ID: ");
                    int appointmentDoctorID  = sc.nextInt();
                    Doctor appointmentDoctor = myHospital.findDoctor(appointmentDoctorID);
                    
                    System.out.print("Enter patient ID: ");
                    int appointmentPatientID  = sc.nextInt();
                    Patient appointmentPatient = myHospital.findPatient(appointmentPatientID);
                    
                    sc.nextLine();

                    boolean freeDate;
                    do {
                        System.out.print("Enter appointment date (dd.MM.yyyy): ");
                        String appointmentDate = sc.nextLine();

                        freeDate = myHospital.addAppointment(appointmentDate, appointmentDoctor, appointmentPatient);
                        
                        if(!freeDate) System.out.println("Unfortunately, the doctor " + appointmentDoctor.getName() + " is not available on this date.");
                        else System.out.println("Appointment created successfully!");
                    } while(!freeDate);
                    break;
                case 13:
                    myHospital.displayAppointments();
                    break;
                case 14:
                    System.out.println("How would you like to sort patients?");
                    System.out.println("1. By name");
                    System.out.println("2. By ID");
                    int sortChoiceP = sc.nextInt();

                    if(sortChoiceP == 1) {
                        patientSortContext.setStrategy(new NameSortStrategy<>());
                    } else {
                        patientSortContext.setStrategy(new IDSortStrategy<>());
                    }

                    patientSortContext.executeSort(myHospital.getPatientList());
                    System.out.println("List sorted.");
                    myHospital.displayAllPatients();
                    break;
                case 15:
                    System.out.println("How would you like to sort doctors?");
                    System.out.println("1. By name");
                    System.out.println("2. By ID");
                    int sortChoiceD = sc.nextInt();

                    if(sortChoiceD == 1) {
                        doctorSortContext.setStrategy(new NameSortStrategy<>());
                    } else {
                        doctorSortContext.setStrategy(new IDSortStrategy<>());
                    }

                    doctorSortContext.executeSort(myHospital.getDoctorList());
                    System.out.println("List sorted.");
                    myHospital.displayAllDoctors();
                    break;
                default:
                    myHospital.saveAllData();
                    System.out.println("Thank you for using the application! Have a nice day!\n");
                    sc.close();
                    return;
            }
        }
    }
}