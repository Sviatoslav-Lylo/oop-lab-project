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

        // Використання анонімного "одноразового" класу
        Person director = new Person("Roman", 0) {
            String phoneNumber = "+38 067 033 7777";
            @Override
            public void showProfile() {
                System.out.println("=====================");
                System.out.println("Системний адміністратор");
                System.out.println(getName());
                System.out.println("Номер телефону: " + phoneNumber);
                System.out.println("=====================");
            }
        };

        // Меню
        while(true) {
            System.out.println("---------- ЛІКАРНЯ ----------");
            System.out.println("1. Зареєструвати пацієнта");
            System.out.println("2. Зареєструвати лікаря");
            System.out.println("3. Виписати пацієнта");
            System.out.println("4. Виписати лікаря");
            System.out.println("5. Показати всіх пацієнтів");
            System.out.println("6. Показати всіх лікарів");
            System.out.println("7. Показати профіль директора");
            System.out.println("Пошук осіб:");
            System.out.println("8. Знайти лікаря за іменем");
            System.out.println("9. Знайти лікаря за ID");
            System.out.println("10. Знайти пацієнта за іменем");
            System.out.println("11. Знайти пацієнта за ID");
            System.out.println("Записи:");
            System.out.println("12. Додати запис");
            System.out.println("13. Переглянути всі записи\n-----------------------------");
            System.out.println("14. Відсортувати список пацієнтів.");
            System.out.println("15. Відсортувати список лікарів.");
            System.out.println("0. Вийти.");
            System.out.println("-----------------------------");
            System.out.print("\nВиберіть дію: ");
            int choice  = sc.nextInt();
            
            switch (choice) {
                case 1:
                    boolean free_patient_ID;
                    do {
                        sc.nextLine();
                        System.out.print("Введіть Ім'я: ");
                        String registerPatientName = sc.nextLine();

                        System.out.print("Введіть ID: ");
                        int registerPatientID = sc.nextInt();
                        sc.nextLine();

                        System.out.print("Введіть діагноз: ");
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
                        System.out.print("Введіть Ім'я: ");
                        String registerDoctortName = sc.nextLine();

                        System.out.print("Введіть ID: ");
                        int registerDoctorID = sc.nextInt();
                        sc.nextLine();

                        System.out.print("Введіть спеціальність: ");
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
                    System.out.print("Введіть ID: ");
                    dischargePatientID = sc.nextInt();
                    sc.nextLine();
                    myHospital.dischargePatient(dischargePatientID);
                    break;
                case 4:
                    int dischargeDoctorID;
                    System.out.print("Введіть ID: ");
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
                    Person dicoratedDirector = new InsuranceDecorator(director, "Преміальний пакет №777");
                    dicoratedDirector.showProfile();
                    break;
                case 8:
                    sc.nextLine();
                    System.out.print("Введіть Ім'я: ");
                    String name3 = sc.nextLine();

                    Person p3 = myHospital.findDoctor(name3);
                    if(p3 != null) p3.showProfile();
                    else System.out.println("Не знайдено.\n");
                    break;
                case 9:
                    System.out.print("Введіть ID: ");
                    int id4  = sc.nextInt();

                    Person p4 = myHospital.findDoctor(id4);
                    if(p4 != null) p4.showProfile();
                    else System.out.println("Не знайдено.\n");
                    break;
                case 10:
                    sc.nextLine();
                    System.out.print("Введіть Ім'я: ");
                    String name5 = sc.nextLine();

                    Person p5 = myHospital.findPatient(name5);
                    if(p5 != null) p5.showProfile();
                    else System.out.println("Не знайдено.\n");
                    break;
                case 11:
                    System.out.print("Введіть ID: ");
                    int id6  = sc.nextInt();

                    Person p6 = myHospital.findPatient(id6);
                    if(p6 != null) p6.showProfile();
                    else System.out.println("Не знайдено.\n");
                    break;
                case 12:
                    System.out.print("Введіть ID лікаря: ");
                    int appointmentDoctorID  = sc.nextInt();
                    Doctor appointmentDoctor = myHospital.findDoctor(appointmentDoctorID);
                    
                    System.out.print("Введіть ID пацієнта: ");
                    int appointmentPatientID  = sc.nextInt();
                    Patient appointmentPatient = myHospital.findPatient(appointmentPatientID);
                    
                    sc.nextLine();

                    boolean freeDate;
                    do {
                        System.out.print("Введіть дату запису у форматі дд.мм.рррр: ");
                        String appointmentDate = sc.nextLine();

                        freeDate = myHospital.addAppointment(appointmentDate, appointmentDoctor, appointmentPatient);
                        
                        if(!freeDate) System.out.println("На цю дату в лікаря " + appointmentDoctor.getName() + " вільного місця, на жаль, нема.");
                        else System.out.println("Запис успішно створено!");
                    } while(!freeDate);
                    break;
                case 13:
                    myHospital.displayAppointments();
                    break;
                case 14:
                    System.out.println("Як сортувати пацієнтів?");
                    System.out.println("1. За ім'ям");
                    System.out.println("2. За ID");
                    int sortChoiceP = sc.nextInt();

                    if(sortChoiceP == 1) {
                        patientSortContext.setStrategy(new NameSortStrategy<>());
                    } else {
                        patientSortContext.setStrategy(new IDSortStrategy<>());
                    }

                    patientSortContext.executeSort(myHospital.getPatientList());
                    System.out.println("Список відсортовано.");
                    myHospital.displayAllPatients();
                    break;
                case 15:
                    System.out.println("Як сортувати лікарів?");
                    System.out.println("1. За ім'ям");
                    System.out.println("2. За ID");
                    int sortChoiceD = sc.nextInt();

                    if(sortChoiceD == 1) {
                        doctorSortContext.setStrategy(new NameSortStrategy<>());
                    } else {
                        doctorSortContext.setStrategy(new IDSortStrategy<>());
                    }

                    doctorSortContext.executeSort(myHospital.getDoctorList());
                    System.out.println("Список відсортовано.");
                    myHospital.displayAllDoctors();
                    break;
                default:
                    myHospital.saveAllData();
                    System.out.println("Дякуємо за використання програми! Гарного дня!\n");
                    sc.close();
                    return;
            }
        }
    }
}