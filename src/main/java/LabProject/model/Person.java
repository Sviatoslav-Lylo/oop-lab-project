package LabProject.model;

public abstract class Person implements Comparable<Person>{
    private int id;
    private String name;

    public Person(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public Person() {
    }

    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }

    public String getName() { return name; }
    public int getId() { return id; }

    public abstract void showProfile();

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null || getClass() != obj.getClass()) return false; // якщо різні класи
        Person person = (Person) obj;
        return id == person.id;
    }

    @Override
    public int compareTo(Person other) {
        int nameComparison = this.name.compareTo(other.name);
        if(nameComparison != 0) {
            return nameComparison;
        }
        return Integer.compare(this.id, other.id);
    }
}
