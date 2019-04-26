package part01.lesson05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;

public class Pet {

    private int id;
    private String petname;
    private Person person;
    private int weight;

    public Pet(int id, String petname, Person person, int weight) {
        this.id = id;
        this.petname = petname;
        this.person = person;
        this.weight = weight;
    }

    public Pet() {
//        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
//        while (!bufferedReader.readLine().equals("exit")) {
//
//            System.out.println("Введите id животного ");
//            String s = bufferedReader.readLine();
//            int id1 = Integer.parseInt(s);
//            setId(id1);
//            System.out.println("Введите кличку животного ");
//            setPetname(bufferedReader.readLine());
//            setPerson(new Person("test", 11, true));
//            System.out.println("Введите вес животного ");
//            int weight1 = Integer.parseInt(bufferedReader.readLine());
//            setWeight(weight1);
//        }
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPetname(String petname) {
        this.petname = petname;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getId() {
        return id;
    }

    public String getPetname() {
        return petname;
    }

    public Person getPerson() {
        return person;
    }

    public int getWeight() {
        return weight;
    }


//    public Pet searchByPetName(String name) {
//
//        return Pet;
//    }
}
