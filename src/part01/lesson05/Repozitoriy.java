package part01.lesson05;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Repozitoriy {

    private ArrayList<Pet> pets;
    private ArrayList<Person> persons;
   // private HashMap<Integer, ArrayList<Pet>> petMap;   // мне надо сделать



    public Repozitoriy() {
        pets = new ArrayList<Pet>();
        persons = new ArrayList<Person>();
    }

    public void addPet(Pet pet) throws Exception {
        checkID(pet);
        pets.add(pet);
        petMap.put(pet.getId(), )
    }

    public void addPerson(Person person) {
        persons.add(person);
    }

    private void checkID(Pet pet) throws Exception {
        for (Pet value: pets) {
            if (value.getId() == pet.getId()) {
                throw new Exception("повтор");
            }
        }
    }
}
