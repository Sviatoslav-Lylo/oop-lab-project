package LabProject.model;

public class Appointment implements Comparable<Appointment>{
        private String appointmentDate;
        private Doctor appointmentDoctor;
        private Patient appointmentPatient;

        public Appointment(String appointmentDate, Doctor appointmentDoctor, Patient appointmentPatient) {
            this.appointmentDate = appointmentDate;
            this.appointmentDoctor = appointmentDoctor;
            this.appointmentPatient = appointmentPatient;
        }

        public Appointment() {
        }

        public String getAppointmentDate() {
            return appointmentDate;
        }

        public Doctor getAppointmentDoctor() {
            return appointmentDoctor;
        }
        
        public Patient getAppointmentPatient() {
            return appointmentPatient;
        }

        public void setAppointmentDate(String date) {this.appointmentDate = date; }
        public void setAppointmentDoctor(Doctor doctor) {this.appointmentDoctor = doctor; }
        public void setAppointmentPatient(Patient patient) {this.appointmentPatient = patient; }

        @Override
        public int compareTo(Appointment other) {
            return this.appointmentDate.compareTo(other.appointmentDate);
        }
    }