package com.example.universityogranizer.domain;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.persistence.MappedSuperclass;
import javax.validation.Valid;
import javax.validation.constraints.Email;

@MappedSuperclass
public class Person extends BaseEntity{
    @Length(min = 3)
    @Valid
    String personName;
    @Length(min = 3)
    String surname;
    @Range(min = 18)
    Integer age;
    @Email
    String email;

    public Person() {
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        if (personName != null ? !personName.equals(person.personName) : person.personName != null) return false;
        if (surname != null ? !surname.equals(person.surname) : person.surname != null) return false;
        if (age != null ? !age.equals(person.age) : person.age != null) return false;
        return email != null ? email.equals(person.email) : person.email == null;
    }

    @Override
    public int hashCode() {
        int result = personName != null ? personName.hashCode() : 0;
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (age != null ? age.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }
}
