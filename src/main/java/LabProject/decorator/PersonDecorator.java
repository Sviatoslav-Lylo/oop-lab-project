package LabProject.decorator;

import LabProject.model.Person;

public abstract class PersonDecorator extends Person {
    protected Person decoratedPerson;

    public PersonDecorator(Person decoratedPerson) {
        super(decoratedPerson.getName(), decoratedPerson.getId());
        this.decoratedPerson = decoratedPerson;
    }

    @Override
    public void showProfile() {
        decoratedPerson.showProfile();
    }
}
