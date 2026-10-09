package LabProject.decorator;

import LabProject.model.Person;

public class InsuranceDecorator extends PersonDecorator{
    private String insurancePolicy;

    public InsuranceDecorator(Person decoratedPerson, String insurancePolicy) {
        super(decoratedPerson);
        this.insurancePolicy = insurancePolicy;
    }

    @Override
    public void showProfile() {
        super.showProfile();
        System.out.println(">>> Insurance: " + insurancePolicy);
        System.out.println("------------------------------");
    }
}
