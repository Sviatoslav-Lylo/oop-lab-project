package LabProject.validation;
import org.springframework.stereotype.Component;

import LabProject.collection.DoublyLinkedList;
import LabProject.model.Appointment;

@Component
public class AppointmentValidator {
        public boolean checkAppointmentAvailability(String date, String appointmentDoctorName, DoublyLinkedList<Appointment> appointments) {
        for(Appointment a : appointments) {
            if(date.equals(a.getAppointmentDate()) && appointmentDoctorName.equals(a.getAppointmentDoctor().getName())) return false;
        }
        return true;
    }
}